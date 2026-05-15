package com.soc.ewok.model;

/**
 * clase que representa una adreça.
 * Els usuaris podran tenir diversas adreças.
 * @author Jose
 *
 */
public class Adreca {
	private Long nId;
	private String sLinia1;
	private String sLinia2;
	private String sCp;
	private String sCiutat;
	private String sNotes;
	private String nTelefon;
	private Long nIdClient;
	private String sAlias;
	
	/**
	 * Retorna l'id de l'adreça.
	 * @return L'id de l'adreça. Si el rol no s'ha grabat a base de dades,
	 * retornarà null. 
	 */
	public Long getId() {
		return nId;
	}
	
	/**
	 * Assigna l'id del rol.
	 * @param id L'id a assignar.
	 */
	public void setId(Long id) {
		nId = id;
	}
	
	/**
	 * Retorna la primera linia de l'adreça.
	 * @return la primera linia de l'adreça. Si l'adreça no s'ha grabat a base de dades,
	 * retornarà null. 
	 */
	public String getLinia1() {
		return sLinia1;
	}
	
	/**
	 * Assigna la primera linia de l'adreça.
	 * @param linia1 La primera linia de l'adreça a assignar.
	 */
	
	public void setLinia1(String linia1) {
		if (linia1 == null || linia1.trim().equals("")) {
			throw new IllegalArgumentException("La linia1 ha d'estar informat");
		}
		sLinia1 = linia1;
	}
	
	/**
	 * Retorna la segona linia de l'adreça.
	 * @return la segona linia de l'adreça. Si l'adreça no s'ha grabat a base de dades,
	 * retornarà null. 
	 */
	
	public String getLinia2() {
		return sLinia2;
	}
	
	/**
	 * Assigna la segona linia de l'adreça.
	 * @param linia2 La segona linia de l'adreça a assignar.
	 */
	public void setLinia2(String linia2) {
		sLinia2 = linia2;
	}
	
	/**
	 * Retorna el codi postal de l'adreca.
	 * @return el codi postal de l'adreca. Si el codi postal no s'ha grabat a base de dades,
	 * retornarà null. 
	 */
	public String getCp() {
		return sCp;
	}
	
	/**
	 * Assigna el codi postal de l'adreça.
	 * @param cp el codi postal de l'adreça a assignar.
	 */
	public void setCp(String cp) {
		if (cp == null || cp.trim().length()!= 5) {
			throw new IllegalArgumentException("El codi postal ha d'estar informat");
		}
		sCp = cp;
	}
	
	/**
	 * Retorna el nom de la ciutat.
	 * @return el nom de la ciutat. Si el nom de la ciutat no s'ha grabat a base de dades,
	 * retornarà null. 
	 */
	public String getCiutat() {
		return sCiutat;
	}
	
	/**
	 * Assigna el nom de la ciutat.
	 * @param ciutat el nom de la ciutat a assignar.
	 */
	public void setCiutat(String ciutat) {
		if (ciutat == null || ciutat.trim().equals("")) {
			throw new IllegalArgumentException("La ciutat ha d'estar informat");
		}
		sCiutat = ciutat;
	}
	
	/**
	 * Retorna les notes de l'adreca.
	 * @return les notes de l'adreca. Si les notes de l'adreca no s'han grabat a base de dades,
	 * retornarà null. 
	 */
	public String getNotes() {
		return sNotes;
	}
	
	/**
	 * Assigna les notes de l'adreca.
	 * @param notes les notes de l'adreca a assignar.
	 */
	public void setNotes(String notes) {
		sNotes = notes;
	}
	
	/**
	 * Retorna el telefon de l'adreca.
	 * @return el telefon de l'adreca. Si el telefon de l'adreca no s'ha grabat a base de dades,
	 * retornarà null. 
	 */
	public String getTelefon() {
		return nTelefon;
	}
	
	/**
	 * Assigna el telefon de l'adreca.
	 * @param telefon el telefon de l'adreca a assignar.
	 */
	public void setTelefon(String telefon) {
		if (telefon != null && telefon.trim().length() > 15) {
			throw new IllegalArgumentException("La telefon ha d'estar informat");
		}
		nTelefon = telefon;
	}
	
	/**
	 * Retorna l'id del client de l'adreca.
	 * @return l'id del client de l'adreca. Si el rol no s'ha grabat a base de dades,
	 * retornarà null. 
	 */
	public Long getIdClient() {
		return nIdClient;
	}
	
	/**
	 * Assigna l'id del client de l'adreca.
	 * @param idCliente l'id del client de l'adreca a assignar.
	 */
	public void setIdClient(Long idClient) {
		nIdClient = idClient;
	}
	
	/**
	 * Retorna l'alias de l'adreca.
	 * @return l'alias de l'adreca. Si l'alias no s'ha grabat a base de dades,
	 * retornarà null. 
	 */
	public String getAlias() {
		return sAlias;
	}
	
	/**
	 * Assigna l'alias de l'adreca.
	 * @param Alias l'aliast de l'adreca a assignar.
	 */
	public void setAlias(String Alias) {
		sAlias = Alias;
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
		Adreca other = (Adreca) obj;
		if (sCp == null) {
			if (other.sCp != null)
				return false;
		} else if (!sCp.equals(other.sCp))
			return false;
		if (nId == null) {
			if (other.nId != null)
				return false;
		} else if (!nId.equals(other.nId))
			return false;
		if (nIdClient == null) {
			if (other.nIdClient != null)
				return false;
		} else if (!nIdClient.equals(other.nIdClient))
			return false;
		if (nTelefon == null) {
			if (other.nTelefon != null)
				return false;
		} else if (!nTelefon.equals(other.nTelefon))
			return false;
		if (sAlias == null) {
			if (other.sAlias != null)
				return false;
		} else if (!sAlias.equals(other.sAlias))
			return false;
		if (sCiutat == null) {
			if (other.sCiutat != null)
				return false;
		} else if (!sCiutat.equals(other.sCiutat))
			return false;
		if (sLinia1 == null) {
			if (other.sLinia1 != null)
				return false;
		} else if (!sLinia1.equals(other.sLinia1))
			return false;
		if (sLinia2 == null) {
			if (other.sLinia2 != null)
				return false;
		} else if (!sLinia2.equals(other.sLinia2))
			return false;
		if (sNotes == null) {
			if (other.sNotes != null)
				return false;
		} else if (!sNotes.equals(other.sNotes))
			return false;
		return true;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Adreca [nId=" + nId + ", sLinia1=" + sLinia1 + ", sLinia2="
				+ sLinia2 + ", sCp=" + sCp + ", sCiutat=" + sCiutat
				+ ", sNotes=" + sNotes + ", nTelefon=" + nTelefon
				+ ", nIdClient=" + nIdClient + ", sAlias=" + sAlias + "]";
	}
	
}