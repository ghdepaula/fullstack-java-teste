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

import br.com.contabilizei.dto.ClienteDTO;
import br.com.contabilizei.model.Cliente;
import br.com.contabilizei.services.ClienteService;

/**
 * Classe responsável pelo processamento de requisições HTTP ao serviço de clientes da aplicação. 
 * 
 * @author Guilherme Henrique de Paula
 * 
 */
@Path("/clientes")
public class ClienteResource {
	
	private ClienteService clienteService;
	
	public ClienteResource() {
		this.clienteService = new ClienteService();
	}

	/**
	 * Método que processa os dados de uma requisição HTTP POST para inserção de um novo {@link Cliente}.
	 * 
	 * @param cliente instância de {@link ClienteDTO} contendo dados do novo {@link Cliente} que será inserido.
	 * @return response da requisição HTTP POST com dados da instância de {@link ClienteDTO} no formato JSON.
	 */
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

	/**
	 * Método que processa os dados de uma requisição HTTP PUT para atualização de uma entidade {@link Cliente}.
	 * 
	 * @param cliente instância de {@link ClienteDTO} contendo dados do {@link Cliente} que será atualizado.
	 * @return response da requisição HTTP PUT com dados da instância de {@link ClienteDTO} atualizada no formato JSON.
	 */
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

	/**
	 * Método que processa a requisição HTTP GET para listagem de todos os clientes.
	 * 
	 * @return response da requisição HTTP GET com lista de {@link ClienteDTO} no formato JSON.
	 */
	@GET
	@Produces({ "application/json" })
	public List<ClienteDTO> findAll() {
		try {
			return this.clienteService.findAll();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	/**
	 * Método que processa a requisição HTTP GET para busca de um cliente.
	 * 
	 * @param idCliente código identificador do cliente a ser pesquisado
	 * @return response da requisição HTTP GET com dados da instância de {@link ClienteDTO} no formato JSON.
	 */
	@GET
	@Path("{idCliente}")
	@Produces({ "application/json" })
	public ClienteDTO findById(@PathParam("idCliente")Long idCliente) {
		try {
			return this.clienteService.findById(idCliente);
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
