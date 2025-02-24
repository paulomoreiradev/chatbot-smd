package entities;
import interfaces.InterfaceResposta;
import java.util.Random;

public class RespostaCurso implements InterfaceResposta {
    private Curso curso;
    private Random random;

    public RespostaCurso(Curso curso) {
        this.curso = curso;
        this.random = new Random();
    }

    @Override
    public String gerarResposta() {
        // Lista de respostas possíveis
        String[] respostas = {
                "O curso de " + curso.getNome() + " forma bacharéis especializados em Sistemas Multimídia e Mídias Digitais, preparando profissionais para atuar em áreas como desenvolvimento web, jogos digitais e animações gráficas.",
                "No curso " + curso.getNome() + ", você se tornará um especialista em Sistemas Multimídia e Mídias Digitais, contribuindo para o desenvolvimento de um polo tecnológico no Ceará.",
                "O curso " + curso.getNome() + " tem como objetivo formar profissionais qualificados para atuar em sistemas web, dispositivos móveis, jogos digitais e produção de mídias digitais.",
                "Se você escolher o curso " + curso.getNome() + ", estará preparado para inovar em áreas como desenvolvimento de sistemas multimídia e criação de mídias digitais, impulsionando o setor tecnológico regional.",
                "O curso " + curso.getNome() + " é focado em formar bacharéis que possam atuar em duas grandes áreas: Sistemas Multimídia e Mídias Digitais, contribuindo para o desenvolvimento de um polo tecnológico no Ceará."
        };

        // Escolhe uma resposta aleatória
        return respostas[random.nextInt(respostas.length)];
    }
}
