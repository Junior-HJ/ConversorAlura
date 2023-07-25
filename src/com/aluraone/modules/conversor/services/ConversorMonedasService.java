/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aluraone.modules.conversor.services;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

public class ConversorMonedasService {

    private final OkHttpClient client;

    public ConversorMonedasService() {
        client = new OkHttpClient();
    }

    public String sendRequestExchangeRates(String apiUrl) throws IOException {
        Request request = new Request.Builder()
            .url(apiUrl)
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Error al realizar la solicitud. Código de respuesta: " + response.code());
            }

            return response.body() != null ? response.body().string() : "";
        }
    }

    public JSONObject getExchangeRates() throws IOException {
        String apiUrl = "http://api.exchangeratesapi.io/v1/latest?access_key=60c47e6459f60f070cf52e898adfc7cb&format=1";
        String apiResponse = sendRequestExchangeRates(apiUrl);
        JSONObject jsonResponse = new JSONObject(apiResponse);
        // Obtener los rates del JSON
        JSONObject rates = jsonResponse.getJSONObject("rates");
        return rates;
    }
}