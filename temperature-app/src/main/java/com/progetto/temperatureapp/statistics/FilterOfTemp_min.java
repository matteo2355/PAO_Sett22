package com.progetto.temperatureapp.statistics;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.progetto.temperatureapp.exceptions.WrongValueException;

/**
* Questa classe implementa FilterStats e contiene i metodi per il filtraggio rispetto alla temperatura minima.
*/

public class FilterOfTemp_min {
Statistics statistic = new Statistics();
	
/**
 * Questo metodo calcola la media della temperatura minima reale delle città passate in ingresso e
 * filtra rispetto al value. Restituisce un JSONArray contenente JSONObject che rappresentano le città e le relative 
 * temperature minime. 
 * A seconda di value c'è un JSONObject che rappresenta la città con max/min valore di temperatura minima reale.
 * 
 * @param cities rappresenta le città con cui si vuole fare la statistica e il filtraggio
 * @param value rappresenta il valore con cui si vuole fare il filtraggio.
 * @return JSONArray come descritto sopra. 
 * @throws WrongValueException se viene inserita una stringa non ammessa, cioè una stringa che non sia max o min.
 */
	
	public JSONArray allTime (ArrayList<String> cities, String value) throws WrongValueException {
		
		JSONArray array = new JSONArray();
		
		ArrayList<JSONObject> weather= new ArrayList<JSONObject>();
		ArrayList<Double> TempMin = new ArrayList<Double>();
		ArrayList<JSONObject> objects = new ArrayList<JSONObject>();
		ArrayList<String> names = new ArrayList<String>();
		
		Iterator<String> it = cities.iterator();
		
		double request1 = 0;
		double request2 = 1000;
	
		int i = 0;
		
		while(it.hasNext()) {
			JSONObject object = new JSONObject();
			object = statistic.TempAverage(it.next());
			weather.add(object);
			double min = object.getDouble("Temp_Real Min");
			TempMin.add(min);
			
			JSONObject obj = new JSONObject();
			obj.put("city:",cities.get(i));
			obj.put("temp_real_min:",min);
			objects.add(obj);
			array.put(obj);
			
			if(value.equals("max")) {
				
				if(min>request1) {
					request1 = min;
					names = new ArrayList<String>();
					names.add(cities.get(i));
				}
				else if(min==request1) {
					names.add(cities.get(i));
				}
				i++;
				
			}
			else if(value.equals("min")) {
				
				if(min<request2) {
					request2 = min;
					names = new ArrayList<String>();
					names.add(cities.get(i));
				}
				else if(min==request2) {
					names.add(cities.get(i));
				}
				i++;
			}
			else throw new WrongValueException (value+" è una stringa errata! Devi inserire una stringa max oppure min");
				
		}
		
		JSONObject object = new JSONObject();
		
		if(value.equals("max") ) {
			object.put("City with max temperature", names);
			object.put("max temperature", request1);
		}
		else { 
			object.put("City with min termperature", names);
			object.put("min temperature", request2);
		}
		
		
		array.put(object);
	
		
		return array;
		
	}
}
