package com.soc.ewok.dao;

/**
 * Classe comu a tots els DAO amb les constants de taules i camps de base de
 * dades
 *
 */
public class ConstantsSQL {
	// Constants de taula ROL
	static final String ROL_TAULA = "rol";
	static final String ROL_CAMP_ID = "Id";
	static final String ROL_CAMP_NOM = "Nom";
	static final String ROL_CAMP_CODI = "Codi";

	// Constants de taula PRODUCTE
	static final String PRODUCTE_TAULA = "producte";
	static final String PRODUCTE_CAMP_ID = "Id";
	static final String PRODUCTE_CAMP_NOM = "Nom";
	static final String PRODUCTE_CAMP_DESCRIPCIO_CURTA = "DescripcioCurta";
	static final String PRODUCTE_CAMP_DESCRIPCIO = "Descripcio";
	static final String PRODUCTE_CAMP_INICI_VIGENCIA = "IniciVigencia";
	static final String PRODUCTE_CAMP_FI_VIGENCIA = "FiVigencia";
	static final String PRODUCTE_CAMP_ID_UNITAT = "IdUnitat";
	static final String PRODUCTE_CAMP_ID_TIPUS_PRODUCTE = "IdTipusProducte";
	
	// Constants de taula LINIA COMANDA
	static final String LINIACOMANDA_TAULA = "liniaComanda";
	static final String LINIACOMANDA_CAMP_ID = "IdComanda";
	static final String LINIACOMANDA_CAMP_NUMLINIA = "NumLinia";
	static final String LINIACOMANDA_CAMP_IDPRODUCTE = "IdProducte";
	static final String LINIACOMANDA_CAMP_QUANTITAT = "Quantitat";
	static final String LINIACOMANDA_CAMP_PREUBRUT = "PreuBrut";
	static final String LINIACOMANDA_CAMP_PREUVENDA = "PreuVenda";
	static final String LINIACOMANDA_CAMP_ESTAT = "Estat";
	static final String LINIACOMANDA_CAMP_ALIAS_ESTAT = "AliasEstatLC";
	
	// Constants de taula COMANDA
	static final String COMANDA_TAULA = "comanda";
	static final String COMANDA_CAMP_ID = "Id";
	static final String COMANDA_CAMP_IDCLIENT = "IdClient";
	static final String COMANDA_CAMP_PUNTS = "PuntsGenerats";
	static final String COMANDA_CAMP_DESCOMPTE = "PctDescompte";
	static final String COMANDA_CAMP_DATA = "Data";
	static final String COMANDA_CAMP_ADRECA = "AdrecaEntrega";
	static final String COMANDA_CAMP_COMENTARIS = "Comentaris";
	static final String COMANDA_CAMP_ESTAT = "Estat";
	static final String COMANDA_CAMP_PREUFINAL = "PreuFinal";
	
	// Constants de taula OfertaProducte
	static final String OFERTAPRODUCTE_TAULA = "ofertaProducte";
	static final String OFERTAPRODUCTE_CAMP_ID = "Id";
	static final String OFERTAPRODUCTE_CAMP_PCTDESCOMPTE = "PctDescompte";
	static final String OFERTAPRODUCTE_CAMP_NOM = "Nom";
	static final String OFERTAPRODUCTE_CAMP_INICI_VIGENCIA = "IniciVigencia";
	static final String OFERTAPRODUCTE_CAMP_FI_VIGENCIA = "FiVigencia";
	static final String OFERTAPRODUCTE_CAMP_DESCRIPCIO = "Descripcio";
	static final String OFERTAPRODUCTE_CAMP_IDPRODUCTE = "IdProducte";

	// Constants de taula PREU
	static final String PREU_TAULA = "preu";
	static final String PREU_CAMP_ID = "Id";
	static final String PREU_CAMP_PREU = "Preu";
	static final String PREU_CAMP_INICIVIGENCIA = "IniciVigencia";
	static final String PREU_CAMP_FINALVIGENCIA = "FinalVigencia";
	static final String PREU_CAMP_IDPRODUCTE = "IdProducte";

	// Constants de taula COMPONENTS
	static final String COMPONENTS_TAULA = "components";
	static final String COMPONENTS_CAMP_IDPRODUCTE = "IdProducte";
	static final String COMPONENTS_CAMP_IDCOMPONENT = "IdComponent";
	static final String COMPONENTS_CAMP_QUANTITAT = "Quantitat";
	static final String COMPONENTS_CAMP_ORDRE = "Ordre";

	// Constants de taula OfertaComanda
	static final String OFERTACOMANDA_TAULA = "ofertaComanda";
	static final String OFERTACOMANDA_CAMP_ID = "Id";
	static final String OFERTACOMANDA_CAMP_LIMITAINFERIOR = "LimitInferior";
	static final String OFERTACOMANDA_CAMP_PCTDESCOMPTE = "PctDescompte";
	static final String OFERTACOMANDA_CAMP_INICI_VIGENCIA = "IniciVigencia";
	static final String OFERTACOMANDA_CAMP_FI_VIGENCIA = "FiVigencia";

	// Constants de taula PAGAMENTS
	static final String PAGAMENTS_TAULA = "pagament";
	static final String PAGAMENTS_CAMP_ID = "Id";
	static final String PAGAMENTS_CAMP_IDCOMANDA = "IdComanda";
	static final String PAGAMENTS_CAMP_QUANTITAT = "Quantitat";
	static final String PAGAMENTS_CAMP_IDFORMAPAGAMENT = "IdFormaPagament";
	static final String PAGAMENTS_CAMP_DATA = "Data";

	// Constants de taula FORMAPAGAMENTS
	static final String FORMAPAGAMENTS_TAULA = "formaPagament";
	static final String FORMAPAGAMENTS_CAMP_ID = "Id";
	static final String FORMAPAGAMENTS_CAMP_NOM = "Nom";

	// Constants de taula XECS
	static final String XEC_TAULA = "xec";
	static final String XEC_CAMP_ID = "Id";
	static final String XEC_CAMP_NUM_PUNTS = "NumPunts";
	static final String XEC_CAMP_DATA_CADUCITAT = "DataCaducitat";
	static final String XEC_CAMP_ID_COMANDA = "IdComanda";
	static final String XEC_CAMP_ID_CLIENT = "IdClient";

	// Constants de taula PUNTSPENDENTS
	static final String PUNTSPENDENTS_TAULA = "puntsPendents";
	static final String PUNTSPENDENTS_CAMP_ID = "Id";
	static final String PUNTSPENDENTS_CAMP_NUM_PUNTS = "NumPunts";
	static final String PUNTSPENDENTS_CAMP_DATA_CADUCITAT = "DataCaducitat";
	static final String PUNTSPENDENTS_CAMP_ID_CLIENT = "IdClient";

	// constants de la taula USUARI
	static final String USUARI_TAULA = "usuari";
	static final String USUARI_CAMP_EMAIL = "eMail";
	static final String USUARI_CAMP_PASSWORD = "Password";
	static final String USUARI_CAMP_ACTIU = "Actiu";

	// constants de la taula COMENTARICLIENT
	static final String COMENTARI_TAULA = "comentariClient";
	static final String COMENTARI_CAMP_ID = "Id";
	static final String COMENTARI_CAMP_IDCLIENT = "IdClient";
	static final String COMENTARI_CAMP_COMENTARI = "Comentari";
	static final String COMENTARI_CAMP_DATA = "Data";
	static final String COMENTARI_CAMP_IDAUTOR = "IdAutor";

	// Constants de taula CLIENT
	static final String CLIENT_TAULA = "client";
	static final String CLIENT_CAMP_ID = "Id";
	static final String CLIENT_CAMP_NOM = "Nom";
	static final String CLIENT_CAMP_COGNOMS = "Cognoms";
	static final String CLIENT_CAMP_TELEFON = "Telefon";
	static final String CLIENT_CAMP_EMAIL = "eMail";
	static final String CLIENT_CAMP_DATA_ALTA = "DataAlta";
	static final String CLIENT_CAMP_DNI = "DNI";
	static final String CLIENT_CAMP_ID_USUARI = "IdUsuari";

	// Constants de taula TIPUSPRODUCTE
	static final String TIPUSPRODUCTE_TAULA = "tipusProducte";
	static final String TIPUSPRODUCTE_CAMP_ID = "Id";
	static final String TIPUSPRODUCTE_CAMP_NOM = "Nom";
	static final String TIPUSPRODUCTE_CAMP_CODI = "Codi";

	// Constants de taula UNITAT
	static final String UNITAT_TAULA = "unitat";
	static final String UNITAT_CAMP_ID = "Id";
	static final String UNITAT_CAMP_NOM = "Nom";
	static final String UNITAT_CAMP_ACRONIM = "Acronim";

	// Constants de taula OFERTAPUNTS
	static final String OFERTAPUNTS_TAULA = "ofertaPunts";
	static final String OFERTAPUNTS_CAMP_ID = "Id";
	static final String OFERTAPUNTS_CAMP_EUROS_PER_PUNT = "EurosPerPunt";
	static final String OFERTAPUNTS_CAMP_PUNTS_PER_XEC = "PuntsPerXec";
	static final String OFERTAPUNTS_CAMP_DIES_VIGENCIA_PUNTS = "DiesVigenciaPunt";
	static final String OFERTAPUNTS_CAMP_DIES_VIGENCIA_XECS = "DiesVigenciaXec";
	static final String OFERTAPUNTS_CAMP_INICI_VIGENCIA = "IniciVigencia";
	static final String OFERTAPUNTS_CAMP_FI_VIGENCIA = "FiVigencia";
	
	// Constants de taula ADRECA
	static final String ADRECA_TAULA = "adreca";
	static final String ADRECA_CAMP_ID = "Id";
	static final String ADRECA_CAMP_LINIA1 = "Linia1";
	static final String ADRECA_CAMP_LINIA2 = "Linia2";
	static final String ADRECA_CAMP_CP = "CP";
	static final String ADRECA_CAMP_CIUTAT= "Ciutat";
	static final String ADRECA_CAMP_NOTES = "Notes";
	static final String ADRECA_CAMP_TELEFON = "Telefon";
	static final String ADRECA_CAMP_IDCLIENT = "IdClient";
	static final String ADRECA_CAMP_ALIAS = "Alias";

	// Constants de taula ROL_USUARI
	static final String ROL_USUARI_TAULA = "rolUsuari";
	static final String ROL_USUARI_CAMP_EMAIL = "eMail";
	static final String ROL_USUARI_CAMP_ID = "idRol";
}
