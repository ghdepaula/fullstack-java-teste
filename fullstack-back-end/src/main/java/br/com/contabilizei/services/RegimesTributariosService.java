package br.com.contabilizei.services;

import java.util.ArrayList;
import java.util.List;

import br.com.contabilizei.dao.RegimeTributarioDAO;
import br.com.contabilizei.dto.RegimeTributarioDTO;
import br.com.contabilizei.dto.TributoDTO;
import br.com.contabilizei.model.RegimeTributario;
import br.com.contabilizei.model.Tributo;

/**
 * Classe responsável por fornecer métodos para aplicação de regras de negócio, processamento e conversão de dados envolvendo operações com regimes tributários. 
 * 
 * @author Guilherme Henrique de Paula 
 * 
 */
public class RegimesTributariosService {

	private RegimeTributarioDAO daoRegimeTributario;
	
	private TributoService tributoService;

	public RegimesTributariosService() {
		this.daoRegimeTributario = new RegimeTributarioDAO();
		this.tributoService = new TributoService();
	}

	/**
	 * Método Java que processa os dados de uma instância de {@link RegimeTributarioDTO} para inserção de uma instância da entidade {@link RegimeTributario}.
	 * 
	 * @param regimeTributarioDTO instância de {@link RegimeTributarioDTO} contendo os dados da entidade {@link RegimeTributario} que será inserida.
	 * 
	 */
	public void insert(RegimeTributarioDTO regimeTributarioDTO) {
		RegimeTributario regimeTributario = convertToModel(regimeTributarioDTO);
		this.daoRegimeTributario.save(regimeTributario);
	}
	
	/**
	 * Método Java que processa os dados de uma instância de {@link RegimeTributarioDTO} para atualização de uma instância da entidade {@link RegimeTributario}.
	 * 
	 * @param regimeTributarioDTO instância de {@link RegimeTributarioDTO} contendo os dados da entidade {@link RegimeTributario} que será atualizada.
	 */
	public void update(RegimeTributarioDTO regimeTributarioDTO) {
		RegimeTributario regimeTributario = convertToModel(regimeTributarioDTO);
		this.daoRegimeTributario.update(regimeTributario);
	}

	/**
	 * Método que lista todas as entidades de {@link RegimeTributario} e realiza a conversão de dados para uma {@link List<RegimeTributarioDTO>}.
	 * 
	 * @return regimesTributariosDTO lista de {@link RegimeTributarioDTO}
	 */
	public List<RegimeTributarioDTO> findAll() {

		List<RegimeTributario> regimesTributarios = this.daoRegimeTributario.findAll();

		List<RegimeTributarioDTO> regimesTributariosDTO = new ArrayList<RegimeTributarioDTO>();
		for (RegimeTributario regimeTributario : regimesTributarios) {
			RegimeTributarioDTO regimeTributarioDTO = convertToDTO(regimeTributario);
			regimesTributariosDTO.add(regimeTributarioDTO);
		}
		
		return regimesTributariosDTO;
	}

	/**
	 * Método que busca uma instância da entidade {@link RegimeTributario} com base no seu código identificador e realiza a conversão de dados para uma instância de {@link RegimeTributarioDTO}.
	 * 
	 * @param código identificado da entidade {@link RegimeTributario}
	 * @return regimeTributarioDTO instância de {@link RegimeTributarioDTO} ou {@link <code>null</code>} caso nenhum registro seja encontrado.
	 */
	public RegimeTributarioDTO findById(Long codTributo) {

		RegimeTributario regimeTributario = this.daoRegimeTributario.find(codTributo);

		if (regimeTributario != null) {
			RegimeTributarioDTO regimeTributarioDTO = convertToDTO(regimeTributario);
			return regimeTributarioDTO;
		}
		return null;
	}
	
	/**
	 * Método que executa a conversão de uma instância da entidade {@link RegimeTributario} para uma instância de {@link RegimeTributarioDTO}
	 * 
	 * @return dto instância de {@link RegimeTributarioDTO}
	 * 
	 */
	public RegimeTributarioDTO convertToDTO(RegimeTributario regimeTributario) {
		RegimeTributarioDTO dto = new RegimeTributarioDTO();
		List<TributoDTO> tributosDTO = this.tributoService.convertToDTO(regimeTributario.getTributos());

		dto.setCodRegimeTributario(regimeTributario.getCodRegimeTributario());
		dto.setDescricaoRegimeTributario(regimeTributario.getDescricaoRegimeTributario());
		dto.setTributos(tributosDTO);
		dto.setEnabledAnexos(regimeTributario.getEnabledAnexos());

		return dto;
	}

	/**
	 * Método que executa a conversão de uma instância de {@link RegimeTributarioDTO} para uma instância da entidade {@link RegimeTributario} 
	 * 
	 * @param regimeTributarioDTO instância de @{link {@link RegimeTributarioDTO} 
	 * @return regimeTributario instância da entidade {@link RegimeTributario}
	 * 
	 */
	public RegimeTributario convertToModel(RegimeTributarioDTO regimeTributarioDTO) {
		RegimeTributario regimeTributario = new RegimeTributario();
		List<Tributo> tributos = this.tributoService.convertToModel(regimeTributarioDTO.getTributos());

		regimeTributario.setCodRegimeTributario(regimeTributarioDTO.getCodRegimeTributario());
		regimeTributario.setDescricaoRegimeTributario(regimeTributarioDTO.getDescricaoRegimeTributario());
		regimeTributario.setTributos(tributos);
		regimeTributario.setEnabledAnexos(regimeTributarioDTO.getEnabledAnexos());
		
		return regimeTributario;
	}

}
