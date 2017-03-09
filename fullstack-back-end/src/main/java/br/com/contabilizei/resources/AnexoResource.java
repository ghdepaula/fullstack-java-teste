package br.com.contabilizei.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import br.com.contabilizei.dto.AnexoDTO;
import br.com.contabilizei.services.AnexoService;

@Path("/anexos")
public class AnexoResource {
	
	
	private AnexoService anexoService;
	
	public AnexoResource() {
		this.anexoService = new AnexoService();
	}

	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response insert(AnexoDTO anexo) {
		try {
			this.anexoService.insert(anexo);

			return Response.ok(anexo).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@PUT
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response update(AnexoDTO anexo) {
		try {
			this.anexoService.update(anexo);

			return Response.ok(anexo).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Produces({ "application/json" })
	public List<AnexoDTO> findAll() {
		try {
			return this.anexoService.findAll();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
