package br.com.contabilizei.services;

import java.util.ArrayList;
import java.util.List;

import br.com.contabilizei.dao.RegimeTributarioDAO;
import br.com.contabilizei.dto.RegimeTributarioDTO;
import br.com.contabilizei.dto.TributoDTO;
import br.com.contabilizei.model.RegimeTributario;
import br.com.contabilizei.model.Tributo;

public class RegimeTributarioService {

	private RegimeTributarioDAO daoRegimeTributario;
	
	private TributoService tributoService;

	public RegimeTributarioService() {
		this.daoRegimeTributario = new RegimeTributarioDAO();
		this.tributoService = new TributoService();
	}

	public void insert(RegimeTributarioDTO tipoEmpresaDTO) {
		RegimeTributario regimeTributario = convertToModel(tipoEmpresaDTO);
		this.daoRegimeTributario.save(regimeTributario);
	}

	public void update(RegimeTributarioDTO tipoEmpresaDTO) {
		RegimeTributario regimeTributario = convertToModel(tipoEmpresaDTO);
		this.daoRegimeTributario.update(regimeTributario);
	}

	public List<RegimeTributarioDTO> findAll() {

		List<RegimeTributario> regimesTributarios = this.daoRegimeTributario.findAll();

		List<RegimeTributarioDTO> regimesTributariosDTO = new ArrayList<RegimeTributarioDTO>();
		for (RegimeTributario regimeTributario : regimesTributarios) {
			RegimeTributarioDTO regimeTributarioDTO = convertToDTO(regimeTributario);
			regimesTributariosDTO.add(regimeTributarioDTO);
		}
		
		return regimesTributariosDTO;
	}

	public RegimeTributarioDTO findById(Long codTributo) {

		RegimeTributario regimeTributario = this.daoRegimeTributario.find(codTributo);

		if (regimeTributario != null) {
			RegimeTributarioDTO tipoEmpresaDTO = convertToDTO(regimeTributario);
			return tipoEmpresaDTO;
		}
		return null;
	}
	
	public RegimeTributarioDTO convertToDTO(RegimeTributario regimeTributario) {
		RegimeTributarioDTO dto = new RegimeTributarioDTO();
		List<TributoDTO> tributosDTO = this.tributoService.convertToDTO(regimeTributario.getTributos());

		dto.setCodRegimeTributario(regimeTributario.getCodRegimeTributario());
		dto.setDescricaoRegimeTributario(regimeTributario.getDescricaoRegimeTributario());
		dto.setTributos(tributosDTO);
		dto.setEnabledAnexos(regimeTributario.getEnabledAnexos());

		return dto;
	}

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
