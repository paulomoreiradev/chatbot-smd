package entities;

import java.util.List;

public class Periodo {
    private int ordem; // 1, 2, 3, etc.
    private List<Disciplina> disciplinas;

    public Periodo(int ordem, List<Disciplina> disciplinas) {
        this.ordem = ordem;
        this.disciplinas = disciplinas;
    }

    public int getOrdem() {
        return ordem;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }
}
