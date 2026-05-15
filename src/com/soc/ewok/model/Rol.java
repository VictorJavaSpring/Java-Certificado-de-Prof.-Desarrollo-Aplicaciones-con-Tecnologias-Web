package com.soc.ewok.model;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * Classe que representa un rol.
 * Els usuaris podran tenir diversos rols que determinaran
 * el que puguin fer a la web.
 * @author Pau 
 *
 */
@XmlRootElement
public class Rol {
	//var membre de l'entitat rol
	private Long nId;
	private String sNom;
	private String sCodi;
	
	//constants de la classe
	public static final String ROL_CODI_ADMINISTRADOR = "c1admin";
	public static final String ROL_CODI_CUINER = "c2cnr";
	public static final String ROL_CODI_CAIXER = "c3cxr";
	//static final String ROL_CODI_REPARTIDOR = "c4rprt";
	
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
	 * Obté el codi del rol
	 * @return el codi del rol
	 * @author JordiM
	 */
	public String getCodi() {
		return sCodi;
	}

	/**
	 * Assigna un codi al Rol
	 * @param sCodi el codi a assignar. No pot ser Null, ni pot ser repetit
	 * @author JordiM
	 */
	public void setCodi(String codi) {
		if (codi == null || codi.trim().equals("")) {
			throw new IllegalArgumentException("El Codi ha d'estar informat");
		}
		this.sCodi = codi;
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
		Rol other = (Rol) obj;
		if (nId == null) {
			if (other.nId != null)
				return false;
		} else if (!nId.equals(other.nId))
			return false;
		if (sCodi == null) {
			if (other.sCodi != null)
				return false;
		} else if (!sCodi.equals(other.sCodi))
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
		return "Rol [nId=" + nId + ", sNom=" + sNom + ", sCodi=" + sCodi + "]";
	}
	
}
