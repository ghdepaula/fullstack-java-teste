package br.com.contabilizei.services;

import java.util.ArrayList;
import java.util.List;
import br.com.contabilizei.dao.TipoImpostoDAO;
import br.com.contabilizei.dto.TipoImpostoDTO;
import br.com.contabilizei.model.TipoImposto;

public class TipoImpostoService {

	private TipoImpostoDAO daoTipoImposto;

	public TipoImpostoService() {
		this.daoTipoImposto = new TipoImpostoDAO();
	}

	public void insert(TipoImpostoDTO tipoImpostoDTO) {
		TipoImposto tipoImposto = convertToModel(tipoImpostoDTO);
		this.daoTipoImposto.save(tipoImposto);
	}

	public void update(TipoImpostoDTO tipoImpostoDTO) {
		TipoImposto tipoImposto = convertToModel(tipoImpostoDTO);
		this.daoTipoImposto.update(tipoImposto);
	}

	public List<TipoImpostoDTO> findAll() {

		List<TipoImposto> tiposImposto = this.daoTipoImposto.findAll();

		List<TipoImpostoDTO> clientesDTO = new ArrayList<TipoImpostoDTO>();
		for (TipoImposto tipoImposto : tiposImposto) {
			TipoImpostoDTO tipoImpostoDTO = convertToDTO(tipoImposto);
			clientesDTO.add(tipoImpostoDTO);
		}
		return clientesDTO;
	}

	public TipoImpostoDTO findById(Long codTipoImposto) {

		TipoImposto tipoImposto = this.daoTipoImposto.find(codTipoImposto);

		if (tipoImposto != null) {
			TipoImpostoDTO tipoImpostoDTO = convertToDTO(tipoImposto);
			return tipoImpostoDTO;
		}
		return null;
	}


	private TipoImpostoDTO convertToDTO(TipoImposto tipoImposto) {
		TipoImpostoDTO dto = new TipoImpostoDTO();
		
		dto.setCodTipoImposto(tipoImposto.getCodTipoImposto());
		dto.setDescricaoTipoImposto(tipoImposto.getDescricaoTipoImposto());
		dto.setAliquotaTipoImposto(tipoImposto.getAliquotaTipoImposto());
		dto.setStatusTipoImposto(tipoImposto.getStatusTipoImposto());

		return dto;
	}

	private TipoImposto convertToModel(TipoImpostoDTO tipoImpostoDTO) {
		TipoImposto tipoImposto = new TipoImposto();

		tipoImposto.setCodTipoImposto(tipoImposto.getCodTipoImposto());
		tipoImposto.setDescricaoTipoImposto(tipoImposto.getDescricaoTipoImposto());
		tipoImposto.setAliquotaTipoImposto(tipoImposto.getAliquotaTipoImposto());
		tipoImposto.setStatusTipoImposto(tipoImposto.getStatusTipoImposto());
		
		return tipoImposto;
	}

}
