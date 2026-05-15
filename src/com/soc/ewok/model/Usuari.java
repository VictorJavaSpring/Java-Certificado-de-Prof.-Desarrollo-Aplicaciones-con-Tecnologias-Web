package com.soc.ewok.model;

import java.util.List;
import java.util.Vector;

public class Usuari {
	private String mail;
	private String password;
	private boolean actiu;
	private List<Rol> llistaRols = null;
	
	/**
	 * Retorna el Mail de l'usuari
	 * @return Mail Usuar
	 */
	public String getMail() {
		return mail;
	}
	
	/**
	 * Assigna el Mail de l'usuari
	 * @param mail Mail a assignar
	 */
	public void setMail(String mail) {
		if (mail == null || mail.trim().equals("")) {
			throw new IllegalArgumentException("El mail ha d'estar informat");}
		this.mail = mail;
	}
	
	/**
	 * Retorna el Password de l'usuari
	 * @return Password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Assigna el pasword de l'usuari
	 * @param password Password a assignar
	 */
	public void setPassword(String password) {
		if (password == null || password.trim().equals("")) {
			throw new IllegalArgumentException("El mail ha d'estar informat");}
		this.password = password;
	}
	
	/**
	 * Retorna cert si l'usuari es actiu
	 * @return actiu
	 */
	public boolean getActiu() {
		return actiu;
	}
	
	/**
	 * Assigna actiu a usuari
	 * @param actiu 
	 */
	public void setActiu(boolean actiu) {
		this.actiu = actiu;
	}

	/**
	 * Afegeix un Rol a la llista de rols de l'usuari
	 * @param r El Rol a afegir
	 * @return False si el rol passat és null.
	 */
	public boolean addRol(Rol r){
		//comprovar si hi ha llista
		if (llistaRols == null){
			//si no n'hi ha. crear-la
			llistaRols = new Vector<Rol>();
		}
		//comprovar si ens passen Rol o no
		if (r == null){
			// si és null retornem fals i no fem res més
			return false;
		}
		//afegim el rol amb el métode add de la classe List i
		//retornem el boolean resultat del metode.
		return llistaRols.add(r);
	}
	
	/**
	 * Obté la llista de rols de l'usuari
	 * @return Un objecte List amb objectes Rol
	 */
	public List<Rol> getRols(){
		//si l'objecte no té llista de rols llençar excepció illegalState
		if (llistaRols == null){
			throw new IllegalStateException("L'usuari "+ this.getMail() +" no té Rols carregats");
		}
		return llistaRols;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuari other = (Usuari) obj;
		if (actiu != other.actiu)
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuari [mail=" + mail + ", actiu="
				+ actiu + ", llistaRols=" + llistaRols.toString() + "]";
	}
	
}
