package com.example;
import entities.Curso;
import utils.CarregadorDados;

public class Main {
    public static void main(String[] args) {
        // Caminho do arquivo JSON no classpath
        String caminhoArquivo = "dados.json"; // O arquivo deve estar em src/main/resources

        // Carrega os dados do curso
        Curso curso = CarregadorDados.carregarDados(caminhoArquivo);

        if (curso != null) {
            Chatbot chatbot = new Chatbot(curso);
            chatbot.iniciarConversa();
        } else {
            System.out.println("Erro ao carregar os dados do curso.");
        }

        System.out.println("Total de mensagens processadas: " + Chatbot.getContadorMensagens());
    }
}
