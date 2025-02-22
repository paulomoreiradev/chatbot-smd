package users;

public class Professor extends Pessoa {
    private String areaAtuacao;

    public Professor(String nome, String areaAtuacao) {
        super(nome);
        this.areaAtuacao = areaAtuacao;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }
}
