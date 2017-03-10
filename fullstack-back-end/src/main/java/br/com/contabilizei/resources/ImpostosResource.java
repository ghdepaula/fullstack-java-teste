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

import br.com.contabilizei.dto.DadosImpostoDTO;
import br.com.contabilizei.dto.ImpostoDTO;
import br.com.contabilizei.services.ImpostosService;

@Path("/impostos")
public class ImpostosResource {
	
	private ImpostosService impostosService;
	
	public ImpostosResource() {
		this.impostosService = new ImpostosService();
	}

	@POST
	@Path("calcular")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response calcular(DadosImpostoDTO dadosImposto) {
		try {
			this.impostosService.calculate(dadosImposto);

			return Response.ok(dadosImposto).build();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new WebApplicationException(500);
		}
	}
	
	@PUT
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response update(ImpostoDTO imposto) {
		try {
			this.impostosService.update(imposto);

			return Response.ok(imposto).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
	
	@GET
	@Produces({ "application/json" })
	public Response findAll() {
		try {
			
			List<ImpostoDTO> impostos = this.impostosService.findAll();
			return Response.ok(impostos).build();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new WebApplicationException(500);
		}
	}
	
	@GET
	@Path("cliente/{codCliente}")
	@Produces({ "application/json" })
	public Response findByCodCliente(@PathParam("codCliente") Long codCliente) {
		try {
			
			List<ImpostoDTO> impostos = this.impostosService.findByCodCliente(codCliente);
			return Response.ok(impostos).build();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new WebApplicationException(500);
		}
	}
	
	@GET
	@Path("{idImposto}")
	@Produces({ "application/json" })
	public ImpostoDTO findById(@PathParam("idImposto") Long idImposto) {
		try {
			return this.impostosService.findById(idImposto);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new WebApplicationException(500);
		}
	}


}
