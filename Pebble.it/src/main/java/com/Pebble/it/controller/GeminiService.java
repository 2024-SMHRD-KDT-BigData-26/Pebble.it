package com.Pebble.it.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

public class GeminiService {

    private final String apiKey;
    private final String apiUrl;
    private final OkHttpClient client;

    public GeminiService(String apiKey, String apiUrl) {
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
        this.client = new OkHttpClient();
    }

    public String getMarketData(String symbol) throws IOException {
        String endpoint = apiUrl + "/marketdata/" + symbol;

        Request request = new Request.Builder()
                .url(endpoint)
                .header("Authorization", "Bearer " + apiKey)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return Objects.requireNonNull(response.body()).string();
        }
    }

    public static void main(String[] args) {
        try {
            // Load settings
            String apiKey = "AIzaSyBSF-N2LDT7EFtvQXdc-Ujr6GnOHjFUIys";
            String apiUrl = "https://api.gemini.com/v1";

            GeminiService geminiService = new GeminiService(apiKey, apiUrl);
            String marketData = geminiService.getMarketData("btcusd");
            JsonObject json = JsonParser.parseString(marketData).getAsJsonObject();

            System.out.println("Market Data: " + json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}