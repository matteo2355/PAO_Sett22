package com.progetto.temperatureapp.statistics;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.progetto.temperatureapp.exceptions.WrongValueException;

/**
* Questa classe contiene i metodi per il filtraggio rispetto alla temperatura percepita massima.
*/

public class FilterOfFeels_like_max {
Statistics statistic= new Statistics();
	
/**
 *  Questo metodo calcola la media della temperatura percepita massima delle città passate in ingresso e
 * filtra rispetto al value. Restituisce un JSONArray contenente JSONObject che rappresentano le città e le relative 
 * temperature percepite. 
 * A seconda di value c'è un JSONObject che rappresenta la città con max/min valore di temperatura percepita massima
 * 
 * @param cities rappresenta le città con cui si vuole fare la statistica e il filtraggio
 * @param value rappresenta il valore con cui si vuole fare il filtraggio.
 * @return JSONArray come descritto sopra. 
 * @throws WrongValueException se viene inserita una stringa non ammessa, cioè una stringa che non sia max o min.
 */
	public JSONArray allTime (ArrayList<String> cities, String value) throws WrongValueException {
			
			JSONArray array = new JSONArray();
			
			ArrayList<JSONObject> weather = new ArrayList<JSONObject>();
			ArrayList<Double> Feels_Like_max = new ArrayList<Double>();
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
				double fl_max = object.getDouble("Temp_Feels_like Max");
				Feels_Like_max.add(fl_max);
				
				JSONObject obj = new JSONObject();
				obj.put("city:", cities.get(i)); 
				obj.put("feels_like_max:",fl_max);
				objects.add(obj);
				array.put(obj);
				
				if(value.equals("max")) {
					
						if(fl_max>request1) {
							request1 = fl_max;
							names = new ArrayList<String>();
							names.add(cities.get(i));
						}
						else if(fl_max==request1) {
							names.add(cities.get(i));
						}
						i++;
					
				}
				else if(value.equals("min")) {
					
					if(fl_max<request2) {
						request2 = fl_max;
						names = new ArrayList<String>();
						names.add(cities.get(i));
					}
					else if(fl_max==request2) {
						names.add(cities.get(i));
					}
					i++;
				}
				else throw new WrongValueException (value+" è una stringa errata! Devi inserire una stringa tra max oppure min");
					
			}
			
			JSONObject object = new JSONObject();
			
			if(value.equals("max")) {
				object.put("City with max feels_like max_value", names);
				object.put("max feels_like", request1);
			}
			else { 
				object.put("City with min feels_like max_value", names);
				object.put("min feels_like", request2);
			}
			
			
			array.put(object);
			
			
			return array;
		}
}
