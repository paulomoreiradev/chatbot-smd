package users;
import entities.Curso;

public class Aluno extends Pessoa {
    private String matricula;
    private Curso curso; // Associação com a classe Curso

    public Aluno(String nome, String matricula, Curso curso) {
        super(nome); // Chama o construtor da classe Pessoa
        this.matricula = matricula;
        this.curso = curso;
    }

    public String getMatricula() {
        return matricula;
    }

    public Curso getCurso() {
        return curso;
    }

    @Override
    public String toString() {
        return "Aluno [Nome: " + getNome() + ", Matrícula: " + matricula + ", Curso: " + curso.getNome() + "]";
    }
}
