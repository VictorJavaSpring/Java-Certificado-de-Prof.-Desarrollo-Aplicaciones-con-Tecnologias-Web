package com.soc.utils;

/**
 * excepcio produida buscant un parametre al request
 * @author Administrador
 *
 */
public class ParameterException extends Exception {
	private static final long serialVersionUID = -3071890788796399898L;

	private String nomParametre;
	private String valorTrobat;
	
	/**
	 * Constructor per defecte
	 */
	public ParameterException() {
		
	}
	
	/**
	 * Constructor indicant un missatge
	 * @param message El missatge de l'excepcio
	 */
	public ParameterException(String message) {
		super(message);
	}
	
	/**
	 * Constructor on s'indiquen tots els camps
	 * de l'excepcio
	 * @param message El missatge
	 * @param nomPar El parametre pel qual s'ha produit una excepcio
	 * @param valor El valor trobat o null si no s'ha trobat
	 */
	public ParameterException(
			String message, String nomPar, String valor) {
		super(message);
		nomParametre = nomPar;
		valorTrobat = valor;
	}	
	
	/**
	 * Obte el nom del parametre pel qual s'ha produit 
	 * una excepcio
	 * @return El nom del parametre
	 */
	public String getNomParametre() {
		return nomParametre;
	}
	
	/**
	 * Assigna el nom del parametre pel qual s'ha produit 
	 * una excepcio
	 * @param nomParametre el nom del parametre
	 */
	public void setNomParametre(String nomParametre) {
		this.nomParametre = nomParametre;
	}
	
	/**
	 * Obte el valor trobat pel parametre que ha produit
	 * l'excepcio
	 * @return El valor trobat o null si no se n'ha trobat cap
	 */
	public String getValorTrobat() {
		return valorTrobat;
	}
	
	/**
	 * Assigna el valor trobat pel parametre que ha produit
	 * l'excepcio
	 * @param valorTrobat El valor trobat o null si no se n'ha trobat cap
	 */
	public void setValorTrobat(String valorTrobat) {
		this.valorTrobat = valorTrobat;
	}
	
}
