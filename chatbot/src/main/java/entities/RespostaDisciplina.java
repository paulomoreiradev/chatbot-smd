package entities;
import interfaces.InterfaceResposta;

public class RespostaDisciplina implements InterfaceResposta {
    private Disciplina disciplina;

    public RespostaDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public String gerarResposta() {
        return "A disciplina " + disciplina.getNome() + " tem como descrição: " + disciplina.getDescricao();
    }
}
