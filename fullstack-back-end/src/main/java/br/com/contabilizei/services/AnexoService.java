package br.com.contabilizei.services;

import java.util.ArrayList;
import java.util.List;

import br.com.contabilizei.dao.AnexoDAO;
import br.com.contabilizei.dto.AnexoDTO;
import br.com.contabilizei.model.Anexo;

public class AnexoService {
	
	private AnexoDAO daoAnexo;

	public AnexoService() {
		this.daoAnexo = new AnexoDAO();
	}

	public void insert(AnexoDTO anexoDTO) {
		Anexo anexo = convertToModel(anexoDTO);
		this.daoAnexo.save(anexo);
	}

	public void update(AnexoDTO anexoDTO) {
		Anexo anexo = convertToModel(anexoDTO);
		this.daoAnexo.update(anexo);
	}

	public List<AnexoDTO> findAll() {

		List<Anexo> anexos = this.daoAnexo.findAll();

		List<AnexoDTO> anexosDTO = new ArrayList<AnexoDTO>();
		for (Anexo anexo : anexos) {
			AnexoDTO anexoDTO = convertToDTO(anexo);
			anexosDTO.add(anexoDTO);
		}
		return anexosDTO;
	}

	public AnexoDTO findById(Long codAnexo) {

		Anexo anexo = this.daoAnexo.find(codAnexo);

		if (anexo != null) {
			AnexoDTO anexoDTO = convertToDTO(anexo);
			return anexoDTO;
		}
		return null;
	}
	
	public List<AnexoDTO> findByStatus(Boolean status) {
		
		List<Anexo> anexos = this.daoAnexo.findByStatus(status);

		List<AnexoDTO> anexosDTO = new ArrayList<AnexoDTO>();
		for (Anexo anexo : anexos) {
			AnexoDTO anexoDTO = convertToDTO(anexo);
			anexosDTO.add(anexoDTO);
		}
		return anexosDTO;
	}

	public AnexoDTO convertToDTO(Anexo anexo) {
		AnexoDTO dto = new AnexoDTO();

		dto.setCodAnexo(anexo.getCodAnexo());
		dto.setDescricaoAnexo(anexo.getDescricaoAnexo());
		dto.setAliquotaAnexo(anexo.getAliquotaAnexo());
		dto.setStatusAnexo(anexo.getStatusAnexo());

		return dto;
	}
	
	public List<AnexoDTO> convertoToDto(List<Anexo> anexos){
		
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

	public Anexo convertToModel(AnexoDTO anexoDTO) {
		Anexo anexo = new Anexo();

		anexo.setCodAnexo(anexoDTO.getCodAnexo());
		anexo.setDescricaoAnexo(anexoDTO.getDescricaoAnexo());
		anexo.setAliquotaAnexo(anexoDTO.getAliquotaAnexo());
		anexo.setStatusAnexo(anexoDTO.getStatusAnexo());

		return anexo;
	}
	

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
}
