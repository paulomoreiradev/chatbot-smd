package com.example;

import java.util.Scanner;
import java.text.Normalizer;

import entities.Curso;
import entities.Disciplina;
import entities.Mensagem;
import entities.Periodo;
import entities.RespostaCurso;
import entities.RespostaDisciplina;
import entities.RespostaProfessor;
import users.Professor;
import users.Usuario;

public class Chatbot {
    private Usuario usuario;
    private Curso curso;
    private static int contadorMensagens = 0;

    public Chatbot(Curso curso) {
        this.curso = curso;
    }

    public void iniciarConversa() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Chatbot: Olá! Qual é o seu nome? ");
        String nomeUsuario = scanner.nextLine();
        this.usuario = new Usuario(nomeUsuario);

        System.out.println("Chatbot: Prazer em conhecê-lo, " + usuario.getNome() + "! Como posso ajudar você hoje?");
        System.out.println("Você pode fazer perguntas sobre o curso, disciplinas ou professores. Digite 'sair' para encerrar.");
        System.out.println("Digite 'help' para ver os comandos disponíveis.");

        while (true) {
            System.out.print(usuario.getNome() + ": ");
            String textoMensagem = scanner.nextLine();

            if (textoMensagem.equalsIgnoreCase("sair")) {
                System.out.println("Chatbot: Até logo, " + usuario.getNome() + "!");
                break;
            }

            Mensagem mensagem = new Mensagem(textoMensagem);
            processarMensagem(mensagem);
        }

        scanner.close();
    }

    public void processarMensagem(Mensagem mensagem) {
        contadorMensagens++;
        String resposta = gerarResposta(mensagem.getTexto());
        System.out.println("Chatbot: " + resposta);
    }

    private String gerarResposta(String texto) {
        texto = texto.toLowerCase();

        if (texto.contains("help") || texto.contains("ajuda")) {
            return mostrarHelp();
        } else if (texto.contains("curso")) {
            return new RespostaCurso(curso).gerarResposta();
        } else if (texto.contains("listar disciplinas") || texto.contains("quais são as disciplinas")) {
            // Lista todas as disciplinas do curso
            return listarDisciplinas();
        } else if (texto.contains("disciplina")) {
            // Remove a palavra "disciplina" do texto da pergunta
            texto = texto.replace("disciplina", "").trim();

            // Verifica se o usuário está pedindo disciplinas de um período específico
            if (texto.matches(".*\\d+.*")) { // Verifica se há números no texto
                // Extrai o número do período
                int periodo = Integer.parseInt(texto.replaceAll("\\D+", ""));
                return listarDisciplinasPorPeriodo(periodo);
            }

            // Normaliza o texto de entrada
            texto = normalizarTexto(texto);

            // Variável para armazenar a disciplina mais específica encontrada
            Disciplina disciplinaEncontrada = null;
            int maiorTamanho = 0;

            // Procura pelo nome da disciplina no texto, iterando sobre os períodos
            for (Periodo periodo : curso.getPeriodos()) {
                for (Disciplina disciplina : periodo.getDisciplinas()) {
                    // Normaliza o nome da disciplina
                    String nomeDisciplinaNormalizado = normalizarTexto(disciplina.getNome());

                    // Verifica se o texto contém o nome da disciplina
                    if (texto.contains(nomeDisciplinaNormalizado) || nomeDisciplinaNormalizado.contains(texto)) {
                        // Prioriza a disciplina com o nome mais longo (mais específico)
                        if (nomeDisciplinaNormalizado.length() > maiorTamanho) {
                            disciplinaEncontrada = disciplina;
                            maiorTamanho = nomeDisciplinaNormalizado.length();
                        }
                    }
                }
            }

            // Retorna a disciplina encontrada (se houver)
            if (disciplinaEncontrada != null) {
                return new RespostaDisciplina(disciplinaEncontrada).gerarResposta();
            }

            // Retorna mensagem de erro se a disciplina não for encontrada
            return "Desculpe, não encontrei informações sobre essa disciplina.";
        } else if (texto.contains("professor")) {
            // Procura pelo nome do professor no texto, iterando sobre os períodos e disciplinas
            for (Periodo periodo : curso.getPeriodos()) {
                for (Disciplina disciplina : periodo.getDisciplinas()) {
                    for (Professor professor : disciplina.getProfessores()) {
                        if (texto.contains(professor.getNome().toLowerCase())) {
                            return new RespostaProfessor(professor).gerarResposta();
                        }
                    }
                }
            }
            return "Desculpe, não encontrei informações sobre esse professor.";
        } else {
            return "Desculpe, não entendi. Digite 'help' para ver os comandos disponíveis.";
        }
    }

    private String mostrarHelp() {
        StringBuilder helpStr = new StringBuilder("Comandos disponíveis:\n");
        helpStr.append("- curso: Informações sobre o curso.\n");
        helpStr.append("- disciplina [nome]: Informações sobre uma disciplina específica.\n");
        helpStr.append("- disciplinas do [X] período: Lista as disciplinas de um período específico.\n");
        helpStr.append("- listar disciplinas: Lista todas as disciplinas do curso.\n");
        helpStr.append("- professor [nome]: Informações sobre um professor específico.\n");
        helpStr.append("- sair: Encerra a conversa com o Chatbot.\n");
        helpStr.append("- help/ajuda: Exibe esta mensagem de ajuda.\n");
        return helpStr.toString();
    }

    private String listarDisciplinas() {
        StringBuilder disciplinasStr = new StringBuilder("Disciplinas do curso ");
        disciplinasStr.append(curso.getNome()).append(" por período:\n");

        for (Periodo periodo : curso.getPeriodos()) {
            disciplinasStr.append("Período ").append(periodo.getOrdem()).append(":\n");
            for (Disciplina disciplina : periodo.getDisciplinas()) {
                disciplinasStr.append("- ").append(disciplina.getNome()).append("\n");
            }
        }

        return disciplinasStr.toString();
    }

    private String listarDisciplinasPorPeriodo(int periodo) {
        StringBuilder disciplinasStr = new StringBuilder("Disciplinas do " + periodo + "º período:\n");

        for (Periodo p : curso.getPeriodos()) {
            if (p.getOrdem() == periodo) {
                for (Disciplina disciplina : p.getDisciplinas()) {
                    disciplinasStr.append("- ").append(disciplina.getNome()).append("\n");
                }
                return disciplinasStr.toString();
            }
        }

        return "Desculpe, não encontrei disciplinas para o " + periodo + "º período.";
    }

    public static int getContadorMensagens() {
        return contadorMensagens;
    }

    public static String normalizarTexto(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("\\p{M}", "");
        return texto.toLowerCase();
    }
}
