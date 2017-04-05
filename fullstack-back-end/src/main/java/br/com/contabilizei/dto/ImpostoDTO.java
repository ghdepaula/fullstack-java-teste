package br.com.contabilizei.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.contabilizei.adapter.BigDecimalAdapter;
import br.com.contabilizei.adapter.LocalDateAdapter;
import br.com.contabilizei.adapter.YearMonthAdapter;

/**
 * Classe responsável pela transferẽncia de dados entre as camadas da aplicação referente as operações do serviço de impostos. 
 * 
 * @author Guilherme Henrique de Paula 
 * 
 */
@XmlRootElement
public class ImpostoDTO {
	
	private Long idImposto;
	
	@XmlJavaTypeAdapter(value = YearMonthAdapter.class)
	private YearMonth mesAno;
	
	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	private LocalDate dataVencimento;
	
	@XmlJavaTypeAdapter(BigDecimalAdapter.class)
	private BigDecimal valorImposto;
	
	private Long codCliente;
	
	private Long codTributo;
	
	private Boolean statusPagamento;
	
	private ClienteDTO clienteDTO;
	
	private TributoDTO tributoDTO;

	public Long getIdImposto() {
		return idImposto;
	}

	public void setIdImposto(Long idImposto) {
		this.idImposto = idImposto;
	}

	public YearMonth getMesAno() {
		return mesAno;
	}

	public void setMesAno(YearMonth mesAno) {
		this.mesAno = mesAno;
	}

	public Long getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Long codCliente) {
		this.codCliente = codCliente;
	}

	public BigDecimal getValorImposto() {
		return valorImposto;
	}

	public void setValorImposto(BigDecimal valorImposto) {
		this.valorImposto = valorImposto;
	}

	public Long getCodTributo() {
		return codTributo;
	}

	public void setCodTributo(Long codTributo) {
		this.codTributo = codTributo;
	}

	public ClienteDTO getClienteDTO() {
		return clienteDTO;
	}

	public void setClienteDTO(ClienteDTO clienteDTO) {
		this.clienteDTO = clienteDTO;
	}

	public TributoDTO getTributoDTO() {
		return tributoDTO;
	}

	public void setTributoDTO(TributoDTO tributoDTO) {
		this.tributoDTO = tributoDTO;
	}

	public Boolean getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(Boolean statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

}
