package model;

import java.util.Vector;

public class City {
	
	private String name;
	private Coordinates coordinates;
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
		this.coordinates=null;
		//this.weatherArray = null
	}
	
	public City(Coordinates coordinates) {
		this.name=null;
		this.coordinates=coordinates;
		//this.weatherArray = null
	}
	
	public City(String name, Coordinates coordinates) {
		this.name=name;
		this.coordinates=coordinates;
		//this.weatherArray = null
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Coordinates getCoordinates() {
		return coordinates;
	}
	
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
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
		return "name=" + name + ", coordinates=" + coordinates + ", weatherArray=" + toStringVector() + "";
	}

}
