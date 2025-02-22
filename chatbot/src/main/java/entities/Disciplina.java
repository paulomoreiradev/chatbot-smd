package entities;
import java.util.List;
import users.Professor;

public class Disciplina {
    private String nome;
    private String descricao;
    private List<Professor> professores; // Adicionando lista de professores

    public Disciplina(String nome, String descricao, List<Professor> professores) {
        this.nome = nome;
        this.descricao = descricao;
        this.professores = professores;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Professor> getProfessores() {
        return professores; // Método para acessar a lista de professores
    }
}
