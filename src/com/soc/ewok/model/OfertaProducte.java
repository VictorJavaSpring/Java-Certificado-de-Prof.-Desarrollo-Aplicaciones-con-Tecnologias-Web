package com.soc.ewok.model;

import java.util.Date;

/**
 * Classe que representa un element de l'entitat Oferta de Producte
 * @author MartaL
 *
 */

public class OfertaProducte {
	private Long nId;
	private Float nPctDescompte;
	private String sOnom;
	private Date dIniciVigencia;
	private Date dFiVigencia;
	private String sOtext;
	private Long nIdP;
	
	/**
	 * Retorna l'id d' oferta de Producte.
	 * @return L'id de la oferta. Si l'oferta no s'ha grabat a base de dades,
	 * retornarà null.
	 */
	public Long getId() {
		return nId;
	}
	/**
	 * Assigna l'id de la oferta
	 * @param nId és l'id que s'assignarà a la oferta de producte
	 */
	public void setId(Long nId) {
		this.nId = nId;
	}
	/**
	 * Retorna el % de descompte
	 * @return es el % de descompte de l'oferta
	 */
	public Float getPctDescompte() {
		return nPctDescompte;
	}
	/**
	 * Assigna el % de descompte
	 * @param pctDescompte es el % de descompte
	 * @throws IllegalArgumentException En cas que el descompte sigui null o buit
	 */

	public void setPctDescompte(Float pctDescompte) {
		if(pctDescompte == null){
			throw new IllegalArgumentException("El camp Descompte és obligatori");
		}
		else if(pctDescompte < 0){
			throw new IllegalArgumentException("El descompte no pot ser negatiu"); 
		}
		nPctDescompte = pctDescompte;
	}
	/**
	 * Retorna el nom de l'oferta
	 * @return es el nom de l'oferta
	 */
	public String getNom() {
		return sOnom;
	}
	/**
	 * Assigna el nom de l'oferta
	 * @param onom es el nom de l'oferta, pot ser null
	 */
	public void setNom(String onom) {
		sOnom = onom;
	}
	/**
	 * Retorna la data d'inici de vigència de l'oferta
	 * @return es la data d'inici de l'oferta
	 */
	public Date getIniciVigencia() {
		return dIniciVigencia;
	}
	/**
	 * Assigna una data d'inici de vigència de l'oferta
	 * @param iniciVigencia
	 */
	public void setIniciVigencia(Date iniciVigencia) {
		dIniciVigencia = iniciVigencia;
	}
	/**
	 * Retorna la data de fi de vigencia de l'oferta
	 * @return es la data de fi de l'oferta
	 */
	public Date getFiVigencia() {
		return dFiVigencia;
	}
	/**
	 * Assigna una data de fi de vigència de l'oferta
	 * @param fiVigencia es la data de fi de vigència de l'oferta
	 */
	public void setFiVigencia(Date fiVigencia) {
		dFiVigencia = fiVigencia;
	}
	/**
	 * Retorna la descripció de l'oferta
	 * @return es la descripció de l'oferta
	 */
	public String getOtext() {
		return sOtext;
	}
	/**
	 * Assigna la descripció de l'oferta
	 * @param otext es la descripció de l'oferta que pot ser null
	 */
	public void setOtext(String otext) {
		sOtext = otext;
	}
	/**
	 * Retorna l'id del producte
	 * @return es l'id del producte
	 */
	public Long getIdProducte() {
		return nIdP;
	}
	/**
	 * Assigna id de relació amb l'oferta de producte
	 * @param nIdP id de relació amb l'oferta i el producte
	 * @throws IllegalArgumentException si es null
	 */
	public void setIdProducte(Long nIdP) {
		if(nIdP == null){
			throw new IllegalArgumentException("L'oferta ha de tenir un producte associat");
		}
		this.nIdP = nIdP;
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
		OfertaProducte other = (OfertaProducte) obj;
		if (dFiVigencia == null) {
			if (other.dFiVigencia != null)
				return false;
		} else if (!dFiVigencia.equals(other.dFiVigencia))
			return false;
		if (dIniciVigencia == null) {
			if (other.dIniciVigencia != null)
				return false;
		} else if (!dIniciVigencia.equals(other.dIniciVigencia))
			return false;
		if (sOnom == null) {
			if (other.sOnom != null)
				return false;
		} else if (!sOnom.equals(other.sOnom))
			return false;
		if (sOtext == null) {
			if (other.sOtext != null)
				return false;
		} else if (!sOtext.equals(other.sOtext))
			return false;
		if (nPctDescompte == null) {
			if (other.nPctDescompte != null)
				return false;
		} else if (!nPctDescompte.equals(other.nPctDescompte))
			return false;
		if (nId == null) {
			if (other.nId != null)
				return false;
		} else if (!nId.equals(other.nId))
			return false;
		if (nIdP == null) {
			if (other.nIdP != null)
				return false;
		} else if (!nIdP.equals(other.nIdP))
			return false;
		return true;
	}
	
}
