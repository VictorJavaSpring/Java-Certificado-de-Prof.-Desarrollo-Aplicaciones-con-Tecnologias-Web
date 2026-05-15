package com.soc.ewok.model;

import java.util.Date;

/**
 * Classe que representa els clients
 * @author Nacho
 *
 */
public class Client {
	private Long nId;
	private String sNom;
	private String sCognoms;
	private String sTelefon;
	private String sEmail;
	private Date dDataAlta;
	private String sDni;
	private String sIdUsuari;
	
	/**
	 * Retorna l'id del client.
	 * @return L'id del client. 
	 * Retornarà null si el client no queda en la base de dades,
	 */
	public Long getId() {
		return nId;
	}
	
	/**
	 * Assigna l'id al client.
	 * @param id és l'id que s'assignarà.
	 * 
	 */
	public void setId(Long id) {
		nId = id;
	}
	
	/**
	 * Retorna el nom del client
	 * @return nom del client
	 */
	public String getNom() {
		return sNom;
	}
	
	/**
	 * Assigna un nom al client.
	 * @param nom és el nom que s'assignarà
	 * @throws IllegalArgumentException En cas que el nom sigui null o buit
	 */
	public void setNom(String nom) {
		if (nom == null || nom.trim().equals("")) {
			throw new IllegalArgumentException("El nom ha d'estar informat");
		}
		sNom = nom;
	}
	
	/**
	 * Retorna els cognoms del client
	 * @return cognoms del client
	 */
	public String getCognoms() {
		return sCognoms;
	}
	
	/**
	 * Assigna cognoms al client.
	 * @param cognoms són els cognoms que s'assignaran	 
	 */
	public void setCognoms(String cognoms) {		
		sCognoms = cognoms;
	}
	
	/**
	 * Retorna un string amb el número de telèfon del client 
	 * @return el telèfon del client
	 */
	public String getTelefon() {
		return sTelefon;
	}
	
	/**
	 * Assigna un telèfon al client en format String	
	 * @param telefon és el número de telèfon que s'assignarà al client
	 */
	public void setTelefon(String telefon) {
		sTelefon = telefon;
	}
	
	/**
	 * Retorna un string amb l'email de contacte del client
	 * @return email és l'adreça d'eMail del client
	 */
	public String getEmail() {
		return sEmail;
	}
	
	/**
	 * Assigna un eMail al client 
	 * @param email és l'adreça d'eMail que s'assignarà al client
	 */
	public void setEmail(String email) {
		sEmail = email;
	}
	
	/**
	 * Retorna la data en què es va donar d'alta el client
	 * @return dataAlta és la data d'alta del client
	 */
	public Date getDataAlta() {
		return dDataAlta;
	}
	
	/**
	 * Assigna una data d'alta al client
	 * @param dataAlta és la data d'alta que s'assignarà al client
	 */
	public void setDataAlta(Date dataAlta) {
		dDataAlta = dataAlta;
	}
	
	/**
	 * Retorna un string amb el document d'identitat del client
	 * @return dni és el DNI del client
	 */
	public String getDni() {
		return sDni;
	}
	
	/**
	 * Assigna un DNI al client
	 * @param dni és el dni que s'assignarà al client
	 */
	public void setDni(String dni) {
		sDni = dni;
	}
	
	/**
	 * Retorna l'id del usuari que es correspon amb el client
	 * @return idUsuari és l'id del usuari d'aquest client
	 */
	public String getIdUsuari() {
		return sIdUsuari;
	}
	
	/**
	 * Assigna un id d'usuari al client
	 * @param idUsuari és l'id d'usuari que s'assignarà al client
	 */
	public void setIdUsuari(String idUsuari) {
		sIdUsuari = idUsuari;
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
		Client other = (Client) obj;
		if (dDataAlta == null) {
			if (other.dDataAlta != null)
				return false;
		} else if (!dDataAlta.equals(other.dDataAlta))
			return false;
		if (nId == null) {
			if (other.nId != null)
				return false;
		} else if (!nId.equals(other.nId))
			return false;
		if (sCognoms == null) {
			if (other.sCognoms != null)
				return false;
		} else if (!sCognoms.equals(other.sCognoms))
			return false;
		if (sDni == null) {
			if (other.sDni != null)
				return false;
		} else if (!sDni.equals(other.sDni))
			return false;
		if (sEmail == null) {
			if (other.sEmail != null)
				return false;
		} else if (!sEmail.equals(other.sEmail))
			return false;
		if (sIdUsuari == null) {
			if (other.sIdUsuari != null)
				return false;
		} else if (!sIdUsuari.equals(other.sIdUsuari))
			return false;
		if (sNom == null) {
			if (other.sNom != null)
				return false;
		} else if (!sNom.equals(other.sNom))
			return false;
		if (sTelefon == null) {
			if (other.sTelefon != null)
				return false;
		} else if (!sTelefon.equals(other.sTelefon))
			return false;
		return true;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Client [nId=" + nId + ", sNom=" + sNom + ", sCognoms="
				+ sCognoms + ", sTelefon=" + sTelefon + ", sEmail=" + sEmail
				+ ", dDataAlta=" + dDataAlta + ", sDni=" + sDni
				+ ", sIdUsuari=" + sIdUsuari + "]";
	}
	
	
	
}
