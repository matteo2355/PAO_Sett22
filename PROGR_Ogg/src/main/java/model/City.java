package model;

import java.util.Vector;

public class City {
	private String name;
    private Vector<Weather> vector= new Vector<Weather>();

/*costruttore dell'oggetto
 * 
 */
public City() {
	super();
}
/*costruttore dell'oggetto
* 
*/

public City(String name) {
	this.name=name;
	}




public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Vector<Weather> getVector() {
	return vector;
}

public void setVector(Vector<Weather> vector) {
	this.vector = vector;
}

/*
 * Con questo Metodo scriviamo il vettore come una stringa.
 *  String toReturn ci raffigura le previsioni meteo.
 */
public String toStringVector() {
	String toReturn="";
	for (int i=0; i<vector.size(); i++)
		toReturn += vector.get(i).toString();
	return toReturn;
}

/*
 * Override del metodo toString().
 * @return String che rappresenta le varie informazioni della città.
 */

public String toString() {
	return "name=" + name +  ", weatherArray=" + toStringVector() + "";
}

}
