package entities;

import java.util.List;

public class Curso {
    private String nome;
    private List<Periodo> periodos;

    public Curso(String nome, List<Periodo> periodos) {
        this.nome = nome;
        this.periodos = periodos;
    }

    public String getNome() {
        return nome;
    }

    public List<Periodo> getPeriodos() {
        return periodos;
    }
}
