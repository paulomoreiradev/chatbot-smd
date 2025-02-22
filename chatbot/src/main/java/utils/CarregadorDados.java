package utils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import entities.Curso;
import entities.Disciplina;
import entities.Periodo;
import users.Professor;

public class CarregadorDados {
    public static Curso carregarDados(String caminhoArquivo) {
        try (InputStream inputStream = CarregadorDados.class.getClassLoader().getResourceAsStream(caminhoArquivo)) {
            if (inputStream == null) {
                throw new RuntimeException("Arquivo " + caminhoArquivo + " não encontrado no classpath.");
            }

            String conteudo = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            JSONObject json = new JSONObject(conteudo);

            String nomeCurso = json.getString("curso");

            // Carregar períodos e disciplinas
            JSONArray periodosJson = json.getJSONArray("periodos");
            List<Periodo> periodos = new ArrayList<>();
            for (int i = 0; i < periodosJson.length(); i++) {
                JSONObject periodoJson = periodosJson.getJSONObject(i);
                int ordem = periodoJson.getInt("ordem");

                JSONArray disciplinasJson = periodoJson.getJSONArray("disciplinas");
                List<Disciplina> disciplinas = new ArrayList<>();
                for (int j = 0; j < disciplinasJson.length(); j++) {
                    JSONObject disciplinaJson = disciplinasJson.getJSONObject(j);

                    // Carregar professores da disciplina
                    JSONArray professoresJson = disciplinaJson.getJSONArray("professores");
                    List<Professor> professores = new ArrayList<>();
                    for (int k = 0; k < professoresJson.length(); k++) {
                        JSONObject professorJson = professoresJson.getJSONObject(k);
                        professores.add(new Professor(
                            professorJson.getString("nome"),
                            professorJson.getString("areaAtuacao")
                        ));
                    }

                    disciplinas.add(new Disciplina(
                        disciplinaJson.getString("nome"),
                        disciplinaJson.getString("descricao"),
                        professores
                    ));
                }

                periodos.add(new Periodo(ordem, disciplinas));
            }

            return new Curso(nomeCurso, periodos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
