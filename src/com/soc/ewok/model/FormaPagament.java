package com.soc.ewok.model;

/**
 * Classe FormaPagamnet
 * @author CarlosM
 *
 */

public class FormaPagament {
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FormaPagament other = (FormaPagament) obj;
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
	private Long nId;
	private String sNom;
	
	/**
	 * Retorna la id de la Forma de Pagament
	 * @return La nId de la Forma de Pagament,
	 * Si la Forma de Pagament no s'ha grabat a la BBDD
	 * retornarà null. 
	 */
	public Long getnId() {
		return nId;
	}
	/**
	 * Assinga la id de la Forma de Pagament
	 * @param nId La id de la Forma de Pagament
	 */
	public void setnId(Long nId) {
		this.nId = nId;
	}
	/**
	 * Retorna la descripcio de la Forma de Pagament
	 * @return sNom la descripcio de la Forma de Pagament
	 */
	public String getsNom() {
		return sNom;
	}
	/**
	 * Assinga la descripcio de la forman
	 * @param sNom la descripcio de la Forma de Pagament
	 * @throws IllegalArgumentException En cas que el nom sigui null o buit
	 */
	 public void setsNom(String sNom) {
		 if (sNom == null || sNom.trim().equals("")) {
			 throw new IllegalArgumentException("la descripció ha d'esta informada");
		 }		 
			this.sNom = sNom;
	}
}
