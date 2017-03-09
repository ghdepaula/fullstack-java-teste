package br.com.contabilizei.services;

import java.util.ArrayList;
import java.util.List;

import br.com.contabilizei.dao.TributoDAO;
import br.com.contabilizei.dto.TributoDTO;
import br.com.contabilizei.model.Tributo;

public class TributoService {

	private TributoDAO daoTributo;

	public TributoService() {
		this.daoTributo = new TributoDAO();
	}

	public void insert(TributoDTO tributoDTO) {
		Tributo tributo = convertToModel(tributoDTO);
		this.daoTributo.save(tributo);
	}

	public void update(TributoDTO tributoDTO) {
		Tributo tributo = convertToModel(tributoDTO);
		this.daoTributo.update(tributo);
	}

	public List<TributoDTO> findAll() {

		List<Tributo> tiposImposto = this.daoTributo.findAll();

		List<TributoDTO> clientesDTO = new ArrayList<TributoDTO>();
		for (Tributo tributo : tiposImposto) {
			TributoDTO tributoDTO = convertToDTO(tributo);
			clientesDTO.add(tributoDTO);
		}
		return clientesDTO;
	}

	public TributoDTO findById(Long codTributo) {

		Tributo tributo = this.daoTributo.find(codTributo);

		if (tributo != null) {
			TributoDTO tributoDTO = convertToDTO(tributo);
			return tributoDTO;
		}
		return null;
	}


	public TributoDTO convertToDTO(Tributo tributo) {
		TributoDTO dto = new TributoDTO();
		
		dto.setCodTributo(tributo.getCodTributo());
		dto.setDescricaoTributo(tributo.getDescricaoTributo());
		dto.setAliquotaTributo(tributo.getAliquotaTributo());

		return dto;
	}
	
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

	public Tributo convertToModel(TributoDTO tributoDTO) {
		Tributo tributo = new Tributo();

		tributo.setCodTributo(tributoDTO.getCodTributo());
		tributo.setDescricaoTributo(tributoDTO.getDescricaoTributo());
		tributo.setAliquotaTributo(tributoDTO.getAliquotaTributo());
		
		return tributo;
	}
	
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
