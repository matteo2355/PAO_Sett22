package model;

public class Coordinates {
	
	private double latitude;
	private double longitude;
	
	
	/* Costruttore dell'oggetto.
	 * 
	 */
	 
     public Coordinates(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/*
	 * Override del metodo toString().
	 * @return String che ci esprime le coordinate.
	 */
	@Override
	public String toString() {
		return "[latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
