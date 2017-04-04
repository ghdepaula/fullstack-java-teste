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

/**
 * Classe responsável pelo processamento de requisições HTTP ao serviço de regimes tributários da aplicação. 
 * 
 * @author Guilherme Henrique de Paula
 * 
 */
@Path("/regtributarios")
public class RegimeTributarioResource {
	
	private RegimesTributariosService regimesTributariosService;
	
	/**
	 * 
	 */
	public RegimeTributarioResource() {
		this.regimesTributariosService = new RegimesTributariosService();
	}

	
	/**
	 * Método Java que processa a requisição HTTP POST para inserção de um novo regime tributário.
	 * 
	 * @param regimeTributario instância contendo dados do regime tributário que será inserido.
	 * @return response retorno requisição HTTP POST com dados do regime tributário inserido no formato JSON.
	 */
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

	/**
	 * Método Java que processa a requisição HTTP PUT para atualização de um regime tributário.
	 * 
	 * @param regimeTributario instância contendo dados do regime tributário que será inserido.
	 * @return response retorno requisição HTTP PUT com dados do regime tributário inserido no formato JSON.
	 */
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

	/**
	 * Método Java que processa a requisição HTTP GET para listagem de todos os regimes tributários.
	 * 
	 * @return response da requisição HTTP GET com lista regimes tributários no formato JSON.
	 */
	@GET
	@Produces({ "application/json" })
	public List<RegimeTributarioDTO> findAll() {
		try {
			return this.regimesTributariosService.findAll();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	/**
	 * Método Java que processa a requisição HTTP GET para busca de um regime tributário.
	 * 
	 * @param codRegTributario código identificador do regime tributário a ser pesquisado
	 * @return response da requisição HTTP GET com dados do regime tributário no formato JSON.
	 */
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
