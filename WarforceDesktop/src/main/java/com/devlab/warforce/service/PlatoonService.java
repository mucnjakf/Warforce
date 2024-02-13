package com.devlab.warforce.service;

import com.devlab.warforce.model.CreatePlatoon;
import com.devlab.warforce.model.Platoon;
import com.devlab.warforce.model.UpdatePlatoon;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class PlatoonService {

    private final String URL = "http://localhost:8082/api/";

    public List<Platoon> getPlatoons() throws URISyntaxException, IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        Gson gson = new Gson();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(URL + "platoons"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return Arrays.stream(gson.fromJson(response.body(), Platoon[].class)).toList();
    }

    public Platoon createPlatoon(CreatePlatoon createPlatoon) throws URISyntaxException, IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        Gson gson = new Gson();

        String createPlatoonJson = gson.toJson(createPlatoon);

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(new URI(URL + "platoons"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(createPlatoonJson))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(response.body(), Platoon.class);
    }

    public Platoon updatePlatoon(String platoonId, UpdatePlatoon updatePlatoon) throws URISyntaxException, IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        Gson gson = new Gson();

        String updatePlatoonJson = gson.toJson(updatePlatoon);

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(new URI(URL + "platoons/" + platoonId))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(updatePlatoonJson))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(response.body(), Platoon.class);
    }

    public void deletePlatoon(String platoonId) throws URISyntaxException, IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(new URI(URL + "platoons/" + platoonId))
                .DELETE()
                .build();

        httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
