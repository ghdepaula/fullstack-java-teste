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
import br.com.contabilizei.model.Anexo;
import br.com.contabilizei.services.AnexoService;

/**
 * Classe responsável pelo processamento de requisições HTTP ao serviço de anexos da aplicação. 
 * 
 * @author Guilherme Henrique de Paula
 * 
 */
@Path("/anexos")
public class AnexoResource {
	
	
	private AnexoService anexoService;
	
	/**
	 * 
	 */
	public AnexoResource() {
		this.anexoService = new AnexoService();
	}

	/**
	 * Método que processa os dados de uma requisição HTTP POST para inserção de um novo {@link Anexo}.
	 * 
	 * @param anexo instância de {@link AnexoDTO} contendo dados do novo {@link Anexo} que será inserido.
	 * @return response da requisição HTTP POST com dados da instância de {@link AnexoDTO} no formato JSON.
	 */
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

	/**
	 * Método que processa os dados de uma requisição HTTP PUT para atualização da entidade {@link Anexo}.
	 * 
	 * @param anexo instância de {@link AnexoDTO} contendo dados da entidade {@link Anexo} que será atualizada.
	 * @return response da requisição HTTP PUT com dados da instância de {@link AnexoDTO} atualizada no formato JSON.
	 */
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

	/**
	 * Método que processa a requisição HTTP GET para listagem de todos os anexos.
	 * 
	 * @return response da requisição HTTP GET com lista de {@link AnexoDTO} no formato JSON.
	 */
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
