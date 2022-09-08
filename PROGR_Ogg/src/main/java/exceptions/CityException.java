package exceptions;

public class CityException extends Exception {
	String mex;
	
	/**
	 * Questo è il costruttore.
	 * @param mex rappresenta il messaggio di errore.
	 */
	public CityException(String mex) {
		this.mex=mex;
	}
	
	/**
	 * Restituisce un messaggio di errore passato dal costruttore quando il nome della città non è stato trovato.
	 * @return String che contiene il messaggio d'errore.
	 */
	public String getMex() {
		return mex;
	}


}
