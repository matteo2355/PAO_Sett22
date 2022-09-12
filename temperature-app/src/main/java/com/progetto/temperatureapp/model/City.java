package com.progetto.temperatureapp.model;

import java.util.Vector;

/**
 Questa classe descrive le proprietà di ogni città e le relative previsioni meteo ristrette.
*/

public class City {
	private String name;
    private Vector<Weather> vector= new Vector<Weather>();

/**
 * costruttore dell'oggetto
 * 
 */
public City() {
	super();
}
/**
 * costruttore dell'oggetto
 * 
 */

public City(String name) {
	this.name=name;
	}

/**
Metodo che restituisce il nome della città.
*/

public String getName() {
	return name;
}


/**
Metodo che setta il nome della città.
*/

public void setName(String name) {
	this.name = name;
}

/**
* Metodo che restituisce il vettore di weather(previsioni) della città.
*/
public Vector<Weather> getVector() {
	return vector;
}

/**
* Metodo che setta il vettore di weather(previsioni) della città.
*/

public void setVector(Vector<Weather> vector) {
	this.vector = vector;
}

/**
 * Metodo che scriviamo il vettore come una stringa.
 * @return String toReturn ci raffigura le previsioni meteo.
 */
public String toStringVector() {
	String toReturn="";
	for (int i=0; i<vector.size(); i++)
		toReturn += vector.get(i).toString();
	return toReturn;
}

/**
 * Override del metodo toString().
 * @return String che rappresenta le varie informazioni della città.
 */
@Override
public String toString() {
	return "name=" + name +  ", weatherArray=" + toStringVector() + "";
}

}
