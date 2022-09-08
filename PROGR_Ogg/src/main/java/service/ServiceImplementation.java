package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import exceptions.CityException;
import exceptions.StringException;
import model.City;
import model.Weather;
import statistics.Statistics;

public class ServiceImplementation {
	
private String api_key = "c70c2a5973a0943bc5253a4e40eb46ca";
	
	//Questo metodo va a prendere da OpenWeather l'attuale meteo di una città.
	
public JSONObject getWeatherOfCity(String city) {
		
		JSONObject obj;
		String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid="+api_key;
		
		RestTemplate rt = new RestTemplate();
		
		obj = new JSONObject(rt.getForObject(url, String.class));
		
		return obj;
		
	}



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


public String saveEveryHour(final String cityName) {
	
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

public JSONArray readHistory(String name ) throws IOException {
	
	
	String path = System.getProperty("user.dir") + "/weather/" + name +".txt";
	
	String all;
		
	BufferedReader br = new BufferedReader(new FileReader(path));
	
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    all = sb.toString();
		} finally {
		    br.close();
		}
			
	
		JSONArray array = new JSONArray(all);

		return array;
		
}

public JSONArray readTemperatureHistory(ArrayList<String> cities) 
		throws StringException, CityException,  IOException {
	
	Iterator<String> it1 = cities.iterator();
	JSONArray weatherInfo = new JSONArray();
	
	
	for(int i=0; i<cities.size(); i++) {
		if(cities.get(i).isEmpty())
			throw new StringException ("Inserisci una citta!!");
		else if(!(cities.get(i).equals("Ancona") || cities.get(i).equals("Bologna") || cities.get(i).equals("Milano") || cities.get(i).equals("Lecce") || cities.get(i).equals("Cagliari") ))
			throw new CityException(cities.get(i) + " non va bene. Devi scegliere tra: \"Ancona\", \"Bologna\", \"Milano\", \"Lecce\" e \"Cagliari\".");
	  }
	
	
	while(it1.hasNext()) {
		
		/*JSONArray array = new JSONArray();
		array = readHistory(it1.next());*/
		Statistics stats= new Statistics();
		
		weatherInfo.put(stats.TempAverage(it1.next()));
		
	  } 
	
	return weatherInfo;
   }
}

