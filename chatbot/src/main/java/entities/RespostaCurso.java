package entities;
import interfaces.InterfaceResposta;

public class RespostaCurso implements InterfaceResposta {
    private Curso curso;

    public RespostaCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public String gerarResposta() {
        return "O curso de " + curso.getNome() + " possui " + curso.getPeriodos().size() + " períodos.";
    }
}
