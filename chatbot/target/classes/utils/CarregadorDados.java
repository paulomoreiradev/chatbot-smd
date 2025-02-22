package utils;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CarregadorDados {
    public static Curso carregarDados(String caminhoArquivo) {
        try {
            String conteudo = new String(Files.readAllBytes(Paths.get(caminhoArquivo)));
            JSONObject json = new JSONObject(conteudo);

            String nomeCurso = json.getString("curso");

            // Carregar disciplinas
            JSONArray disciplinasJson = json.getJSONArray("disciplinas");
            List<Disciplina> disciplinas = new ArrayList<>();
            for (int i = 0; i < disciplinasJson.length(); i++) {
                JSONObject disciplinaJson = disciplinasJson.getJSONObject(i);
                String nomeDisciplina = disciplinaJson.getString("nome");
                String descricaoDisciplina = disciplinaJson.getString("descricao");
                disciplinas.add(new Disciplina(nomeDisciplina, descricaoDisciplina));
            }

            // Carregar professores
            JSONArray professoresJson = json.getJSONArray("professores");
            List<Professor> professores = new ArrayList<>();
            for (int i = 0; i < professoresJson.length(); i++) {
                JSONObject professorJson = professoresJson.getJSONObject(i);
                String nomeProfessor = professorJson.getString("nome");
                String areaAtuacao = professorJson.getString("areaAtuacao");
                professores.add(new Professor(nomeProfessor, areaAtuacao));
            }

            return new Curso(nomeCurso, disciplinas, professores);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
