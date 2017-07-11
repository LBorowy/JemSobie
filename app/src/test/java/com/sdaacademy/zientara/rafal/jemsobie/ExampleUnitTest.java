package com.sdaacademy.zientara.rafal.jemsobie;

import com.sdaacademy.zientara.rafal.jemsobie.models.Restaurant;
import com.sdaacademy.zientara.rafal.jemsobie.service.RestaurantsApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {
    private ArrayList<Restaurant> feedsList;

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void connectionTest() throws Exception {
        String urlString = RestaurantsApi.ENDPOINT + "restaurants";
        Integer result = 0;
        HttpURLConnection urlConnection;
        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            int statusCode = urlConnection.getResponseCode();

            // 200 represents HTTP OK
            if (statusCode == 200) {
                BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    response.append(line);
                }
                parseResult(response.toString());
                result = 1; // Successful
            } else {
                result = 0; //"Failed to fetch data!";
            }
        } catch (Exception e) {
            System.out.printf("Error: " + e.getMessage());
            e.printStackTrace();
        }
        Assert.assertTrue(feedsList != null);
        Assert.assertTrue(feedsList.size() > 0);
        Assert.assertTrue(result == 1);
    }

    private void parseResult(String result) {
        try {
            JSONArray posts = new JSONArray(result);
            feedsList = new ArrayList<Restaurant>();

            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);
                Restaurant item = new Restaurant();
                item.setName(post.optString("name"));
                item.setComment(post.optString("comment"));
                feedsList.add(item);
            }
        } catch (JSONException e) {
            System.out.printf("ErrorJSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

}