package com.soc.utils;

/**
 * Excepció produida buscant un paràmetre al request
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
	 * @param message El missatge de l'excepció
	 */
	public ParameterException(String message) {
		super(message);
	}
	
	/**
	 * Constructor on s'indiquen tots els camps
	 * de l'excepció
	 * @param message El missatge
	 * @param nomPar El paràmetre pel qual s'ha produit una excepció
	 * @param valor El valor trobat o null si no s'ha trobat
	 */
	public ParameterException(
			String message, String nomPar, String valor) {
		super(message);
		nomParametre = nomPar;
		valorTrobat = valor;
	}	
	
	/**
	 * Obté el nom del paràmetre pel qual s'ha produit 
	 * una excepció
	 * @return El nom del paràmetre
	 */
	public String getNomParametre() {
		return nomParametre;
	}
	
	/**
	 * Assigna el nom del paràmetre pel qual s'ha produit 
	 * una excepció
	 * @param nomParametre el nom del paràmetre
	 */
	public void setNomParametre(String nomParametre) {
		this.nomParametre = nomParametre;
	}
	
	/**
	 * Obté el valor trobat pel paràmetre que ha produit
	 * l'excepció
	 * @return El valor trobat o null si no se n'ha trobat cap
	 */
	public String getValorTrobat() {
		return valorTrobat;
	}
	
	/**
	 * Assigna el valor trobat pel paràmetre que ha produit
	 * l'excepció
	 * @param valorTrobat El valor trobat o null si no se n'ha trobat cap
	 */
	public void setValorTrobat(String valorTrobat) {
		this.valorTrobat = valorTrobat;
	}
	
}
