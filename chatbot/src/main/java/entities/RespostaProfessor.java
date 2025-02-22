package entities;
import interfaces.InterfaceResposta;
import users.Professor;

public class RespostaProfessor implements InterfaceResposta {
    private Professor professor;

    public RespostaProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String gerarResposta() {
        return "O professor " + professor.getNome() + " atua na área de " + professor.getAreaAtuacao();
    }
}
