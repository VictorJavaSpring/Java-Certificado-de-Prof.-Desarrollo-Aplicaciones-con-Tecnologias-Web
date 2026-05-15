package com.soc.ewok.model;


import java.util.Date;

public class ComentariClient {
	private Long id;
	private Long idClient;
	private String comentari;
	private Date data;
	private String idAutor;
	
	/**
	 * retorna Id de ComentariClient
	 * @return Id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Assigna Id a ComentariClient
	 * @param id a assignar
	 */
	public void setId(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("L' Id ha d'estar informat");
		}
		this.id = id;
	}
	
	/**
	 * Retorna IdClient de ComentariClient
	 * @return IdClient
	 */
	public long getIdClient() {
		return idClient;
	}
	
	/**
	 * Assigna  IdClient a ComentariClient
	 * @param idClient a assignar
	 */
	public void setIdClient(Long idClient) {
		if (idClient == null) {
			throw new IllegalArgumentException("L'IdClient ha d'estar informat");
		}
		this.idClient = idClient;
	}
	
	/**
	 * Retorna Text de ComentariClient
	 * @return Text
	 */
	public String getComentari() {
		return comentari;
	}
	
	/**
	 * Assigna Text a ComentariClient
	 * @param text a assignar
	 */
	public void setComentari(String comentari) {
		this.comentari = comentari;
	}
	
	/**
	 * Retorna Data de ComentariClient
	 * @return Data
	 */
	public Date getData() {
		return data;
	}
	
	/**
	 * Assigna Data a ComentariClient
	 * @param data a assignar
	 */
	public void setData(Date data) {
		this.data = data;
	}
	
	
	public String getIdAutor() {
		return idAutor;
	}
	
	public void setIdAutor(String idAutor) {
		this.idAutor = idAutor;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComentariClient other = (ComentariClient) obj;
		if (comentari == null) {
			if (other.comentari != null)
				return false;
		} else if (!comentari.equals(other.comentari))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idAutor == null) {
			if (other.idAutor != null)
				return false;
		} else if (!idAutor.equals(other.idAutor))
			return false;
		if (idClient == null) {
			if (other.idClient != null)
				return false;
		} else if (!idClient.equals(other.idClient))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ComentariClient [id=" + id + ", idClient=" + idClient
				+ ", comentari=" + comentari + ", data=" + data + ", idAutor="
				+ idAutor + "]";
	}


	
	

}
