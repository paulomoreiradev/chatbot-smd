package entities;

import java.util.List;

import users.Professor;

public class Curso {
    private String nome;
    private List<Disciplina> disciplinas;
    private List<Professor> professores;

    public Curso(String nome, List<Disciplina> disciplinas, List<Professor> professores) {
        this.nome = nome;
        this.disciplinas = disciplinas;
        this.professores = professores;
    }

    public String getNome() {
        return nome;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public List<Professor> getProfessores() {
        return professores;
    }
}
