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

/**
 * Classe responsável pelo processamento de requisições HTTP ao serviço de tributos da aplicação. 
 * 
 * @author ghdepaula
 * 
 */
@Path("/tributos")
public class TributosResource {
	
	private TributoService tributoService;
	
	/**
	 * 
	 */
	public TributosResource() {
		this.tributoService = new TributoService();
	}

	/**
	 * Método Java que processa a requisição HTTP POST para inserção de um novo tributo.
	 * 
	 * @param tributo instância contendo dados do tributo que será inserido.
	 * @return response retorno requisição HTTP POST com dados do tributo inserido no formato JSON.
	 */
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

	/**
	 * Método Java que processa a requisição HTTP PUT para atualização de um tributo.
	 * 
	 * @param tributo instância contendo dados do tributo que será inserido.
	 * @return response retorno requisição HTTP PUT com dados do tributo inserido no formato JSON.
	 */
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

	/**
	 * Método Java que processa a requisição HTTP GET para listagem de todos os tributos.
	 * 
	 * @return response da requisição HTTP GET com lista tributos no formato JSON.
	 */
	@GET
	@Produces({ "application/json" })
	public List<TributoDTO> findAll() {
		try {
			return this.tributoService.findAll();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	/**
	 * Método Java que processa a requisição HTTP GET para busca de um tributo.
	 * 
	 * @param codTributo código identificador do tributo a ser pesquisado
	 * @return response da requisição HTTP GET com dados do tributo no formato JSON.
	 */
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
