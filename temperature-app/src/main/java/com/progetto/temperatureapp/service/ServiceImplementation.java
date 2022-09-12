package com.progetto.temperatureapp.service;


import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;


import com.progetto.temperatureapp.model.City;
import com.progetto.temperatureapp.model.Weather;

/**
 * Questa classe è l'implementazione dell'interfaccia Service.
 * Contiene i metodi che vengono utilizzati dal controller.
 */
@Service

public class ServiceImplementation {
private String api_key = "c70c2a5973a0943bc5253a4e40eb46ca";
	
	/** Questo metodo va a prendere da OpenWeather l'attuale meteo di una città.
      * @param city è il nome della città di cui si vuole conoscere le previsioni meteo.
      * @return un JSONObject contenente le previsioni meteo complete.
      */

public JSONObject getWeatherOfCity(String city) {
		
		JSONObject obj;
		String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid="+api_key;
		
		RestTemplate rt = new RestTemplate();
		
		obj = new JSONObject(rt.getForObject(url, String.class));
		
		return obj;
		
	}

/**
 * Questo metodo utilizza getWeatherOfCity per andare a selezionare le previsioni meteo ristrette (temperatura, temperatura
 * massima, minima e percepita).
 * @param name è il nome della città di cui si vogliono conoscere le previsioni ristrette.
 * @return un oggetto di tipo City che contiene tutte le informazioni richieste e anche le informazioni sulla città.
 */

public City getRistrictWeatherOfCityfromApi(String name) {
	
	JSONObject object = getWeatherOfCity(name);
	
	City city = new City(name);
	
	JSONArray weatherArray = object.getJSONArray("list");
	JSONObject counter;
	
	Vector<Weather> vector = new Vector<Weather>(weatherArray.length());
	
	
	try {
		
		
		for (int i = 0; i<weatherArray.length(); i++) {
			
			Weather weather = new Weather();
			counter = weatherArray.getJSONObject(i);
			weather.setMain(counter.getString("main"));
			JSONObject objectMain = counter.getJSONObject("main");
			weather.setTemp(objectMain.getDouble("temp"));
			weather.setTemp_max(objectMain.getDouble("temp_max"));
			weather.setTemp_min(objectMain.getDouble("temp_min"));
			weather.setFeels_like(object.getDouble("feels_like"));
			weather.setData(counter.getString("dt"));
			vector.add(weather); 
	
		}

	} catch(Exception e) {
		e.printStackTrace();
	}
	
	
	city.setVector(vector);
	
	return city;
	
}
/**
         * Questo metodo utilizza getWeatherOfCity per andare a selezionare le previsioni meteo ristrette (temperatura, temperatura
		 * massima, minima e percepita) e restituisce un JSONArray.
		 * @param name è il nome della città di cui si vogliono conoscere le previsioni ristrette.
		 * @return un JSONArray che contiene tutte le informazioni richieste e anche le informazioni sulla città.
		 */
public JSONArray getRistrictWeatherfromApiJSON(String name) {

	JSONObject object = getWeatherOfCity(name);
	JSONArray array = new JSONArray();
		
		JSONArray weatherArray = object.getJSONArray("list");
		JSONObject support;
		double temp;
		double temp_max;
		double temp_min;
		double feels_like;
		String data;
		
		for (int i = 0; i<weatherArray.length(); i++) {
			
			support = weatherArray.getJSONObject(i);
			temp = (double) support.get("temp");
			temp_max = (double) support.get("temp_max");
			temp_min = (double) support.get("temp_min");
			feels_like = (double) support.get("feels_like");
			data = (String) support.get("dt");
			JSONObject toReturn = new JSONObject();
			toReturn.put("temp", temp);
			toReturn.put("temp_max", temp_max);
			toReturn.put("temp_min", temp_min);
			toReturn.put("feels_like", feels_like);
			toReturn.put("data", data);
			array.put(toReturn);
			
		}

	return array;
	
}

/** Questo metodo richiama getRistrictWeatherOfCityfromApiJSON(String name) e serve per salvare le previsioni meteo ogni ora.
* @param cityName è il nome della città
* @return una stringa contenente il path del file salvato.
*/
public String saveEveryHour(String cityName) {
	
	String path = System.getProperty("user.dir") + "/" + cityName + "HourlyReport.txt";
	
	 final File file = new File(path);
	//ripropone il run ogni ora di tempo
	ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	scheduler.scheduleAtFixedRate(new Runnable() {
	    @Override
	    public void run() {
	    	
	    	JSONArray weather = new JSONArray();
	    	weather = getRistrictWeatherfromApiJSON(cityName);
	    	
	    	JSONObject actualWeather = new JSONObject();
	    	actualWeather= weather.getJSONObject(0);
	    	
	    			try{
	    			    if(!file.exists()) {
	    			        file.createNewFile();
	    			    }

	    			    FileWriter fileWriter = new FileWriter(file, true);
	    				
	    				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	    			    bufferedWriter.write(actualWeather.toString());
	    			    bufferedWriter.write("\n");
	    			    
	    			    bufferedWriter.close();
	    			    
	    			} catch(IOException e) {
	    			    System.out.println(e);
	    			}
	    	
	    }
	}, 0, 3, TimeUnit.HOURS);
	
	
	return "Il file è stato salvato in " + path;
	
}


}
