package statistics;

import java.util.ArrayList;

import org.json.JSONArray;

import exceptions.ParameterException;
import exceptions.WrongValueException;

public class Filter {
	
	private ArrayList<String> cities = new ArrayList<String>();
	private String param;
	private String value;
	
	public Filter(ArrayList<String> cities, String param, String value) {
		super();
		this.cities = cities;
		this.param = param;
		this.value = value;
		}
	
	
	
	public JSONArray analyze() throws  WrongValueException, ParameterException {
	JSONArray array = new JSONArray ();
	
	
		if(param.equals("temp_max")) {
			FilterOfTemp_max filter = new FilterOfTemp_max();
			array = filter.allTime(cities, value);
		} 
		else if (param.equals("temp_min")) {
			FilterOfTemp_min filter = new FilterOfTemp_min();
			array = filter.allTime(cities, value);
		}
		else if (param.equals("feels_like_max")) {
			FilterOfFeels_like_max filter = new FilterOfFeels_like_max();
			array = filter.allTime(cities, value);
		}
		else if (param.equals("feels_like_min")) {
			FilterOfFeels_like_min filter = new FilterOfFeels_like_min();
			array = filter.allTime(cities, value);
		}
		else  throw new ParameterException (param+ " non è una stringa ammessa.Inserisci una stringa tra temp_min,temp_max,feels_like e visibility");   
		
		
		return array;				
	}
}
