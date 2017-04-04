package br.com.contabilizei.services;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import br.com.contabilizei.adapter.YearMonthAdapter;
import br.com.contabilizei.dao.NotaFiscalDAO;
import br.com.contabilizei.dto.AnexoDTO;
import br.com.contabilizei.dto.ClienteDTO;
import br.com.contabilizei.dto.NotaFiscalDTO;
import br.com.contabilizei.model.NotaFiscal;

/**
 * Classe responsável por fornecer métodos para aplicação de regras de negócio, processamento e conversão de dados envolvendo operações com notas fiscais. 
 * 
 * @author Guilherme Henrique de Paula 
 * 
 */
public class NotaFiscalService {

	private NotaFiscalDAO daoNotaFiscal;
	
	private AnexoService anexoService;
	
	private ClienteService clienteService;
	
	private YearMonthAdapter yearMonthAdapter;

	public NotaFiscalService() {
		this.daoNotaFiscal = new NotaFiscalDAO();
		this.anexoService = new AnexoService();
		this.clienteService = new ClienteService();
		this.yearMonthAdapter = new YearMonthAdapter();
	}

	/**
	 * Método Java que processa os dados de uma instância de {@link notaFiscalDTO} para inserção de uma instância da entidade {@link NotaFiscal}.
	 * 
	 * @param notaFiscalDTO instância de {@link NotaFiscalDTO} contendo os dados da entidade {@link NotaFiscal} que será inserida.
	 * 
	 */
	public void insert(NotaFiscalDTO notaFiscalDTO) {
		NotaFiscal notaFiscal = convertToModel(notaFiscalDTO);
		this.daoNotaFiscal.save(notaFiscal);
	}

	/**
	 * Método Java que processa os dados de uma instância de {@link NotaFiscalDTO} para atualização de uma instância da entidade {@link NotaFiscal}.
	 * 
	 * @param notaFiscalDTO instância de {@link NotaFiscalDTO} contendo os dados da entidade {@link NotaFiscal} que será atualizada.
	 */
	public void update(NotaFiscalDTO notaFiscalDTO) {
		NotaFiscal notaFiscal = convertToModel(notaFiscalDTO);
		this.daoNotaFiscal.update(notaFiscal);
	}

	/**
	 * Método que lista todas as entidades de {@link NotaFiscal} e realiza a conversão de dados para uma lista de instãncias de {@link NotaFiscalDTO}.
	 * 
	 * @return notasFiscaisDTO lista de instâncias {@link NotaFiscalDTO}
	 */
	public List<NotaFiscalDTO> findAll() {

		List<NotaFiscal> notas = this.daoNotaFiscal.findAll();

		List<NotaFiscalDTO> notasFiscaisDTO = new ArrayList<NotaFiscalDTO>();
		for (NotaFiscal notaFiscal : notas) {
			NotaFiscalDTO notaFiscalDTO = convertToDTO(notaFiscal);
			notasFiscaisDTO.add(notaFiscalDTO);
		}
		return notasFiscaisDTO;
	}

	/**
	 * Método que busca uma instância da entidade {@link NotaFiscal} com base no seu código identificador e realiza a conversão de dados para uma instância de {@link NotaFiscalDTO}.
	 * 
	 * @param idNotaFiscal código identificador da nota fiscal.
	 * @return notaFiscalDTO instância de {@link NotaFiscalDTO} ou {@link <code>null</code>} caso nenhum registro seja encontrado.
	 */
	public NotaFiscalDTO findById(Long idNotaFiscal) {

		NotaFiscal notaFiscal = this.daoNotaFiscal.find(idNotaFiscal);

		if (notaFiscal != null) {
			NotaFiscalDTO notaFiscalDTO = convertToDTO(notaFiscal);
			return notaFiscalDTO;
		}
		return null;
	}
	
	/**
	 * Método que busca uma lista de instâncias da entidade {@link NotaFiscal} com base no código do cliente 
	 * e realiza a conversão de dados para uma lista de instâncias de {@link NotaFiscalDTO}.
	 * 
	 * @param codCliente código do cliente que será parâmetro para busca da lista de instâncias de {@link NotaFiscal}.  
	 * 
	 * @return notasFiscaisDTO lista de instâncias de {@link NotaFiscalDTO}.
	 * 
	 */
	public List<NotaFiscalDTO> findByCodCliente(Long codCliente) {
		
		List<NotaFiscal> notasFiscais = this.daoNotaFiscal.findByCodCliente(codCliente);

		List<NotaFiscalDTO> notasFiscaisDTO = new ArrayList<NotaFiscalDTO>();
		for (NotaFiscal notaFiscal : notasFiscais) {
			NotaFiscalDTO notaFiscalDTO = convertToDTO(notaFiscal);
			notasFiscaisDTO.add(notaFiscalDTO);
		}
		return notasFiscaisDTO;
	}
	
	/**
	 * Método que busca uma lista de instâncias da entidade {@link NotaFiscal} com base no código do cliente e mês/ano 
	 * e realiza a conversão de dados para uma lista de instâncias de {@link NotaFiscalDTO}.
	 * 
	 * @param codCliente código do cliente que será parâmetro para busca da lista de instâncias de {@link NotaFiscal}.  
	 * @param month que será parâmetro para busca da lista de instâncias de {@link NotaFiscal}.
	 * 
	 * @return notasFiscaisDTO lista de instâncias de {@link NotaFiscalDTO}.
	 * 
	 */
	public List<NotaFiscalDTO> findByCodClienteAndMes(Long codCliente, YearMonth month) {
		
		LocalDate dataInicial = month.atDay(1);
		LocalDate dataFinal = month.atEndOfMonth();
		
		List<NotaFiscal> notasFiscais = this.daoNotaFiscal.findByCodClienteAndMes(codCliente, dataInicial, dataFinal);

		List<NotaFiscalDTO> notasFiscaisDTO = new ArrayList<NotaFiscalDTO>();
		for (NotaFiscal notaFiscal : notasFiscais) {
			NotaFiscalDTO notaFiscalDTO = convertToDTO(notaFiscal);
			notasFiscaisDTO.add(notaFiscalDTO);
		}
		return notasFiscaisDTO;
	}
	
	/**
	 * Método que busca uma lista de instâncias da entidade {@link NotaFiscal} com base no código do cliente, mês e ano 
	 * e realiza a conversão de dados para uma lista de instâncias de {@link NotaFiscalDTO}.
	 * 
	 * @param codCliente código do cliente que será parâmetro para busca da lista de instâncias de {@link NotaFiscal}.  
	 * @param mes que será parâmetro para busca da lista de instâncias de {@link NotaFiscal}.
	 * @param ano que será parâmetro para busca da lista de instâncias de {@link NotaFiscal}.
	 * 
	 * @return notasFiscaisDTO lista de instâncias de {@link NotaFiscalDTO}.
	 * 
	 */
	public List<NotaFiscalDTO> findByCodClienteAndMes(Long codCliente, String month, String year) throws Exception {
		
		String yearMth = month + "/" + year;
		YearMonth ymt = this.yearMonthAdapter.unmarshal(yearMth);
		
		LocalDate dataInicial = ymt.atDay(1);
		LocalDate dataFinal = ymt.atEndOfMonth();
		
		List<NotaFiscal> notasFiscais = this.daoNotaFiscal.findByCodClienteAndMes(codCliente, dataInicial, dataFinal);

		List<NotaFiscalDTO> notasFiscaisDTO = new ArrayList<NotaFiscalDTO>();
		for (NotaFiscal notaFiscal : notasFiscais) {
			NotaFiscalDTO notaFiscalDTO = convertToDTO(notaFiscal);
			notasFiscaisDTO.add(notaFiscalDTO);
		}
		return notasFiscaisDTO;
	}
	
	/**
	 * Método que busca uma lista de instâncias da entidade {@link NotaFiscal} com base no número da nota
	 * e realiza a conversão de dados para uma lista de instâncias de {@link NotaFiscalDTO}.
	 * 
	 * @param numNota número da nota que será parâmetro para busca da lista de instâncias de {@link NotaFiscal}.  
	 * 
	 * @return notasFiscaisDTO lista de instâncias de {@link NotaFiscalDTO}.
	 * 
	 */
	public List<NotaFiscalDTO> findByNumNota(Long numNota) {
		
		List<NotaFiscal> notasFiscais = this.daoNotaFiscal.findByNumNota(numNota);

		List<NotaFiscalDTO> notasFiscaisDTO = new ArrayList<NotaFiscalDTO>();
		for (NotaFiscal notaFiscal : notasFiscais) {
			NotaFiscalDTO notaFiscalDTO = convertToDTO(notaFiscal);
			notasFiscaisDTO.add(notaFiscalDTO);
		}
		return notasFiscaisDTO;
	}

	/**
	 * Método que executa a conversão de uma instância da entidade {@link NotaFiscal} para uma instância de {@link NotaFiscalDTO}
	 * 
	 * @param notaFiscal instância da entidade {@link NotaFiscal}
	 * @return notaFiscalDTO instância de {@link NotaFiscalDTO}
	 * 
	 */
	public NotaFiscalDTO convertToDTO(NotaFiscal notaFiscal) {
		
		NotaFiscalDTO notaFiscalDTO = new NotaFiscalDTO();
		AnexoDTO anexoDTO = anexoService.convertoToDTO(notaFiscal.getAnexo());
		ClienteDTO clienteDTO = clienteService.convertToDTO(notaFiscal.getCliente());
		
		notaFiscalDTO.setIdNotaFiscal(notaFiscal.getIdNotaFiscal());
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

	/**
	 * Método que executa a conversão de uma instância da entidade {@link NotaFiscalDTO} para uma instância de {@link NotaFiscal}
	 * 
	 * @param notaFiscalDTO instância da entidade {@link NotaFiscalDTO}
	 * @return notaFiscal instância de {@link NotaFiscal}
	 * 
	 */
	public NotaFiscal convertToModel(NotaFiscalDTO notaFiscalDTO) {
		
		NotaFiscal notaFiscal = new NotaFiscal();
		
		notaFiscal.setIdNotaFiscal(notaFiscalDTO.getIdNotaFiscal());
		notaFiscal.setNumNotaFiscal(notaFiscalDTO.getNumNotaFiscal());;
		notaFiscal.setCodCliente(notaFiscalDTO.getCodCliente());;
		notaFiscal.setCodAnexo(notaFiscalDTO.getCodAnexo());
		notaFiscal.setDataEmissao(notaFiscalDTO.getDataEmissao());
		notaFiscal.setValorNotaFiscal(notaFiscalDTO.getValorNotaFiscal());
		notaFiscal.setDescricaoNotaFiscal(notaFiscalDTO.getDescricaoNotaFiscal());
		
		return notaFiscal;
	}
}
