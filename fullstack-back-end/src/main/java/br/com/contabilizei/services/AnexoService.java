package br.com.contabilizei.services;

import java.util.ArrayList;
import java.util.List;
import br.com.contabilizei.dao.AnexoDAO;
import br.com.contabilizei.dto.AnexoDTO;
import br.com.contabilizei.model.Anexo;

/**
 * Classe responsável por fornecer métodos para aplicação de regras de negócio, processamento e conversão de dados envolvendo operações com clientes. 
 * 
 * @author Guilherme Henrique de Paula 
 * 
 */
public class AnexoService {
	
	private AnexoDAO daoAnexo;

	public AnexoService() {
		this.daoAnexo = new AnexoDAO();
	}

	/**
	 * Método Java que processa os dados de uma instância de {@link AnexoDTO} para inserção de uma instância da entidade {@link Anexo}.
	 * 
	 * @param clienteDTO instância de {@link AnexoDTO} contendo os dados da entidade {@link Anexo} que será inserida.
	 * 
	 */
	public void insert(AnexoDTO anexoDTO) {
		Anexo anexo = convertToModel(anexoDTO);
		this.daoAnexo.save(anexo);
	}

	/**
	 * Método Java que processa os dados de uma instância de {@link AnexoDTO} para atualização de uma instância da entidade {@link Anexo}.
	 * 
	 * @param clienteDTO instância de {@link AnexoDTO} contendo os dados da entidade {@link Anexo} que será atualizada.
	 */
	public void update(AnexoDTO anexoDTO) {
		Anexo anexo = convertToModel(anexoDTO);
		this.daoAnexo.update(anexo);
	}

	/**
	 * Método que lista todas as entidades de {@link Anexo} e realiza a conversão de dados para uma {@link List<AnexoDTO>}.
	 * 
	 * @return clientesDTO lista de {@link AnexoDTO}
	 */
	public List<AnexoDTO> findAll() {

		List<Anexo> anexos = this.daoAnexo.findAll();

		List<AnexoDTO> anexosDTO = new ArrayList<AnexoDTO>();
		for (Anexo anexo : anexos) {
			AnexoDTO anexoDTO = convertToDTO(anexo);
			anexosDTO.add(anexoDTO);
		}
		return anexosDTO;
	}

	/**
	 * Método que busca uma instância da entidade {@link Anexo} com base no seu código identificador e realiza a conversão de dados para uma instância de {@link AnexoDTO}.
	 * 
	 * @return clienteDTO instância de {@link AnexoDTO} ou {@link <code>null</code>} caso nenhum registro seja encontrado.
	 * 
	 */
	public AnexoDTO findById(Long codAnexo) {

		Anexo anexo = this.daoAnexo.find(codAnexo);

		if (anexo != null) {
			AnexoDTO anexoDTO = convertToDTO(anexo);
			return anexoDTO;
		}
		return null;
	}


	/**
	 * Método que executa a conversão de uma instância da entidade {@link Anexo} para uma instância de {@link AnexoDTO}
	 * 
	 * @return dto instancia de {@link AnexoDTO}
	 * 
	 */
	public AnexoDTO convertToDTO(Anexo anexo) {
		AnexoDTO dto = new AnexoDTO();

		dto.setCodAnexo(anexo.getCodAnexo());
		dto.setDescricaoAnexo(anexo.getDescricaoAnexo());
		dto.setAliquotaAnexo(anexo.getAliquotaAnexo());
		dto.setStatusAnexo(anexo.getStatusAnexo());

		return dto;
	}
	
	/**
	 * Método que executa a conversão de uma lista de instâncias da entidade {@link Anexo} para uma lista de instancias instância de {@link AnexoDTO} 
	 * 
	 * @param anexos lista de instâncias da entidade {@link Anexo}
	 * @return anexosDTO lista de instâncias da entidade {@link AnexoDTO}
	 * 
	 */
	public List<AnexoDTO> convertoToDTO(List<Anexo> anexos){
		
		List<AnexoDTO> anexosDTO = new ArrayList<AnexoDTO>();
		
		if(anexos == null || anexos.size() < 1){
			return anexosDTO;
		}
		
		for(Anexo anexo : anexos){
			AnexoDTO dto = new AnexoDTO();

			dto.setCodAnexo(anexo.getCodAnexo());
			dto.setDescricaoAnexo(anexo.getDescricaoAnexo());
			dto.setAliquotaAnexo(anexo.getAliquotaAnexo());
			dto.setStatusAnexo(anexo.getStatusAnexo());

			anexosDTO.add(dto);
		}
		
		return anexosDTO;
	}
	
	/**
	 * Método que executa a conversão de uma instância da entidade {@link Anexo} para uma instância de {@link AnexoDTO} 
	 * 
	 * @param anexo instância da entidade {@link Anexo} 
	 * @return dto instância de {@link AnexoDTO}
	 * 
	 */
	public AnexoDTO convertoToDTO(Anexo anexo) {

		if (anexo == null) {
			return null;
		}

		AnexoDTO dto = new AnexoDTO();

		dto.setCodAnexo(anexo.getCodAnexo());
		dto.setDescricaoAnexo(anexo.getDescricaoAnexo());
		dto.setAliquotaAnexo(anexo.getAliquotaAnexo());
		dto.setStatusAnexo(anexo.getStatusAnexo());

		return dto;
	}
	
	/**
	 * Método que executa a conversão de uma lista de instâncias de {@link AnexoDTO} para uma lista de instâncias da entidade {@link Anexo} 
	 * 
	 * @param anexosDTO lista de instâncias da entidade {@link AnexoDTO} 
	 * @return dto lista de instâncias da entidade {@link Anexo}
	 * 
	 */
	public List<Anexo> convertToModel(List<AnexoDTO> anexosDTO) {
		
		List<Anexo> anexos = new ArrayList<Anexo>();
		
		if(anexosDTO == null || anexosDTO.size() < 1){
			return anexos;
		}
		
		for(AnexoDTO anexoDTO : anexosDTO){
			Anexo anexo = new Anexo();
			
			anexo.setCodAnexo(anexoDTO.getCodAnexo());
			anexo.setDescricaoAnexo(anexoDTO.getDescricaoAnexo());
			anexo.setAliquotaAnexo(anexoDTO.getAliquotaAnexo());
			anexo.setStatusAnexo(anexoDTO.getStatusAnexo());

			anexos.add(anexo);
		}
		
		return anexos;
	}

	/**
	 * Método que executa a conversão de uma instância de {@link AnexoDTO} para uma instância da entidade {@link Anexo} 
	 * 
	 * @param anexo instãncia da entidade {@link Anexo} 
	 * @return dto instância de {@link AnexoDTO}
	 * 
	 */
	public Anexo convertToModel(AnexoDTO anexoDTO) {
		Anexo anexo = new Anexo();

		anexo.setCodAnexo(anexoDTO.getCodAnexo());
		anexo.setDescricaoAnexo(anexoDTO.getDescricaoAnexo());
		anexo.setAliquotaAnexo(anexoDTO.getAliquotaAnexo());
		anexo.setStatusAnexo(anexoDTO.getStatusAnexo());

		return anexo;
	}

}
