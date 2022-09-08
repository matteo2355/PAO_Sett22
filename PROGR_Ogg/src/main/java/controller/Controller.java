package controller;



import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exceptions.ParameterException;
import exceptions.WrongValueException;
import model.City;
import service.Service;
import service.ToJSON;
import statistics.Filter;
import statistics.Statistics;


@RestController

public class Controller {
	@Autowired 
	Service service;
	Statistics statistic= new Statistics();
	
	@GetMapping(value="/weather")
public ResponseEntity<Object> getCityWeather(@RequestParam String cityName) {
		
		City city = service.getRistrictWeatherOfCityfromApi(cityName);
		
		JSONObject obj = new JSONObject();
		ToJSON tojson = new ToJSON();
		
		obj = tojson.toJSON(city);
		
		
		return new ResponseEntity<Object> (obj.toString(), HttpStatus.OK);
    }
	
	@GetMapping(value="/saveEveryHour")
    public ResponseEntity<Object> saveHour(@RequestParam String cityName)  {
		
		String path = service.saveEveryHour(cityName);
		
		return new ResponseEntity<Object> (path, HttpStatus.OK);
	} 
	
	@PostMapping(value="/stats")
    public ResponseEntity<Object> stats(@RequestParam String cityName)  {
		
		
		
		return new ResponseEntity<Object> (statistic.TempAverage(cityName).toString(), HttpStatus.OK);
	}
	
	@PostMapping("/filters")
	public ResponseEntity<Object> filters(@RequestBody String body) throws  WrongValueException, ParameterException {
		
		JSONObject object = new JSONObject(body);
        JSONArray array = new JSONArray();

 

        array = object.getJSONArray("cities");
        
        ArrayList<String> cities = new ArrayList<String>(array.length());
        
        for(int i=0; i<array.length();i++) {
            JSONObject obj = new JSONObject();
            obj = array.getJSONObject(i);
            cities.add(obj.getString("name"));
        }
        
        String param = object.getString("param");
        String value = object.getString("value");
       
		
        Filter filter;
		filter = new Filter(cities,param,value);
		
		try {
        	return new ResponseEntity<Object>(filter.analyze().toString(),HttpStatus.OK);
        

	        }
		catch(WrongValueException e) {
        	return new ResponseEntity<Object>(e.getMex(),HttpStatus.BAD_REQUEST);
        }
		catch(ParameterException e) {
        	return new ResponseEntity<Object>(e.getMex(),HttpStatus.BAD_REQUEST);
        }
		
		
	}
}


