package com.progetto.temperatureapp.service;

import org.json.JSONArray;
import org.json.JSONObject;

import com.progetto.temperatureapp.model.City;

/**
* Questa è l'interfaccia di ServiceImplementation che richiama i metodi del controller.
*/

public interface Service {
	
	public abstract JSONObject getWeatherOfCity(String city);
	public abstract City getRistrictWeatherOfCityfromApi(String name);
	public abstract JSONArray getRistrictWeatherfromApiJSON(String name);
	public abstract String saveEveryHour(final String cityName);
	
}
