package statistics;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import exceptions.WrongValueException;


public class FilterOfFeels_like_max {
	
	Statistics statistic= new Statistics();
	
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
