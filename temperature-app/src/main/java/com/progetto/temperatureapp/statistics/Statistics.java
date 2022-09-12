package com.progetto.temperatureapp.statistics;

import org.json.JSONObject;

import com.progetto.temperatureapp.model.City;
import com.progetto.temperatureapp.service.ServiceImplementation;

// Questa classe contiene i metodi necessari alle statistiche.
 
public class Statistics {
ServiceImplementation service= new ServiceImplementation();
	
/**
 * Questo metodo serve per calcolare le statistiche relative a una città.
 * @param name è il nome della città su cui si vogliono fare statistiche.
 * @return JSONObject contenente il nome della città e le relative medie
 */
	public JSONObject TempAverage(String name) {
	         
	        
	        City city = new City(name);
	        city = service.getRistrictWeatherOfCityfromApi(name);
	        
	        double temp_max_real = 0;
	        double temp_min_real = 0;
	        double temp_ave_real = 0;
	        double variance_real = 0;
	        double temp_max_feels_like = 0;
	        double temp_min_feels_like = 0;
	        double temp_ave_feels_like = 0;
	        double variance_feels_like= 0;
	        
	        int i= 0;
	        
	        while( i<city.getVector().size() ) {
	        	 temp_ave_real += city.getVector().get(i).getTemp();
	             temp_ave_feels_like += city.getVector().get(i).getFeels_like();
	             if(city.getVector().get(i).getTemp()>temp_max_real)
	             	temp_max_real= city.getVector().get(i).getTemp();
	             if (city.getVector().get(i).getTemp()<temp_min_real)
	             	temp_min_real = city.getVector().get(i).getTemp();
	            
	             if(city.getVector().get(i).getFeels_like()>temp_max_feels_like)
	              	temp_max_feels_like= city.getVector().get(i).getFeels_like();
	              if (city.getVector().get(i).getFeels_like()<temp_min_feels_like)
	              	temp_min_feels_like = city.getVector().get(i).getFeels_like();
	              
	              i++;
	            }
	            
	        temp_ave_real = temp_ave_real/i;
	        temp_ave_feels_like = temp_ave_feels_like/i;
	       
	        
	        
	        i=0;
	        
	        /**calcolo della varianza di temperatura
	         * 
	         */
	        while(i<city.getVector().size()) {
	        	variance_real += ((int)((city.getVector().get(i).getTemp())-temp_ave_real))^2;
	        	variance_feels_like += ((int)((city.getVector().get(i).getFeels_like())-temp_ave_feels_like))^2;
	        	i++;
	        }
	        
	        variance_real /=i;
	        variance_feels_like /=i;
	        
	        JSONObject object = new JSONObject();
	       
	        object.put("City", name);
	        object.put("Temp_Real Average", temp_ave_real);
	        object.put("Temp_Real Max", temp_max_real);
	        object.put("Temp_Real Min", temp_min_real);
	        object.put("Temp_Feels_like Average", temp_ave_feels_like);
	        object.put("Temp_Feels_like Max", temp_max_feels_like);
	        object.put("Temp_Feels_like Min", temp_min_feels_like);
	        object.put("Variance_real", variance_real);
	        object.put("Variance_feels_like", variance_feels_like);

	        return object;
	        
	    }
}
