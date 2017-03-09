package br.com.contabilizei.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import br.com.contabilizei.dto.RegimeTributarioDTO;
import br.com.contabilizei.services.RegimeTributarioService;

@Path("/regtributarios")
public class RegimeTributarioResource {
	
	private RegimeTributarioService regimeTributarioService;
	
	public RegimeTributarioResource() {
		this.regimeTributarioService = new RegimeTributarioService();
	}

	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response insert(RegimeTributarioDTO regimeTributario) {
		try {
			this.regimeTributarioService.insert(regimeTributario);

			return Response.ok(regimeTributario).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@PUT
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response update(RegimeTributarioDTO regimeTributario) {
		try {
			this.regimeTributarioService.update(regimeTributario);

			return Response.ok(regimeTributario).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Produces({ "application/json" })
	public List<RegimeTributarioDTO> findAll() {
		try {
			return this.regimeTributarioService.findAll();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("{codRegTributario}")
	@Produces({ "application/json" })
	public RegimeTributarioDTO buscarPorCodigo(@PathParam("codRegTributario") Long codRegTributario) {
		try {
			return this.regimeTributarioService.findById(codRegTributario);
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
