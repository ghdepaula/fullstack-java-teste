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

import br.com.contabilizei.dto.ClienteDTO;
import br.com.contabilizei.services.ClienteService;

@Path("/clientes")
public class ClienteResource {
	
	private ClienteService clienteService;
	
	public ClienteResource() {
		this.clienteService = new ClienteService();
	}

	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response insert(ClienteDTO cliente) {
		try {
			this.clienteService.insert(cliente);

			return Response.ok(cliente).build();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new WebApplicationException(500);
		}
	}

	@PUT
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response update(ClienteDTO cliente) {
		try {
			this.clienteService.update(cliente);

			return Response.ok(cliente).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@DELETE
	@Path("{idCliente}")
	@Produces({ "application/json" })
	public Response excluir(@PathParam("idCliente") Long idCliente) {
		try {
			boolean sucesso = this.clienteService.remove(idCliente);
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
	public List<ClienteDTO> findAll() {
		try {
			return this.clienteService.findAll();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("{idCliente}")
	@Produces({ "application/json" })
	public ClienteDTO buscarPorCodigo(@PathParam("idCliente") Long idCliente) {
		try {
			return this.clienteService.findById(idCliente);
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
