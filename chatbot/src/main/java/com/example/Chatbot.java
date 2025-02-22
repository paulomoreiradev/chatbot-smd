package com.example;

import java.util.Scanner;

import entities.Curso;
import entities.Disciplina;
import entities.Mensagem;
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

        if (texto.contains("curso")) {
            return new RespostaCurso(curso).gerarResposta();
        } else if (texto.contains("listar disciplinas") || texto.contains("quais são as disciplinas")) {
            return listarDisciplinas();
        } else if (texto.contains("disciplina")) {
            // Procura pelo nome da disciplina no texto
            for (Disciplina disciplina : curso.getDisciplinas()) {
                if (texto.contains(disciplina.getNome().toLowerCase())) {
                    return new RespostaDisciplina(disciplina).gerarResposta();
                }
            }
            return "Desculpe, não encontrei informações sobre essa disciplina.";
        } else if (texto.contains("professor")) {
            // Procura pelo nome do professor no texto
            for (Professor professor : curso.getProfessores()) {
                if (texto.contains(professor.getNome().toLowerCase())) {
                    return new RespostaProfessor(professor).gerarResposta();
                }
            }
            return "Desculpe, não encontrei informações sobre esse professor.";
        } else {
            return "Desculpe, não entendi. Você pode perguntar sobre o curso, disciplinas ou professores.";
        }
    }

    private String listarDisciplinas() {
        StringBuilder disciplinasStr = new StringBuilder("Disciplinas do curso:\n");
        for (Disciplina disciplina : curso.getDisciplinas()) {
            disciplinasStr.append("- ").append(disciplina.getNome()).append("\n");
        }
        return disciplinasStr.toString();
    }

    public static int getContadorMensagens() {
        return contadorMensagens;
    }
}
