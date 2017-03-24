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

import br.com.contabilizei.dto.TributoDTO;
import br.com.contabilizei.services.TributoService;

@Path("/tributos")
public class TributosResource {
	
	private TributoService tributoService;
	
	public TributosResource() {
		this.tributoService = new TributoService();
	}

	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response insert(TributoDTO tributo) {
		try {
			this.tributoService.insert(tributo);
			return Response.ok(tributo).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@PUT
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response update(TributoDTO tributo) {
		try {
			this.tributoService.update(tributo);
			return Response.ok(tributo).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Produces({ "application/json" })
	public List<TributoDTO> findAll() {
		try {
			return this.tributoService.findAll();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("{codTributo}")
	@Produces({ "application/json" })
	public TributoDTO findById(@PathParam("codTributo") Long codTributo) {
		try {
			return this.tributoService.findById(codTributo);
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
