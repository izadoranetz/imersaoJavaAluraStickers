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
        System.out.println("\n" + listaDeFilmes.size() + " Top Movies:");

        // exibir e manipular dados
        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println(ConsoleColors.YELLOW_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD_BRIGHT + filme.get("title") + ConsoleColors.RESET );
            System.out.println(ConsoleColors.WHITE + filme.get("image") + ConsoleColors.RESET );
            System.out.println(ConsoleColors.PURPLE + filme.get("imDbRating") + ConsoleColors.RESET );
        }
        
        // desafios:
        // pintar terminal
        // endpoint: https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json
        // fazer conexão HTTP e buscar os Top 250 filmes
        String url2 = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        URI endereco2 = URI.create(url2);
        // HttpClient cliente é a mesma coisa que escrever var client (as versões mais novas já entendem o tipo dessa variável)
        HttpClient client2 = HttpClient.newHttpClient();
        HttpRequest request2 = HttpRequest.newBuilder(endereco2).GET().build();
        HttpResponse<String> response2 = client2.send(request2, BodyHandlers.ofString());
        String body2 = response2.body();

        List<Map<String, String>> listaDeFilmesPopulares = parser.parse(body2);
        System.out.println("\n" + "Most Popular Movies: ");
        for (Map<String, String> filme : listaDeFilmesPopulares) {
            System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + filme.get("rank") + ConsoleColors.RESET );
            System.out.println(ConsoleColors.YELLOW_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD_BRIGHT + filme.get("title") + ConsoleColors.RESET );
            System.out.println(ConsoleColors.PURPLE + filme.get("crew") + ConsoleColors.RESET );
        }
        // tv: https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json
        // tvPop: https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json
    
    }
}
