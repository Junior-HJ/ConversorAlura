/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aluraone.modules.conversor.services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.json.JSONObject;

public class ConversorMonedasService {

    private String fromCurrency;
    private String toCurrency;
    private double amount;

    public ConversorMonedasService() {
    }

    public String sendRequestExchangeRates(String apiUrl) throws IOException {
	URL url = new URL(apiUrl);
	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	connection.setRequestMethod("GET");

	int responseCode = connection.getResponseCode();
	if (responseCode == HttpURLConnection.HTTP_OK) {
	    try (Scanner scanner = new Scanner(connection.getInputStream(), "UTF-8")) {
		scanner.useDelimiter("\\A");
		return scanner.hasNext() ? scanner.next() : "";
	    }
	} else {
	    throw new IOException("Error al realizar la solicitud. Código de respuesta: " + responseCode);
	}
    }

    public JSONObject getExchangeSymbols() throws IOException {
	String apiUrl = "https://api.exchangerate.host/symbols";
	String apiResponse = sendRequestExchangeRates(apiUrl);
	JSONObject jsonResponse = new JSONObject(apiResponse);
	// Obtener los rates del JSON
	JSONObject symbols = jsonResponse.getJSONObject("symbols");
	return symbols;
    }

    public void setSelectedCurrencies(String selectedCurrencyDe, String selectedCurrencyA) {
	this.fromCurrency = selectedCurrencyDe;
	this.toCurrency = selectedCurrencyA;
    }

    public void setAmount(double amount) {
	this.amount = amount;
    }

    public double convertirMonedas() throws IOException {
	if (this.fromCurrency != null && this.toCurrency != null) {
	    String apiUrl = "https://api.exchangerate.host/convert?from=" + this.fromCurrency + "&to=" + this.toCurrency + "&amount=" + this.amount;
	    String apiResponse = sendRequestExchangeRates(apiUrl);
	    JSONObject jsonResponse = new JSONObject(apiResponse);
	    return jsonResponse.getDouble("result");
	} else {
	    throw new IOException("Monedas no seleccionadas.");
	}
    }

    public List<JSONObject> obtenerListaOrdenadaDeSymbols(JSONObject symbols) throws IOException {
	List<JSONObject> symbolList = new ArrayList<>(symbols.length());
	for (Object value : symbols.toMap().values()) {
	    symbolList.add(new JSONObject((Map<?, ?>) value));
	}
	symbolList.sort(Comparator.comparing(json -> json.getString("description")));
	return symbolList;
    }
}
