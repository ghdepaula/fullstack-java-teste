package br.com.contabilizei.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.contabilizei.adapter.BigDecimalAdapter;
import br.com.contabilizei.adapter.LocalDateAdapter;

/**
 * Classe responsável pela transferẽncia de dados entre as camadas da aplicação referente as operações do serviço de notas fiscais. 
 * 
 * @author Guilherme Henrique de Paula 
 * 
 */
@XmlRootElement
public class NotaFiscalDTO {
	
	private Long idNotaFiscal;

	private Long numNotaFiscal;
	
	private Long codCliente;
	
	private Long codAnexo;

	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	private LocalDate dataEmissao;
	
	private String descricaoNotaFiscal;
	
	@XmlJavaTypeAdapter(BigDecimalAdapter.class) 
	private BigDecimal valorNotaFiscal;
	
	private ClienteDTO clienteDTO;
	
	private AnexoDTO anexoDTO;

	public Long getNumNotaFiscal() {
		return numNotaFiscal;
	}

	public void setNumNotaFiscal(Long numNotaFiscal) {
		this.numNotaFiscal = numNotaFiscal;
	}

	public String getDescricaoNotaFiscal() {
		return descricaoNotaFiscal;
	}

	public void setDescricaoNotaFiscal(String descricaoNotaFiscal) {
		this.descricaoNotaFiscal = descricaoNotaFiscal;
	}

	public BigDecimal getValorNotaFiscal() {
		return valorNotaFiscal;
	}

	public void setValorNotaFiscal(BigDecimal valorNotaFiscal) {
		this.valorNotaFiscal = valorNotaFiscal;
	}

	public Long getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Long codCliente) {
		this.codCliente = codCliente;
	}

	public Long getCodAnexo() {
		return codAnexo;
	}

	public void setCodAnexo(Long codAnexo) {
		this.codAnexo = codAnexo;
	}

	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public ClienteDTO getClienteDTO() {
		return clienteDTO;
	}

	public void setClienteDTO(ClienteDTO clienteDTO) {
		this.clienteDTO = clienteDTO;
	}

	public AnexoDTO getAnexoDTO() {
		return anexoDTO;
	}

	public void setAnexoDTO(AnexoDTO anexoDTO) {
		this.anexoDTO = anexoDTO;
	}

	public Long getIdNotaFiscal() {
		return idNotaFiscal;
	}

	public void setIdNotaFiscal(Long idNotaFiscal) {
		this.idNotaFiscal = idNotaFiscal;
	}

}
