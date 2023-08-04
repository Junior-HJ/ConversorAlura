/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aluraone.modules.conversor.services;

import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleUnaryOperator;

public final class TemperaturaConverterService {

    private final Map<String, DoubleUnaryOperator> conversions;

    public TemperaturaConverterService() {
	conversions = new HashMap<>();
	buildConvertionsMap();
    }

    public void buildConvertionsMap() {
	conversions.put("Celsius to Fahrenheit", celsius -> (celsius * 9 / 5) + 32);
	conversions.put("Celsius to Kelvin", celsius -> celsius + 273.15);
	conversions.put("Celsius to Rankine", celsius -> (celsius + 273.15) * 9 / 5);
	conversions.put("Fahrenheit to Celsius", fahrenheit -> (fahrenheit - 32) * 5 / 9);
	conversions.put("Fahrenheit to Kelvin", fahrenheit -> (fahrenheit + 459.67) * 5 / 9);
	conversions.put("Fahrenheit to Rankine", fahrenheit -> fahrenheit + 459.67);
	conversions.put("Kelvin to Celsius", kelvin -> kelvin - 273.15);
	conversions.put("Kelvin to Fahrenheit", kelvin -> (kelvin * 9 / 5) - 459.67);
	conversions.put("Kelvin to Rankine", kelvin -> kelvin * 9 / 5);
	conversions.put("Rankine to Celsius", rankine -> (rankine - 491.67) * 5 / 9);
	conversions.put("Rankine to Fahrenheit", rankine -> rankine - 459.67);
	conversions.put("Rankine to Kelvin", rankine -> rankine * 5 / 9);
    }
    
    public double convertTemperature(double inputTemp, String conversionKey) {
	return conversions.get(conversionKey).applyAsDouble(inputTemp);
    }
}
