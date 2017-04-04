package br.com.contabilizei.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import br.com.contabilizei.adapter.YearMonthAdapter;
import br.com.contabilizei.dao.ImpostosDAO;
import br.com.contabilizei.dto.ClienteDTO;
import br.com.contabilizei.dto.DadosImpostoDTO;
import br.com.contabilizei.dto.ImpostoDTO;
import br.com.contabilizei.dto.NotaFiscalDTO;
import br.com.contabilizei.dto.RegimeTributarioDTO;
import br.com.contabilizei.dto.TributoDTO;
import br.com.contabilizei.model.Imposto;

/**
 * Classe responsável por fornecer métodos para aplicação de regras de negócio, processamento e conversão de dados envolvendo operações com impostos. 
 * 
 * @author Guilherme Henrique de Paula 
 * 
 */
public class ImpostosService {
	
	private ImpostosDAO daoImpostos;
	
	private TributoService tributoService;
	
	private NotaFiscalService notaFiscalService;
	
	private ClienteService clienteService;
	
	private RegimesTributariosService regimesTributariosService;
	
	private YearMonthAdapter yearMonthAdapter;
	
	public ImpostosService(){
		this.daoImpostos = new ImpostosDAO();
		this.tributoService = new TributoService();
		this.notaFiscalService = new NotaFiscalService();
		this.clienteService = new ClienteService();
		this.regimesTributariosService = new RegimesTributariosService();
		this.yearMonthAdapter = new YearMonthAdapter();
	}
	
	/**
	 * Método Java que processa os dados de uma instância de {@link DadosImpostoDTO} para cáclculo e inserção de uma ou várias instâncias da entidade {@link Imposto}.
	 * 
	 * @param dadosImposto instância de {@link DadosImpostoDTO} contendo os dados da entidade {@link Imposto} que será calculada e inserida.
	 * 
	 */
	public void calculate(DadosImpostoDTO dadosImposto) {
		
		List<Imposto> impostos = new ArrayList<Imposto>();
		
		Long codCli = dadosImposto.getCodCliente();
		YearMonth month = dadosImposto.getYearMonth();
		
		List<NotaFiscalDTO> notasFiscais = this.notaFiscalService.findByCodClienteAndMes(codCli, month);

		if(notasFiscais != null && !notasFiscais.isEmpty() && notasFiscais.size() > 0){
			impostos = calculateImpostos(notasFiscais, dadosImposto);
		}

		if (impostos != null && !impostos.isEmpty() && impostos.size() > 0) {
			for(Imposto imposto : impostos){
				daoImpostos.save(imposto);
			}
		}
	}
	
	/**
	 * Método Java que processa uma lista de instâncias {@link NotaFiscalDTO} e os dados de uma instância de {@link DadosImpostoDTO} executando o cálculo de impostos mensal.
	 * 
	 * @param notasFiscais lista de instâncias de{@link NotaFiscalDTO} do mês.
	 * @param dadosImposto instância de {@link DadosImpostoDTO} necessários para execução do cálculo de impostos. 
	 * 
	 * @return impostos lista de instâncias de {@link Imposto}
	 * 
	 */
	private List<Imposto> calculateImpostos(List<NotaFiscalDTO> notasFiscais, DadosImpostoDTO dadosImposto){
		
		ClienteDTO clienteDTO = this.clienteService.findById(dadosImposto.getCodCliente());
		RegimeTributarioDTO regTributarioDTO = this.regimesTributariosService.findById(clienteDTO.getCodRegimeTributario());
		
		List<Imposto> impostos = new ArrayList<Imposto>();
		List<TributoDTO> tributos = regTributarioDTO.getTributos();
		Boolean enabledAnexos = clienteDTO.getRegimeTributario().getEnabledAnexos();
		
		for(TributoDTO tributo : tributos){
			
			BigDecimal totalTributo = BigDecimal.ZERO;
			BigDecimal aliquota = BigDecimal.ZERO;
			BigDecimal divisor = new BigDecimal(100);
			LocalDate dataVencimento = dadosImposto.getYearMonth().plusMonths(1).atDay(25);
			
			for (NotaFiscalDTO notaFiscal : notasFiscais) {
				
				if(enabledAnexos){
					aliquota = new BigDecimal(notaFiscal.getAnexoDTO().getAliquotaAnexo());
				}else{
					aliquota = new BigDecimal(tributo.getAliquotaTributo());
				}
				
				BigDecimal valorNota = notaFiscal.getValorNotaFiscal();
				BigDecimal valorTributo = valorNota.multiply(aliquota).divide(divisor);

				totalTributo = totalTributo.add(valorTributo);
			}
			
			Imposto imposto = new Imposto();
			imposto.setCodCliente(dadosImposto.getCodCliente());
			imposto.setYearMonth(dadosImposto.getYearMonth());
			imposto.setDataVencimento(dataVencimento);
			imposto.setCodTributo(tributo.getCodTributo());
			imposto.setStatusPagamento(Boolean.FALSE);
			imposto.setValorImposto(totalTributo);
			
			impostos.add(imposto);
			
		}
		
		return impostos;
	}
	
	/**
	 * Método Java que processa os dados de uma instância de {@link ImpostoDTO} para atualização de uma instância da entidade {@link Imposto}.
	 * 
	 * @param impostoDTO instância de {@link ImpostoDTO} contendo os dados da entidade {@link Imposto} que será atualizada.
	 */
	public void update(ImpostoDTO impostoDTO) {
		Imposto imposto = convertToModel(impostoDTO);
		this.daoImpostos.update(imposto);
	}
	
	/**
	 * Método que lista todas as entidades de {@link Imposto} e realiza a conversão de dados para uma {@link List<ImpostoDTO>}.
	 * 
	 * @return regimesTributariosDTO lista de {@link ImpostoDTO}
	 */
	public List<ImpostoDTO> findAll(){
		
		List<Imposto> impostos = this.daoImpostos.findAll();
		List<ImpostoDTO> impostosDTO = new ArrayList<ImpostoDTO>();
		
		for(Imposto imposto : impostos){
			ImpostoDTO dto = convertToDTO(imposto);
			impostosDTO.add(dto);
		}
		
		return impostosDTO;
	}
	
	/**
	 * Método que busca uma instância da entidade {@link Imposto} com base no seu código identificador e realiza a conversão de dados para uma instância de {@link ImpostoDTO}.
	 * 
	 * @return impostoDTO instância de {@link ImpostoDTO} ou {@link <code>null</code>} caso nenhum registro seja encontrado.
	 */
	public ImpostoDTO findById(Long idImposto) {
		Imposto imposto = this.daoImpostos.find(idImposto);

		if (imposto != null) {
			ImpostoDTO impostoDTO = convertToDTO(imposto);
			return impostoDTO;
		}
		return null;
	}
	
	/**
	 * Método que busca uma lista de instâncias da entidade {@link Imposto} com base no código do cliente 
	 * e realiza a conversão de dados para uma lista de instâncias de {@link ImpostoDTO}.
	 * 
	 * @param codCliente código do cliente que será parâmetro para busca da lista de instâncias de {@link Imposto}.  
	 * 
	 * @return impostosDTO lista de instâncias de {@link ImpostoDTO}.
	 * 
	 */
	public List<ImpostoDTO> findByCodCliente(Long codCliente){
		
		List<Imposto> impostos = this.daoImpostos.findByCodCliente(codCliente);
		List<ImpostoDTO> impostosDTO = new ArrayList<ImpostoDTO>();
		
		for(Imposto imposto : impostos){
			ImpostoDTO dto = convertToDTO(imposto);
			impostosDTO.add(dto);
		}
		
		return impostosDTO;
	}
	
	/**
	 * Método que busca uma lista de instâncias da entidade {@link Imposto} com base no código do cliente, mês e ano 
	 * e realiza a conversão de dados para uma lista de instâncias de {@link ImpostoDTO}.
	 * 
	 * @param codCliente código do cliente que será parâmetro para busca da lista de instâncias de {@link Imposto}.  
	 * @param mes que será parâmetro para busca da lista de instâncias de {@link Imposto}.
	 * @param ano que será parâmetro para busca da lista de instâncias de {@link Imposto}.
	 * 
	 * @return impostosDTO lista de instâncias de {@link ImpostoDTO}.
	 * 
	 */
	public List<ImpostoDTO> findByCodClienteMesAno(Long codCliente, String mes, String ano) throws Exception {
		
		String yearMth = mes + "/" + ano;
		YearMonth month = this.yearMonthAdapter.unmarshal(yearMth);
		
		List<Imposto> impostos = this.daoImpostos.findByCodClienteMes(codCliente, month);
		List<ImpostoDTO> impostosDTO = new ArrayList<ImpostoDTO>();
		
		for(Imposto imposto : impostos){
			ImpostoDTO dto = convertToDTO(imposto);
			impostosDTO.add(dto);
		}
		
		return impostosDTO;
	}
	
	/**
	 * Método que executa a conversão de uma instância da entidade {@link Imposto} para uma instância de {@link ImpostoDTO}
	 * 
	 * @param tributos lista de instâncias da entidade {@link Imposto}
	 * @return tributosDTO lista instâncias de {@link ImpostoDTO}
	 * 
	 */
	public ImpostoDTO convertToDTO(Imposto imposto){
	
		ImpostoDTO dto = new ImpostoDTO();
		TributoDTO tributoDTO = this.tributoService.convertToDTO(imposto.getTributo());
		ClienteDTO clienteDTO = this.clienteService.convertToDTO(imposto.getCliente());
		
		dto.setIdImposto(imposto.getIdImposto());
		dto.setYearMonth(imposto.getYearMonth());
		dto.setDataVencimento(imposto.getDataVencimento());
		dto.setValorImposto(imposto.getValorImposto());
		dto.setStatusPagamento(imposto.getStatusPagamento());
		dto.setCodCliente(imposto.getCodCliente());
		dto.setCodTributo(imposto.getCodTributo());
		dto.setTributoDTO(tributoDTO);
		dto.setClienteDTO(clienteDTO);
		
		return dto;
	}

	/**
	 * Método que executa a conversão de uma instância de {@link ImpostoDTO} para uma instância da entidade {@link Imposto} 
	 * 
	 * @param dto instância de @{link {@link ImpostoDTO} 
	 * @return imposto instância da entidade {@link Imposto}
	 * 
	 */
	public Imposto convertToModel(ImpostoDTO dto){
		
		Imposto imposto = new Imposto();
		
		imposto.setIdImposto(dto.getIdImposto());
		imposto.setYearMonth(dto.getYearMonth());
		imposto.setDataVencimento(dto.getDataVencimento());
		imposto.setValorImposto(dto.getValorImposto());
		imposto.setStatusPagamento(dto.getStatusPagamento());
		imposto.setCodCliente(dto.getCodCliente());
		imposto.setCodTributo(dto.getCodTributo());
		
		return imposto;
	}	
}
