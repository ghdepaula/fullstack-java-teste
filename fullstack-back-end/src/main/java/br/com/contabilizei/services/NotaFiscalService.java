package br.com.contabilizei.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.contabilizei.dao.NotaFiscalDAO;
import br.com.contabilizei.dto.AnexoDTO;
import br.com.contabilizei.dto.ClienteDTO;
import br.com.contabilizei.dto.NotaFiscalDTO;
import br.com.contabilizei.model.NotaFiscal;

public class NotaFiscalService {

	private NotaFiscalDAO daoNotaFiscal;
	
	private AnexoService anexoService;
	
	private ClienteService clienteService;

	public NotaFiscalService() {
		this.daoNotaFiscal = new NotaFiscalDAO();
		this.anexoService = new AnexoService();
		this.clienteService = new ClienteService();
	}

	public void insert(NotaFiscalDTO notaFiscalDTO) {
		NotaFiscal notaFiscal = convertToModel(notaFiscalDTO);
		this.daoNotaFiscal.save(notaFiscal);
	}

	public void update(NotaFiscalDTO notaFiscalDTO) {
		NotaFiscal notaFiscal = convertToModel(notaFiscalDTO);
		this.daoNotaFiscal.update(notaFiscal);
	}

	public List<NotaFiscalDTO> findAll() {

		List<NotaFiscal> notas = this.daoNotaFiscal.findAll();

		List<NotaFiscalDTO> notasFiscaisDTO = new ArrayList<NotaFiscalDTO>();
		for (NotaFiscal notaFiscal : notas) {
			NotaFiscalDTO notaFiscalDTO = convertToDTO(notaFiscal);
			notasFiscaisDTO.add(notaFiscalDTO);
		}
		return notasFiscaisDTO;
	}
	
	public boolean remove(Long numNotaFiscal) {

		NotaFiscal notaFiscal = (NotaFiscal) this.daoNotaFiscal.find(numNotaFiscal);
		if (notaFiscal == null) {
			return false;
		}
		Long id = notaFiscal.getNumNotaFiscal();
		this.daoNotaFiscal.delete(id, NotaFiscal.class);

		return true;
	}

	public NotaFiscalDTO findById(Long numNotaFiscal) {

		NotaFiscal notaFiscal = (NotaFiscal) this.daoNotaFiscal.find(numNotaFiscal);

		if (notaFiscal != null) {
			NotaFiscalDTO notaFiscalDTO = convertToDTO(notaFiscal);
			return notaFiscalDTO;
		}
		return null;
	}
	
	public List<NotaFiscalDTO> findByCodCliente(Long codCliente) {
		
		List<NotaFiscal> notasFiscais = this.daoNotaFiscal.findByCodCliente(codCliente);

		List<NotaFiscalDTO> notasFiscaisDTO = new ArrayList<NotaFiscalDTO>();
		for (NotaFiscal notaFiscal : notasFiscais) {
			NotaFiscalDTO notaFiscalDTO = convertToDTO(notaFiscal);
			notasFiscaisDTO.add(notaFiscalDTO);
		}
		return notasFiscaisDTO;
	}
	
	public List<NotaFiscalDTO> findByCodClienteAndPeriodo(Long codCliente, LocalDate dataInicial, LocalDate dataFinal) {
		
		List<NotaFiscal> notasFiscais = this.daoNotaFiscal.findByCodClienteAndPeriodo(codCliente, dataInicial, dataFinal);

		List<NotaFiscalDTO> notasFiscaisDTO = new ArrayList<NotaFiscalDTO>();
		for (NotaFiscal notaFiscal : notasFiscais) {
			NotaFiscalDTO notaFiscalDTO = convertToDTO(notaFiscal);
			notasFiscaisDTO.add(notaFiscalDTO);
		}
		return notasFiscaisDTO;
	}


	public NotaFiscalDTO convertToDTO(NotaFiscal notaFiscal) {
		NotaFiscalDTO notaFiscalDTO = new NotaFiscalDTO();
		AnexoDTO anexoDTO = anexoService.convertoToDTO(notaFiscal.getAnexo());
		ClienteDTO clienteDTO = clienteService.convertToDTO(notaFiscal.getCliente());
		
		notaFiscalDTO.setNumNotaFiscal(notaFiscal.getNumNotaFiscal());;
		notaFiscalDTO.setCodCliente(notaFiscal.getCodCliente());;
		notaFiscalDTO.setCodAnexo(notaFiscal.getCodAnexo());
		notaFiscalDTO.setDataEmissao(notaFiscal.getDataEmissao());
		notaFiscalDTO.setValorNotaFiscal(notaFiscal.getValorNotaFiscal());
		notaFiscalDTO.setDescricaoNotaFiscal(notaFiscal.getDescricaoNotaFiscal());
		notaFiscalDTO.setAnexoDTO(anexoDTO);
		notaFiscalDTO.setClienteDTO(clienteDTO);

		return notaFiscalDTO;
	}

	public NotaFiscal convertToModel(NotaFiscalDTO notaFiscalDTO) {
		NotaFiscal notaFiscal = new NotaFiscal();
		
		notaFiscal.setNumNotaFiscal(notaFiscalDTO.getNumNotaFiscal());;
		notaFiscal.setCodCliente(notaFiscalDTO.getCodCliente());;
		notaFiscal.setCodAnexo(notaFiscalDTO.getCodAnexo());
		notaFiscal.setDataEmissao(notaFiscalDTO.getDataEmissao());
		notaFiscal.setValorNotaFiscal(notaFiscalDTO.getValorNotaFiscal());
		notaFiscal.setDescricaoNotaFiscal(notaFiscalDTO.getDescricaoNotaFiscal());

		return notaFiscal;
	}


}
