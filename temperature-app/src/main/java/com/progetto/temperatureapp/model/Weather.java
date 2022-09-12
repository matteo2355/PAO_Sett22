package com.progetto.temperatureapp.model;

/**
* Classe che rappresenta le previsioni meteo ristrette.
 */

public class Weather {
	private String main;
	private double temp;
	private double temp_max;
	private double temp_min;
	private double feels_like;
	private String data;
	
	/**
	* Costruttore dell'oggetto.
	 */
	
	public Weather() {
		super();
	}
	/**
	* Costruttore dell'oggetto.
	*/
	
	public Weather(String main) {
		super();
		this.main = main;
		}
	/**
	 * Costruttore dell'oggetto.
	*/
	
	public Weather(String main, double temp , double temp_max, double temp_min, double feels_like,
			String data) {
		super();
		this.main = main;
		this.temp=temp;
		this.temp_max = temp_max;
		this.temp_min = temp_min;
		this.feels_like = feels_like;
		this.data = data;
	}
	/**
     * Metodo che restituisce la principale informazione sul meteo.
     */
	
	public String getMain() {
		return main;
	}
	
	/**
     * Metodo che setta il main.
     */
	
	public void setMain(String main) {
		this.main = main;
	}
	
	/**
     *Metodo che restituisce la temperatura.
     */
	
	public double getTemp() {
		return temp;
	}

	/**
     * Metodo che setta la temperatura.
     */
	public void setTemp(double temp) {
		this.temp = temp;
	}
	
	/**
      *Metodo che restituisce la temperatura massima.
     */
	public double getTemp_max() {
		return temp_max;
	}
	
	/**
     * Metodo che setta la temperatura massima.
     */
	public void setTemp_max(double temp_max) {
		this.temp_max = temp_max;
	}
	
	/**
     * Metodo che restituisce la temperatura minima.
     */
	
	public double getTemp_min() {
		return temp_min;
	}
	
	/**
    * Metodo che setta la temperatura minima.
     */
	
	public void setTemp_min(double temp_min) {
		this.temp_min = temp_min;
	}
	
	/**
      * Metodo che restituisce la temperatura percepita.
     */
	
	public double getFeels_like() {
		return feels_like;
	}

	/**
     * Metodo che setta la temperatura percepita.
     */
	
	public void setFeels_like(double feels_like) {
		this.feels_like = feels_like;
	}
	
	
	/**
    * Metodo che restituisce giorno e ora.
   */

     
	public String getData() {
		return data;
	}
	
	/**
      * Metodo che setta giorno e ora.
     */
	
	public void setData(String data) {
		this.data = data;
	}
	
	/**
	  * Override del metodo toString().
	  * @return String che rappresenta il meteo.
	 */
	
	@Override
	public String toString() {
		return "data=" + data + "main=" + main + ", temp="
				+ temp + ", temp_max="
				+ temp_max + ", temp_min=" + temp_min + ", feels_like=" + feels_like + "";
	}
}
