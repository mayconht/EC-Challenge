package com.ecore.challenge.config;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Helper {

    public static JSONObject retrieveJson(final String urlString) throws IOException, JSONException {
        final StringBuilder result = new StringBuilder();
        final URL url = new URL(urlString);

        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        return new JSONObject(result.toString());
    }

    public static List<JSONObject> convertJsonToList(final JSONArray jarray) throws JSONException {
        final List<JSONObject> result = new ArrayList<>();
        for (int i = 0; i < jarray.length(); i++) {
            result.add(jarray.getJSONObject(i));
        }
        return result;
    }
}
