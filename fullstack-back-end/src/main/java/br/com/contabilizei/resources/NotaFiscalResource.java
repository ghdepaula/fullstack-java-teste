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

import br.com.contabilizei.dto.NotaFiscalDTO;
import br.com.contabilizei.services.NotaFiscalService;

/**
 * Classe responsável pelo processamento de requisições HTTP ao serviço de notas fiscais da aplicação. 
 * 
 * @author Guilherme Henrique de Paula
 * 
 */
@Path("/notas")
public class NotaFiscalResource {
	
	private NotaFiscalService notaFiscalService;
	
	/**
	 * 
	 */
	public NotaFiscalResource() {
		this.notaFiscalService = new NotaFiscalService();
	}

	/**
	 * Método Java que processa a requisição HTTP POST para inserção novas notas fiscais.
	 * 
	 * @param notaFiscal instância que contem os dados da nota fiscal a ser inserida.
	 * @return response contendo os dados da nota fiscal inserida.
	 */
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

	/**
	 * Método Java que processa a requisição HTTP PUT para atualização dos dados de uma nota fiscal.
	 * 
	 * @param notaFiscal instância contendo os dados da nota que deverá ser atualizada.
	 * @return response contendo os dados da nota fiscal atualizada.
	 */
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

	/**
	 * Método Java que processa a requisição HTTP GET para listagem de todas as notas fiscais.
	 * 
	 * @return respone da requisição HTTP GET com lista notas fiscais no formato JSON.
	 */
	@GET
	@Produces({ "application/json" })
	public List<NotaFiscalDTO> findAll() {
		try {
			return this.notaFiscalService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	/**
	 * Método Java que processa a requisição HTTP GET para busca de uma nota fiscal.
	 * 
	 * @param idNotaFiscal código identificador da nota fiscal a ser pesquisada.
	 * @return respone da requisição HTTP GET com dados da nota fiscal no formato JSON.
	 */
	@GET
	@Path("{idNotaFiscal}")
	@Produces({ "application/json" })
	public NotaFiscalDTO findById(@PathParam("idNotaFiscal") Long idNotaFiscal) {
		try {
			return this.notaFiscalService.findById(idNotaFiscal);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}
	
	
	/**
	 * Método Java que processa a requisição HTTP GET para listagem de todas as notas fiscais com base no código de cliente.
	 * 
	 * @param codCliente código do cliente que será parâmetro para busca da lista de notas fiscais
	 * @return respone da requisição HTTP GET com lista notas fiscais no formato JSON.
	 */
	@GET
	@Path("cliente/{codCliente}")
	@Produces({ "application/json" })
	public List<NotaFiscalDTO> findByCodCliente(@PathParam("codCliente") Long codCliente) {
		try {
			return this.notaFiscalService.findByCodCliente(codCliente);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}
	
	/**
	 * Método Java que processa a requisição HTTP GET para listagem de todos as notas fiscais com base no código de cliente.
	 * 
	 * @param codCliente código do cliente que será parâmetro para busca da lista de notas fiscais
	 * @param mes mês que será parâmetro para busca da lista de notas fiscais.
	 * @param ano ano que será parâmetro para busca da lista de notas fiscais.
	 * @return respone da requisição HTTP GET com lista notas fiscais no formato JSON.
	 */
	@GET
	@Path("cliente/{codCliente}/mes/{mes}/{ano}")
	@Produces({ "application/json" })
	public List<NotaFiscalDTO> findByCodClienteMes(@PathParam("codCliente") Long codCliente, @PathParam("mes")String mes, @PathParam("ano")String ano) {
		try {
			return this.notaFiscalService.findByCodClienteAndMes(codCliente, mes, ano);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}
	
}
