import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // fazer conexão HTTP e buscar os Top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        // HttpClient cliente é a mesma coisa que escrever var client (as versões mais novas já entendem o tipo dessa variável)
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair os dados que interessam (titulo, poster, classificação)

        JsonParser parser = new JsonParser();
        // estrutura associativa - no java chama Map
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        System.out.println(listaDeFilmes.size());

        // exibir e manipular dados
        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imdbRating"));
        }

        // desafios:
        // pintar terminal
        // endpoint: https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json
        // tv: https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json
        // tvPop: https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json
    
    }
}
