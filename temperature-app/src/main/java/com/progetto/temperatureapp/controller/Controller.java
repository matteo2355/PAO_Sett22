package com.progetto.temperatureapp.controller;


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


import com.progetto.temperatureapp.exceptions.ParameterException;

import com.progetto.temperatureapp.exceptions.WrongValueException;
import com.progetto.temperatureapp.model.City;
import com.progetto.temperatureapp.service.Service;
import com.progetto.temperatureapp.service.ToJSON;
import com.progetto.temperatureapp.statistics.Filter;
import com.progetto.temperatureapp.statistics.Statistics;

/** Questa classe gestisce tutte le chiamate al server possibili
 * 
 */
@RestController
public class Controller {
	@Autowired
	Service service;
	Statistics statistic= new Statistics();
	
	/**
	 * Rotta di tipo GET che mostra le informazioni sull' attuale condizione meterologica della città.
	 * 
	 * @param cityName rappresenta la città di cui si richiedono le informazioni.
	 * @return le condizioni meteo della città richiesta.
	 */
	
	@GetMapping(value="/weather")
public ResponseEntity<Object> getCityWeather(@RequestParam String cityName) {
		
		City city = service.getRistrictWeatherOfCityfromApi(cityName);
		
		JSONObject obj = new JSONObject();
		ToJSON tojson = new ToJSON();
		
		obj = tojson.toJSON(city);
		
		
		return new ResponseEntity<Object> (obj.toString(), HttpStatus.OK);
    }
	
	/*
	 * Rotta di tipo GET che salva ogni ora le condizioni meterologiche della città inserita dall'utente.
	 * 
	 * @param cityName rappresenta la città della quale si richiede di salvare le informazioni.
	 * @return il path dove viene salvato il file.
	 */
	
	@GetMapping(value="/saveEveryHour")
    public ResponseEntity<Object> saveHour(@RequestParam String cityName)  {
		
		String path = service.saveEveryHour(cityName);
		
		return new ResponseEntity<Object> (path, HttpStatus.OK);
	} 
	
	/**
	 * Rotta di tipo POST che mostra la massima, minima, media e la varianza della temperatura reale  
	 * e la massima, minima, media e la varianza della temperatura percepita.
	 * @param cityName è una String come sopra indicato.
	 * @return il JSONObject che contiene la statistica richiesta.
	 */
	
	@PostMapping(value="/stats")
    public ResponseEntity<Object> stats(@RequestParam String cityName)  {
		
		
		
		return new ResponseEntity<Object> (statistic.TempAverage(cityName).toString(), HttpStatus.OK);
	}
	
	/**
	 * Rotta di tipo POST che filtra le statistiche in base all'informazione che si vuole richiedere.
	 * L'utente deve inserire un JSONObject di questo tipo:
	 * 
	 * {
     *     "cities": [
     *        {
     *          "name": "Ancona"
     *        },
     *        {
     *          "name": "Lecce"
     *        },
     *        {
     *          "name": "cagliari"
     *        }
     *      ],
     *     "param": "temp_max",
     *     "value": "max",
     *     
     *  }
	 * 
	 * a seconda del "param"(temp_max o min o feels_like_max o min) di cui vuole conoscere quale delle
	 *  città abbia la media,la massima, la minima e la varianza più alta o più bassa(a seconda che "value" sia max o min).* 
	 * @param body è un JSONObject come sopra indicato.
	 * @return il JSONArray che contiene tanti JSONObject quante sono le città specificate nella richiesta
	 *         ognuno dei quali contiene il nome della città e il valore del "param" indicato. 
	 * @throws WrongValueException se viene inserita una stringa errata per value.
	 * @throws ParameterException se viene inserita una stringa errata per param.
	 */
	
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
        	return new ResponseEntity<Object>(filter.examine().toString(),HttpStatus.OK);
        

	        }
		catch(WrongValueException e) {
        	return new ResponseEntity<Object>(e.getMex(),HttpStatus.BAD_REQUEST);
        }
		catch(ParameterException e) {
        	return new ResponseEntity<Object>(e.getMex(),HttpStatus.BAD_REQUEST);
        }
		
		
	}
	
	
}
