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
import br.com.contabilizei.services.RegimesTributariosService;

@Path("/regtributarios")
public class RegimeTributarioResource {
	
	private RegimesTributariosService regimesTributariosService;
	
	public RegimeTributarioResource() {
		this.regimesTributariosService = new RegimesTributariosService();
	}

	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response insert(RegimeTributarioDTO regimeTributario) {
		try {
			this.regimesTributariosService.insert(regimeTributario);

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
			this.regimesTributariosService.update(regimeTributario);

			return Response.ok(regimeTributario).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Produces({ "application/json" })
	public List<RegimeTributarioDTO> findAll() {
		try {
			return this.regimesTributariosService.findAll();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("{codRegTributario}")
	@Produces({ "application/json" })
	public RegimeTributarioDTO findById(@PathParam("codRegTributario") Long codRegTributario) {
		try {
			return this.regimesTributariosService.findById(codRegTributario);
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
