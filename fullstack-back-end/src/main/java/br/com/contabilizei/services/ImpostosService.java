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
	
	public List<ImpostoDTO> findAll(){
		
		List<Imposto> impostos = this.daoImpostos.findAll();
		List<ImpostoDTO> impostosDTO = new ArrayList<ImpostoDTO>();
		
		for(Imposto imposto : impostos){
			ImpostoDTO dto = convertToDTO(imposto);
			impostosDTO.add(dto);
		}
		
		return impostosDTO;
	}
	
	public List<ImpostoDTO> findByCodCliente(Long codCliente){
		
		List<Imposto> impostos = this.daoImpostos.findByCodCliente(codCliente);
		List<ImpostoDTO> impostosDTO = new ArrayList<ImpostoDTO>();
		
		for(Imposto imposto : impostos){
			ImpostoDTO dto = convertToDTO(imposto);
			impostosDTO.add(dto);
		}
		
		return impostosDTO;
	}
	
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

	public void update(ImpostoDTO impostoDTO) {
		Imposto imposto = convertToModel(impostoDTO);
		this.daoImpostos.update(imposto);
	}
	
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

	public ImpostoDTO findById(Long idImposto) {
		Imposto imposto = this.daoImpostos.find(idImposto);

		if (imposto != null) {
			ImpostoDTO impostoDTO = convertToDTO(imposto);
			return impostoDTO;
		}
		return null;
	}



}
