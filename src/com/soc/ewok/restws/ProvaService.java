package com.soc.ewok.restws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.soc.ewok.model.Rol;

@Path("/prova")
public class ProvaService {
	@GET
	@Path("/unRol")
	// s'arriba a aquest mètode per eWok/data/prova/unRol
	@Produces({MediaType.APPLICATION_JSON})
	public Rol getUnRol() {
		Rol r = new Rol();
		r.setId(33l);
		r.setNom("El nom del rol");
		r.setCodi("codRol");
		return r;
	}
}
