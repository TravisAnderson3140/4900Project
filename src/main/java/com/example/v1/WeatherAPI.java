package com.example.v1;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class WeatherAPI {
    private static final String API_URL = "http://api.weatherapi.com/v1/";
    private static final String API_KEY = "YOUR-API-KEY-HERE";
    public static WeatherData getData(String keyword) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest GetRequest = HttpRequest.newBuilder()
                .header("key", API_KEY)
                .uri(URI.create(API_URL + "current.json" + "?key" + API_KEY + "&q=" + keyword + "&aqi=no"))
                .build();

        HttpResponse<String> response = client.send(GetRequest, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        WeatherData weatherData = new WeatherData();
        weatherData = gson.fromJson(response.body(), weatherData.getClass());
        return weatherData;
    }
    public static WeatherForecast getForecast(String keyword) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest GetRequest = HttpRequest.newBuilder()
                .header("key", API_KEY)
                .uri(URI.create(API_URL + "forecast.json" + "?key" + API_KEY + "&days=3&q=" + keyword + "&aqi=no"))
                .build();

        HttpResponse<String> response = client.send(GetRequest, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        WeatherForecast weatherForecast = new WeatherForecast();
        weatherForecast = gson.fromJson(response.body(), weatherForecast.getClass());
        return weatherForecast;
    }
}