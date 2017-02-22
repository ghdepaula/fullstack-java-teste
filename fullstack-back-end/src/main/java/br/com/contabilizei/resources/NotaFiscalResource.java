package br.com.contabilizei.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import br.com.contabilizei.dto.NotaFiscalDTO;
import br.com.contabilizei.services.NotaFiscalService;

@Path("/notas")
public class NotaFiscalResource {
	
	private NotaFiscalService notaFiscalService;
	
	public NotaFiscalResource() {
		this.notaFiscalService = new NotaFiscalService();
	}

	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response insert(NotaFiscalDTO notaFiscal) {
		try {
			this.notaFiscalService.insert(notaFiscal);

			return Response.ok(notaFiscal).build();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new WebApplicationException(500);
		}
	}

	@PUT
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response update(NotaFiscalDTO notaFiscal) {
		try {
			this.notaFiscalService.update(notaFiscal);

			return Response.ok(notaFiscal).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@DELETE
	@Path("{numNotaFiscal}")
	@Produces({ "application/json" })
	public Response excluir(@PathParam("numNotaFiscal") Long numNotaFiscal) {
		try {
			boolean sucesso = this.notaFiscalService.remove(numNotaFiscal);
			if (!sucesso) {
				throw new WebApplicationException(404);
			}
			return Response.ok().build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Produces({ "application/json" })
	public List<NotaFiscalDTO> findAll() {
		try {
			return this.notaFiscalService.findAll();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("{numNotaFiscal}")
	@Produces({ "application/json" })
	public NotaFiscalDTO buscarPorCodigo(@PathParam("numNotaFiscal") Long numNotaFiscal) {
		try {
			return this.notaFiscalService.findById(numNotaFiscal);
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
