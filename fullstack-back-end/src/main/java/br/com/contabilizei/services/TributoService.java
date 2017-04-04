package br.com.contabilizei.services;

import java.util.ArrayList;
import java.util.List;
import br.com.contabilizei.dao.TributoDAO;
import br.com.contabilizei.dto.TributoDTO;
import br.com.contabilizei.model.Tributo;

/**
 * Classe responsável por fornecer métodos para aplicação de regras de negócio, processamento e conversão de dados envolvendo operações com tributos. 
 * 
 * @author Guilherme Henrique de Paula 
 * 
 */
public class TributoService {

	private TributoDAO daoTributo;

	public TributoService() {
		this.daoTributo = new TributoDAO();
	}

	/**
	 * Método Java que processa os dados de uma instância de {@link TributoDTO} para inserção de uma instância da entidade {@link Tributo}.
	 * 
	 * @param tributoDTO instância de {@link TributoDTO} contendo os dados da entidade {@link Tributo} que será inserida.
	 * 
	 */
	public void insert(TributoDTO tributoDTO) {
		Tributo tributo = convertToModel(tributoDTO);
		this.daoTributo.save(tributo);
	}

	/**
	 * Método Java que processa os dados de uma instância de {@link TributoDTO} para atualização de uma instância da entidade {@link Tributo}.
	 * 
	 * @param tributoDTO instância de {@link TributoDTO} contendo os dados da entidade {@link Tributo} que será atualizada.
	 */
	public void update(TributoDTO tributoDTO) {
		Tributo tributo = convertToModel(tributoDTO);
		this.daoTributo.update(tributo);
	}

	/**
	 * Método que lista todas as entidades de {@link Tributo} e realiza a conversão de dados para uma {@link List<TributoDTO>}.
	 * 
	 * @return regimesTributariosDTO lista de {@link TributoDTO}
	 */
	public List<TributoDTO> findAll() {

		List<Tributo> tiposImposto = this.daoTributo.findAll();

		List<TributoDTO> clientesDTO = new ArrayList<TributoDTO>();
		for (Tributo tributo : tiposImposto) {
			TributoDTO tributoDTO = convertToDTO(tributo);
			clientesDTO.add(tributoDTO);
		}
		return clientesDTO;
	}

	/**
	 * Método que busca uma instância da entidade {@link Tributo} com base no seu código identificador e realiza a conversão de dados para uma instância de {@link TributoDTO}.
	 * 
	 * @return tributoDTO instância de {@link TributoDTO} ou {@link <code>null</code>} caso nenhum registro seja encontrado.
	 */
	public TributoDTO findById(Long codTributo) {

		Tributo tributo = this.daoTributo.find(codTributo);

		if (tributo != null) {
			TributoDTO tributoDTO = convertToDTO(tributo);
			return tributoDTO;
		}
		return null;
	}


	/**
	 * Método que executa a conversão de uma instância da entidade {@link Tributo} para uma instância de {@link TributoDTO}
	 * 
	 * @param tributo instância da entidade {@link Tributo}
	 * @return dto instância de {@link TributoDTO}
	 * 
	 */
	public TributoDTO convertToDTO(Tributo tributo) {
		TributoDTO dto = new TributoDTO();
		
		dto.setCodTributo(tributo.getCodTributo());
		dto.setDescricaoTributo(tributo.getDescricaoTributo());
		dto.setAliquotaTributo(tributo.getAliquotaTributo());

		return dto;
	}
	
	/**
	 * Método que executa a conversão de uma instância da entidade {@link Tributo} para uma instância de {@link TributoDTO}
	 * 
	 * @param tributos lista de instâncias da entidade {@link Tributo}
	 * @return tributosDTO lista instâncias de {@link TributoDTO}
	 * 
	 */
	public List<TributoDTO> convertToDTO(List<Tributo> tributos) {
		
		List<TributoDTO> tributosDTO = new ArrayList<TributoDTO>();
		
		for(Tributo tributo : tributos){
			TributoDTO dto = new TributoDTO();
			
			dto.setCodTributo(tributo.getCodTributo());
			dto.setDescricaoTributo(tributo.getDescricaoTributo());
			dto.setAliquotaTributo(tributo.getAliquotaTributo());
			
			tributosDTO.add(dto);
		}

		return tributosDTO;
	}

	/**
	 * Método que executa a conversão de uma instância de {@link TributoDTO} para uma instância da entidade {@link Tributo} 
	 * 
	 * @param tributoDTO instância de @{link {@link TributoDTO} 
	 * @return tributo instância da entidade {@link Tributo}
	 * 
	 */
	public Tributo convertToModel(TributoDTO tributoDTO) {
		Tributo tributo = new Tributo();

		tributo.setCodTributo(tributoDTO.getCodTributo());
		tributo.setDescricaoTributo(tributoDTO.getDescricaoTributo());
		tributo.setAliquotaTributo(tributoDTO.getAliquotaTributo());
		
		return tributo;
	}
	
	/**
	 * Método que executa a conversão de uma instância de {@link TributoDTO} para uma instância da entidade {@link Tributo} 
	 * 
	 * @param tributoDTO  lista de instâncias de @{link {@link TributoDTO} 
	 * @return tributos lista instância da entidade {@link Tributo}
	 * 
	 */
	public List<Tributo> convertToModel(List<TributoDTO> tributosDTO) {
		
		List<Tributo> tributos = new ArrayList<Tributo>();

		for(TributoDTO tributoDTO : tributosDTO){
			Tributo tributo = new Tributo();
			tributo.setCodTributo(tributoDTO.getCodTributo());
			tributo.setDescricaoTributo(tributoDTO.getDescricaoTributo());
			tributo.setAliquotaTributo(tributoDTO.getAliquotaTributo());
			
			tributos.add(tributo);
		}
		
		return tributos;
	}

}
