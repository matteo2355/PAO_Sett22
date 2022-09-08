package statistics;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import exceptions.WrongValueException;

public class FilterOfTemp_max { 
	
	Statistics statistic = new Statistics();
	
public JSONArray allTime (ArrayList<String> cities, String value) throws WrongValueException {
		
		JSONArray array = new JSONArray();
		
		ArrayList<JSONObject> weather = new ArrayList<JSONObject>();
		ArrayList<Double> TempMax = new ArrayList<Double>();
		ArrayList<JSONObject> objects = new ArrayList<JSONObject>();
		ArrayList<String> names = new ArrayList<String>();
		
		Iterator<String> it = cities.iterator();
		
		double request1 = 0;
		double request2 = 100;

		int i = 0;
		
		while(it.hasNext()) {
			JSONObject object = new JSONObject();
			object = statistic.TempAverage(it.next());
			weather.add(object);
			double max = object.getDouble("Temp_Real max");
			TempMax.add(max);
			
			JSONObject obj = new JSONObject();
			obj.put("Name:",cities.get(i));
			obj.put("temp_real_max:",max);
			objects.add(obj);
			array.put(obj);
			
			if(value.equals("max")) {
				
				if(max>request1) {
					request1 = max;
					names = new ArrayList<String>();
					names.add(cities.get(i));
				}
				else if(max==request1) {
					names.add(cities.get(i));
				}
				i++;
				
			}
			else if(value.equals("min")) {
				
				if(max<request2) {
					request2 = max;
					names = new ArrayList<String>();
					names.add(cities.get(i));
				}
				else if(max==request2) {
					names.add(cities.get(i));
				}
				i++;
			}
			else throw new WrongValueException (value+" è una stringa errata! Devi inserire una stringa max oppure min");
				
		}
		
		JSONObject object = new JSONObject();
		
		if(value.equals("max")) {
			object.put("City with max temperature", names);
			object.put("max temperature", request1);
		}
		else { 
			object.put("City with min temperature", names);
			object.put("min temperature", request2);
		}
		
		
		array.put(object);
	
		
		return array;
		
	}
}
