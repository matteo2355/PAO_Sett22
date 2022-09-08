package statistics;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import exceptions.WrongValueException;

public class FilterOfFeels_like_min {
	
	Statistics statistic= new Statistics();
	
	public JSONArray allTime (ArrayList<String> cities, String value) throws WrongValueException {
			
			JSONArray array = new JSONArray();
			
			ArrayList<JSONObject> weather = new ArrayList<JSONObject>();
			ArrayList<Double> Feels_Like_min = new ArrayList<Double>();
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
				double fl_min = object.getDouble("Temp_Feels_like Min");
				Feels_Like_min.add(fl_min);
				
				JSONObject obj = new JSONObject();
				obj.put("city:", cities.get(i)); 
				obj.put("feels_like_min:",fl_min);
				objects.add(obj);
				array.put(obj);
				
				if(value.equals("max")) {
					
						if(fl_min>request1) {
							request1 = fl_min;
							names = new ArrayList<String>();
							names.add(cities.get(i));
						}
						else if(fl_min==request1) {
							names.add(cities.get(i));
						}
						i++;
					
				}
				else if(value.equals("min")) {
					
					if(fl_min<request2) {
						request2 = fl_min;
						names = new ArrayList<String>();
						names.add(cities.get(i));
					}
					else if(fl_min==request2) {
						names.add(cities.get(i));
					}
					i++;
				}
				else throw new WrongValueException (value+" è una stringa errata! Devi inserire una stringa tra max oppure min");
					
			}
			
			JSONObject object = new JSONObject();
			
			if(value.equals("max")) {
				object.put("City with max feels_like_min", names);
				object.put("max feels_like", request1);
			}
			else { 
				object.put("City with min feels_like_min value", names);
				object.put("min feels_like", request2);
			}
			
			
			array.put(object);
			
			
			return array;
		}

}
