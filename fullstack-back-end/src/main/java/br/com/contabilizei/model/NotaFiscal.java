package br.com.contabilizei.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.contabilizei.converter.LocalDatePersistenceConverter;

@Entity
@Table(name="nota_fiscal")
public class NotaFiscal implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false, length = 11)
	private Long numNotaFiscal;
	
	@Column
	private Long codCliente;
	
	@Column
	private Long codAnexo;
	
	@Column
	@Convert(converter = LocalDatePersistenceConverter.class)
	private LocalDate dataEmissao;
	
	@Column
	private String descricaoNotaFiscal;
	
	@Column
	private BigDecimal valorNotaFiscal;
	
	@ManyToOne
	@JoinColumn(name="codCliente", referencedColumnName="idCliente", insertable=false, updatable=false)
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="codAnexo" , referencedColumnName="codAnexo", insertable=false, updatable=false)
	private Anexo anexo;

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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Anexo getAnexo() {
		return anexo;
	}

	public void setAnexo(Anexo anexo) {
		this.anexo = anexo;
	}

	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

}
