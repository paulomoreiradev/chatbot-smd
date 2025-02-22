package com.example;
import entities.Curso;
import utils.CarregadorDados;

public class Main {
    public static void main(String[] args) {
        Curso curso = CarregadorDados.carregarDados();
        if (curso != null) {
            Chatbot chatbot = new Chatbot(curso);
            chatbot.iniciarConversa();
        } else {
            System.out.println("Erro ao carregar os dados do curso.");
        }
        System.out.println("Total de mensagens processadas: " + Chatbot.getContadorMensagens());
    }
}
