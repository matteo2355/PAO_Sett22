package com.progetto.temperatureapp.exceptions;
/**
Questa classe  contiene il metodo che segnala l'eccezione riguardante una stringa non ammessa per il value.
*/

public class WrongValueException extends Exception{
	String mex;
	/**
	 * Questo è il costruttore.
	 * @param mex rappresenta il messaggio di errore.
	 */
	public WrongValueException(String mex) {
		
		this.mex = mex;
	}
	
	/**
	 * Restituisce un messaggio di errore passato al costruttore quando viene inserita una stringa non ammessa per il value desiderato.
	 * @return String che contiene il messaggio d'errore.
	 */
	public String getMex() {
		return mex;
	}
}
