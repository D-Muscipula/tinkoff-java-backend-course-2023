package edu.hw6;

import edu.hw6.Task5.HackerNews;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task5Test {
    @Test void hackerNewsTopStoriesTest() {
        try {
            URL url = URI.create("https://hacker-news.firebaseio.com/v0/topstories.json").toURL();
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                Assertions.assertTrue(HackerNews.hackerNewsTopStories().length != 0);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test void newsTest() {
        try {
            URL url = URI.create("https://hacker-news.firebaseio.com/v0/item/" + 38290145 + ".json").toURL();
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                Assertions.assertEquals("The real realtime preemption end game", HackerNews.news("38290145"));
            }
        } catch (IOException ignored) {

        }
    }

}
