package com.devlab.warforce.service;

import com.devlab.warforce.model.CreateOperator;
import com.devlab.warforce.model.Operator;
import com.devlab.warforce.model.UpdateOperator;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class OperatorService {

    private final String URL = "http://localhost:8082/api/";

    public List<Operator> getOperators() throws URISyntaxException, IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        Gson gson = new Gson();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(URL + "operators"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return Arrays.stream(gson.fromJson(response.body(), Operator[].class)).toList();
    }

    public Operator createOperator(CreateOperator createOperator) throws URISyntaxException, IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        Gson gson = new Gson();

        String createOperatorJson = gson.toJson(createOperator);

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(new URI(URL + "operators"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(createOperatorJson))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(response.body(), Operator.class);
    }

    public Operator updateOperator(String operatorId, UpdateOperator updateOperator) throws URISyntaxException, IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        Gson gson = new Gson();

        String updateOperatorJson = gson.toJson(updateOperator);

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(new URI(URL + "operators/" + operatorId))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(updateOperatorJson))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(response.body(), Operator.class);
    }

    public void deleteOperator(String operatorId) throws IOException, InterruptedException, URISyntaxException {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(new URI(URL + "operators/" + operatorId))
                .DELETE()
                .build();

        httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
