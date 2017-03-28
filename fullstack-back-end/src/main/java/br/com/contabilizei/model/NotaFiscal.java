package br.com.contabilizei.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="nota_fiscal")
@NamedQueries({
	 @NamedQuery(name="NotaFiscal.findByCodCliente", query="SELECT n FROM NotaFiscal n WHERE n.codCliente = :codCliente"),
	 @NamedQuery(name="NotaFiscal.findByCodClienteAndMes", query="SELECT n FROM NotaFiscal n WHERE n.codCliente = :codCliente AND n.dataEmissao BETWEEN :dataInicial AND :dataFinal")
})
public class NotaFiscal implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_COD_CLIENTE = "NotaFiscal.findByCodCliente";
	
	public static final String FIND_BY_COD_CLIENTE_PERIODO = "NotaFiscal.findByCodClienteAndMes";

	@Id
	@Column(nullable = false, length = 11)
	private Long numNotaFiscal;
	
	@Column
	private Long codCliente;
	
	@Column
	private Long codAnexo;
	
	@Column
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
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    NotaFiscal other = (NotaFiscal) obj;
	    if (getNumNotaFiscal() == null) {
	        if (other.getNumNotaFiscal() != null)
	            return false;
	    } else if (getNumNotaFiscal().equals(other.getNumNotaFiscal()))
	        return true;
	    return false;
	}

	@Override
	public int hashCode() {
	    final int prime = 43;
	    int result = 1;
	    result = prime * result + ((getNumNotaFiscal() == null) ? 0 : getNumNotaFiscal().hashCode());
	    return result;
	}

}
