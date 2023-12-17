package edu.hw6.Task5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class HackerNews {
    public static long[] hackerNewsTopStories() {
        try {
            HttpRequest
                request =
                HttpRequest.newBuilder(new URI("https://hacker-news.firebaseio.com/v0/topstories.json")).build();

            HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            client.close();
            String s = response.body();
            s = s.replace("[", "").replace("]", "");
            return Arrays.stream(s.split(",")).mapToLong(Long::parseLong).toArray();
        } catch (URISyntaxException | IOException | InterruptedException ex) {
            return new long[] {};
        }
    }

    public static String news(String id) {
        try {
            URL url = URI.create("https://hacker-news.firebaseio.com/v0/item/" + id + ".json").toURL();
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpURLConnection.getInputStream();
                String s =
                    new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
                String format = "(?<=\"title\":\").*(?=\",\"type\")";
                Pattern pattern = Pattern.compile(format);
                Matcher matcher = pattern.matcher(s);
                if (matcher.find()) {
                    s = matcher.group();
                    return s;
                }
                return null;
            }
        } catch (
            IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private HackerNews() {
    }
}
