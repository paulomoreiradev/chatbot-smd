package utils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import entities.Curso;
import entities.Disciplina;
import users.Professor;

public class CarregadorDados {
    public static Curso carregarDados() {
        try (InputStream inputStream = CarregadorDados.class.getClassLoader().getResourceAsStream("dados.json")) {
            if (inputStream == null) {
                throw new RuntimeException("Arquivo dados.json não encontrado no classpath.");
            }

            String conteudo = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            JSONObject json = new JSONObject(conteudo);

            // Processar o JSON e criar o objeto Curso
            String nomeCurso = json.getString("curso");

            JSONArray disciplinasJson = json.getJSONArray("disciplinas");
            List<Disciplina> disciplinas = new ArrayList<>();
            for (int i = 0; i < disciplinasJson.length(); i++) {
                JSONObject disciplinaJson = disciplinasJson.getJSONObject(i);
                disciplinas.add(new Disciplina(
                    disciplinaJson.getString("nome"),
                    disciplinaJson.getString("descricao")
                ));
            }

            JSONArray professoresJson = json.getJSONArray("professores");
            List<Professor> professores = new ArrayList<>();
            for (int i = 0; i < professoresJson.length(); i++) {
                JSONObject professorJson = professoresJson.getJSONObject(i);
                professores.add(new Professor(
                    professorJson.getString("nome"),
                    professorJson.getString("areaAtuacao")
                ));
            }

            return new Curso(nomeCurso, disciplinas, professores);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
