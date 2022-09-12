package com.progetto.temperatureapp.service;

import org.json.JSONArray;
import org.json.JSONObject;

import com.progetto.temperatureapp.model.City;
/**
*Questa classe contiene il metodo che restituisce il JSONObject corrispondente alla città passata in ingresso.
*/

public class ToJSON {
City city = new City();
	/**
	*Questo metodo restituisce il JSONObject corrispondente alla città passata in ingresso.
	*/

public JSONObject toJSON(City city) {
	JSONObject object = new JSONObject();
	
	object.put("name", city.getName());

	JSONArray arr = new JSONArray();
	
	for(int i=0; i<(city.getVector()).size(); i++) {
		JSONObject weather = new JSONObject();
		weather.put("data", (city.getVector()).get(i).getData());
		weather.put("main", (city.getVector()).get(i).getMain());
		weather.put("temp", (city.getVector()).get(i).getTemp());
        weather.put("temp_max", (city.getVector()).get(i).getTemp_max());
		weather.put("temp_min", (city.getVector()).get(i).getTemp_min());
		weather.put("feels_like", (city.getVector()).get(i).getFeels_like());
		arr.put(weather);
	}
	
	
	object.put("Weather", arr);
	
	return object;
}
}
