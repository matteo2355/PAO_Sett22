package com.progetto.temperatureapp.exceptions;

/**
 Questa classe  contiene il metodo che segnala l'eccezione generata a causa di una stringa vuota. 
 */

public class StringException extends Exception{
	String mex;
	/**
	 * Questo è il costruttore.
	 * @param mex rappresenta il messaggio di errore.
	 */
	public StringException(String mex) {
		this.mex=mex;
	}
	
	/**
	 * Restituisce un messaggio di errore passato al costruttore se viene inserita una stringa vuota.
	 * @return String che contiene il messaggio d'errore.
	 */
	public String getMex() {
		return mex;
	}


}
