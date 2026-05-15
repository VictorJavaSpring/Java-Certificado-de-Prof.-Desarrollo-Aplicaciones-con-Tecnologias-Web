package com.soc.ewok.test.model.exemple;

import java.util.Date;


/**
 * Classe que representa un rol.
 * Els usuaris podran tenir diversos rols que determinaran
 * el que puguin fer a la web.
 * @author Pau 
 *
 */
public class RolData {
	private Long nId;
	private String sNom;
	Date dDataCreacio;
	
	/**
	 * Retorna l'id del rol.
	 * @return L'id del rol. Si el rol no s'ha grabat a base de dades,
	 * retornarà null. 
	 */
	public Long getId() {
		return nId;
	}
	
	/**
	 * Assigna l'id del rol.
	 * @param id L'id a assignar.
	 * 
	 */
	public void setId(Long id) {
		nId = id;
	}
	
	/**
	 * Retorna el nom del rol
	 * @return El nom del rol
	 */
	public String getNom() {
		return sNom;
	}
	
	/**
	 * Assigna un nom al rol.
	 * @param nom El nom a assignar
	 * @throws IllegalArgumentException En cas que el nom sigui null o buit
	 */
	public void setNom(String nom) {
		if (nom == null || nom.trim().equals("")) {
			throw new IllegalArgumentException("El nom ha d'estar informat");
		}
		sNom = nom;
	}

	/**
	 * Obté la data de creació del rol
	 * @return La data de creació
	 */
	public Date getDataCreacio() {
		return dDataCreacio;
	}

	/**
	 * Assigna la data de creació
	 * @param dataCreacio La data de creació a assignar
	 */
	public void setDataCreacio(Date dataCreacio) {
		this.dDataCreacio = dataCreacio;
	}

	
	
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolData other = (RolData) obj;
		if (dDataCreacio == null) {
			if (other.dDataCreacio != null)
				return false;
		} else if (!dDataCreacio.equals(other.dDataCreacio))
			return false;
		if (nId == null) {
			if (other.nId != null)
				return false;
		} else if (!nId.equals(other.nId))
			return false;
		if (sNom == null) {
			if (other.sNom != null)
				return false;
		} else if (!sNom.equals(other.sNom))
			return false;
		return true;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RolData [nId=" + nId + ", sNom=" + sNom + ", dDataCreacio="
				+ dDataCreacio + "]";
	}
	
	
}
