package edu.project3.Sources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class HttpSource implements LogSource {
    @Override
    public String getData(String path) {
        try {
            URL url = URI.create(path).toURL();
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String fileName = Paths.get(url.getPath()).getFileName().toString();
                MasterOfSources.paths.add(fileName);
                InputStream inputStream = httpURLConnection.getInputStream();
                return new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
            }
        } catch (IOException e) {
            throw new RuntimeException("что-то не так с http", e);
        }
        return "";
    }
}
