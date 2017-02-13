package br.com.contabilizei.services;

import java.util.ArrayList;
import java.util.List;
import br.com.contabilizei.dao.RegimeTributarioDAO;
import br.com.contabilizei.dto.RegimeTributarioDTO;
import br.com.contabilizei.model.RegimeTributario;

public class RegimeTributarioService {

	private RegimeTributarioDAO daoRegimeTributario;

	public RegimeTributarioService() {
		this.daoRegimeTributario = new RegimeTributarioDAO();
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

		List<RegimeTributario> tiposEmpresa = this.daoRegimeTributario.findAll();

		List<RegimeTributarioDTO> clientesDTO = new ArrayList<RegimeTributarioDTO>();
		for (RegimeTributario regimeTributario : tiposEmpresa) {
			RegimeTributarioDTO tipoEmpresaDTO = convertToDTO(regimeTributario);
			clientesDTO.add(tipoEmpresaDTO);
		}
		return clientesDTO;
	}

	public RegimeTributarioDTO findById(Long codTipoEmpresa) {

		RegimeTributario regimeTributario = this.daoRegimeTributario.find(codTipoEmpresa);

		if (regimeTributario != null) {
			RegimeTributarioDTO tipoEmpresaDTO = convertToDTO(regimeTributario);
			return tipoEmpresaDTO;
		}
		return null;
	}
	
	public List<RegimeTributarioDTO> findByStatus(Boolean status) {
		
		List<RegimeTributario> regsTributarios = this.daoRegimeTributario.findByStatus(status);

		List<RegimeTributarioDTO> regsTributariosDTO = new ArrayList<RegimeTributarioDTO>();
		for (RegimeTributario regimeTributario : regsTributarios) {
			RegimeTributarioDTO regimeTributarioDTO = convertToDTO(regimeTributario);
			regsTributariosDTO.add(regimeTributarioDTO);
		}
		return regsTributariosDTO;
	}

	public RegimeTributarioDTO convertToDTO(RegimeTributario regimeTributario) {
		RegimeTributarioDTO dto = new RegimeTributarioDTO();

		dto.setCodRegimeTributario(regimeTributario.getCodRegimeTributario());
		dto.setDescricaoRegimeTributario(regimeTributario.getDescricaoRegimeTributario());
		dto.setStatusRegimeTributario(regimeTributario.getStatusRegimeTributario());	

		return dto;
	}

	public RegimeTributario convertToModel(RegimeTributarioDTO regimeTributarioDTO) {
		RegimeTributario regimeTributario = new RegimeTributario();

		regimeTributario.setCodRegimeTributario(regimeTributarioDTO.getCodRegimeTributario());
		regimeTributario.setDescricaoRegimeTributario(regimeTributarioDTO.getDescricaoRegimeTributario());
		regimeTributario.setStatusRegimeTributario(regimeTributarioDTO.getStatusRegimeTributario());
		
		return regimeTributario;
	}

}
