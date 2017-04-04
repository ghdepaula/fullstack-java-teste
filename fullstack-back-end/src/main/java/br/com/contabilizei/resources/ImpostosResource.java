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
import br.com.contabilizei.model.Imposto;
import br.com.contabilizei.services.ImpostosService;

/**
 * Classe responsável pelo processamento de requisições HTTP ao serviço de impostos da aplicação. 
 * 
 * @author Guilherme Henrique de Paula
 * 
 */
@Path("/impostos")
public class ImpostosResource {
	
	private ImpostosService impostosService;
	
	/**
	 * 
	 */
	public ImpostosResource() {
		this.impostosService = new ImpostosService();
	}

	/**
	 * Método Java que processa a requisição HTTP POST para calcúlo e inserção novos impostos.
	 * 
	 * @param imposto instância de {@link ImpostoDTO} contendo dados do novo {@link Imposto} que será calculado e inserido.
	 * @return response da requisição HTTP POST com dados da instância de {@link ImpostoDTO} no formato JSON.
	 */
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
	
	/**
	 * Método que processa os dados de uma requisição HTTP PUT para atualização de uma entidade {@link Imposto}.
	 * 
	 * @param imposto instância de {@link ImpostoDTO} contendo dados do {@link Imposto} que será atualizado.
	 * @return response da requisição HTTP PUT com dados da instância de {@link ImpostoDTO} atualizada no formato JSON.
	 */
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
	
	/**
	 * Método que processa a requisição HTTP GET para listagem de todos os anexos.
	 * 
	 * @return response da requisição HTTP GET com lista de {@link ImpostoDTO} no formato JSON.
	 */
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
	
	/**
	 * Método Java que processa a requisição HTTP GET para listagem de todos os impostos com base no código de cliente.
	 * 
	 * @param codCliente código do cliente que será parâmetro para busca da lista de impostos
	 * 
	 * @return response da requisição HTTP GET com lista de {@link ImpostoDTO} no formato JSON.
	 */
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
	
	/**
	 * Método Java que processa a requisição HTTP GET para listagem de todos os impostos com base no código de cliente.
	 * 
	 * @param codCliente código do cliente que será parâmetro para busca da lista de impostos
	 * @param mes mês que será parâmetro para busca da lista de impostos
	 * @param ano ano que será parâmetro para busca da lista de impostos
	 * 
	 * @return response da requisição HTTP GET com lista de {@link ImpostoDTO} no formato JSON.
	 */
	@GET
	@Path("cliente/{codCliente}/mes/{mes}/{ano}")
	@Produces({ "application/json" })
	public List<ImpostoDTO> findByCodClienteMes(@PathParam("codCliente") Long codCliente, @PathParam("mes")String mes, @PathParam("ano")String ano) {
		try {
			
			List<ImpostoDTO> impostos = this.impostosService.findByCodClienteMesAno(codCliente, mes, ano);
			return impostos;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new WebApplicationException(500);
		}
	}
	
	/**
	 * Método Java que processa a requisição HTTP GET para busca de um imposto.
	 * 
	 * @param idImposto código identificador do imposto a ser pesquisado
	 * 
	 * @return response da requisição HTTP GET com dados de uma instância de {@link ImpostoDTO} no formato JSON.
	 */
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
