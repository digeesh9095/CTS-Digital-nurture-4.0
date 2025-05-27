import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class GitHubApiClient {
    public static void main(String[] args) {
        String url = "https://api.github.com/users/octocat";

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Accept", "application/vnd.github.v3+json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body:\n" + response.body());

            // Parse JSON using Gson
            Gson gson = new Gson();
            JsonObject json = gson.fromJson(response.body(), JsonObject.class);

            System.out.println("\nParsed Data:");
            System.out.println("Login: " + json.get("login").getAsString());
            System.out.println("Public Repos: " + json.get("public_repos").getAsInt());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}