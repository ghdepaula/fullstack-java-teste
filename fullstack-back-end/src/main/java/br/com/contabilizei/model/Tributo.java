package br.com.contabilizei.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tributo")
public class Tributo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false, length = 11)
	private Long codTributo;
	
	@Column
	private String descricaoTributo;
	
	@Column
	private Double aliquotaTributo; 
	
	public Long getCodTributo() {
		return codTributo;
	}

	public void setCodTributo(Long codTributo) {
		this.codTributo = codTributo;
	}

	public String getDescricaoTributo() {
		return descricaoTributo;
	}

	public void setDescricaoTributo(String descricaoTributo) {
		this.descricaoTributo = descricaoTributo;
	}

	public Double getAliquotaTributo() {
		return aliquotaTributo;
	}

	public void setAliquotaTributo(Double aliquotaTributo) {
		this.aliquotaTributo = aliquotaTributo;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    Tributo other = (Tributo) obj;
	    if (getCodTributo() == null) {
	        if (other.getCodTributo() != null)
	            return false;
	    } else if (getCodTributo().equals(other.getCodTributo()))
	        return true;
	    return false;
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((getCodTributo() == null) ? 0 : getCodTributo().hashCode());
	    return result;
	}

}
