package com.soc.ewok.model;

import java.sql.Date;

public class ComentariClient2 {
	private Long Id;
	private Long IdClient;
	private String Text;
	private Date Data;
	private Long IdAutor;

/**
 * Retorna l'Id del ComentariClient2
 * @return Id
 */
public Long getIdClient() {
	return IdClient;
}

/**
 * Assigna un IdClient a ComentariClient2
 * @param idClient
 */
public void setIdClient(Long idClient) {
	IdClient = idClient;
}

/**
 * Retorna el Text de ComentariClient2
 * @return Text
 */
public String getText() {
	return Text;
}

/**
 * Assigna un Text a ComentariClient2
 * @param text
 */
public void setText(String text) {
	Text = text;
}

/**
 * Retorna la Data de ComentariClient2
 * @return Data
 */
public Date getData() {
	return Data;
}

/**
 * Assigna una Data a ComentariClient2
 * @param data
 */
public void setData(Date data) {
	Data = data;
}

/**
 * Assigna un Id a ComentariClient2
 * @param id
 */
public void setId(Long id) {
	Id = id;
}

/**
 * Retorna un Id de ComentariClient2
 * @param id
 */
public void getId(Long id){
	
}

/**
 * Retorna l'IdAutor de ComentariClient2
 * @return IdAutor
 */
public Long getIdAutor() {
	return IdAutor;
}

/**
 * Assigna un IdAutor a ComentariClient2
 * @param idAutor
 */
public void setIdAutor(Long idAutor) {
	IdAutor = idAutor;
}


@Override
public String toString() {
	return "ComentariClient2 [Id=" + Id + ", IdClient=" + IdClient + ", Text="
			+ Text + ", Data=" + Data + ", IdAutor=" + IdAutor + "]";
}


}
