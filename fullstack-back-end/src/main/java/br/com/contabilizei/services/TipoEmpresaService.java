package br.com.contabilizei.services;

import java.util.ArrayList;
import java.util.List;

import br.com.contabilizei.dao.TipoEmpresaDAO;
import br.com.contabilizei.dto.TipoEmpresaDTO;
import br.com.contabilizei.model.TipoEmpresa;

public class TipoEmpresaService {

	private TipoEmpresaDAO daoTipoEmpresa;

	public TipoEmpresaService() {
		this.daoTipoEmpresa = new TipoEmpresaDAO();
	}

	public void insert(TipoEmpresaDTO tipoEmpresaDTO) {
		TipoEmpresa tipoEmpresa = convertToModel(tipoEmpresaDTO);
		this.daoTipoEmpresa.save(tipoEmpresa);
	}

	public void update(TipoEmpresaDTO tipoEmpresaDTO) {
		TipoEmpresa tipoEmpresa = convertToModel(tipoEmpresaDTO);
		this.daoTipoEmpresa.update(tipoEmpresa);
	}

	public List<TipoEmpresaDTO> findAll() {

		List<TipoEmpresa> tiposEmpresa = this.daoTipoEmpresa.findAll();

		List<TipoEmpresaDTO> clientesDTO = new ArrayList<TipoEmpresaDTO>();
		for (TipoEmpresa tipoEmpresa : tiposEmpresa) {
			TipoEmpresaDTO tipoEmpresaDTO = convertToDTO(tipoEmpresa);
			clientesDTO.add(tipoEmpresaDTO);
		}
		return clientesDTO;
	}

	public TipoEmpresaDTO findById(Long codTipoEmpresa) {

		TipoEmpresa tipoEmpresa = this.daoTipoEmpresa.find(codTipoEmpresa);

		if (tipoEmpresa != null) {
			TipoEmpresaDTO tipoEmpresaDTO = convertToDTO(tipoEmpresa);
			return tipoEmpresaDTO;
		}
		return null;
	}


	private TipoEmpresaDTO convertToDTO(TipoEmpresa tipoEmpresa) {
		TipoEmpresaDTO dto = new TipoEmpresaDTO();

		dto.setCodTipoEmpresa(tipoEmpresa.getCodTipoEmpresa());
		dto.setDescricaoTipoEmpresa(tipoEmpresa.getDescricaoTipoEmpresa());
		dto.setStatusTipoEmpresa(tipoEmpresa.getStatusTipoEmpresa());	

		return dto;
	}

	private TipoEmpresa convertToModel(TipoEmpresaDTO tipoEmpresaDTO) {
		TipoEmpresa tipoEmpresa = new TipoEmpresa();

		tipoEmpresa.setCodTipoEmpresa(tipoEmpresa.getCodTipoEmpresa());
		tipoEmpresa.setDescricaoTipoEmpresa(tipoEmpresa.getDescricaoTipoEmpresa());
		tipoEmpresa.setStatusTipoEmpresa(tipoEmpresa.getStatusTipoEmpresa());
		
		return tipoEmpresa;
	}

}
