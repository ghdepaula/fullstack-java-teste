package br.com.contabilizei.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Classe que representa a tabela impostos. 
 * 
 * @author Guilherme Henrique de Paula 
 * 
 */
@Entity
@Table(name="impostos")
@NamedQueries({
	 @NamedQuery(name="Imposto.findByCodCliente", query="SELECT i FROM Imposto i WHERE i.codCliente = :codCliente"),
	 @NamedQuery(name="Imposto.findByCodClienteMes", query="SELECT i FROM Imposto i WHERE i.codCliente = :codCliente AND i.yearMonth = :yearMonth")
})
public class Imposto implements Serializable {
	
	private static final long serialVersionUID = 201057454934560392L;

	public static final String FIND_BY_COD_CLIENTE = "Imposto.findByCodCliente";
	
	public static final String FIND_BY_COD_CLIENTE_MES = "Imposto.findByCodClienteMes";

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(nullable = false, length = 11)
	private Long idImposto;
	
	@Column
	private YearMonth yearMonth;
	
	@Column
	private LocalDate dataVencimento;
	
	@Column
	private BigDecimal valorImposto;
	
	@Column
	private Long codCliente;
	
	@Column
	private Long codTributo;
	
	@Column
	private Boolean statusPagamento;
	
	@ManyToOne
	@JoinColumn(name="codCliente", referencedColumnName="idCliente", insertable=false, updatable=false)
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="codTributo" , referencedColumnName="codTributo", insertable=false, updatable=false)
	private Tributo tipoImposto;

	public Long getIdImposto() {
		return idImposto;
	}

	public void setIdImposto(Long idImposto) {
		this.idImposto = idImposto;
	}

	public YearMonth getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(YearMonth yearMonth) {
		this.yearMonth = yearMonth;
	}
	
	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Tributo getTributo() {
		return tipoImposto;
	}

	public void setTributo(Tributo tipoImposto) {
		this.tipoImposto = tipoImposto;
	}
	
	public Long getCodTributo() {
		return codTributo;
	}

	public void setCodTributo(Long codTributo) {
		this.codTributo = codTributo;
	}
	
	public Boolean getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(Boolean statusPagamento) {
		this.statusPagamento = statusPagamento;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    Imposto other = (Imposto) obj;
	    if (getIdImposto() == null) {
	        if (other.getIdImposto() != null)
	            return false;
	    } else if (getIdImposto().equals(other.getIdImposto()))
	        return true;
	    return false;
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((getIdImposto() == null) ? 0 : getIdImposto().hashCode());
	    return result;
	}

}
