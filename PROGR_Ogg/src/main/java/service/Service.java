package service;

import org.json.JSONArray;
import org.json.JSONObject;

import model.City;

public interface Service {
   
	public abstract JSONObject getWeatherOfCity(String city);
	public abstract City getRistrictWeatherOfCityfromApi(String name);
	public abstract JSONArray getRistrictWeatherfromApiJSON(String name);
	public abstract String saveEveryHour(final String cityName);
	public abstract JSONArray readHistory(String name );
}
