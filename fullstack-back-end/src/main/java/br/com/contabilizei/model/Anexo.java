package br.com.contabilizei.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe que representa a tabela anexos. 
 * 
 * @author Guilherme Henrique de Paula 
 * 
 */
@Entity
@Table(name="anexos")
public class Anexo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(nullable = false, length = 11)
	private Long codAnexo;
	
	@Column
	private String descricaoAnexo;
	
	@Column
	private Double aliquotaAnexo;
	
	@Column
	private Boolean statusAnexo;
	
	public Long getCodAnexo() {
		return codAnexo;
	}

	public void setCodAnexo(Long codAnexo) {
		this.codAnexo = codAnexo;
	}

	public String getDescricaoAnexo() {
		return descricaoAnexo;
	}

	public void setDescricaoAnexo(String descricaoAnexo) {
		this.descricaoAnexo = descricaoAnexo;
	}
	
	public Double getAliquotaAnexo() {
		return aliquotaAnexo;
	}

	public void setAliquotaAnexo(Double aliquotaAnexo) {
		this.aliquotaAnexo = aliquotaAnexo;
	}

	public Boolean getStatusAnexo() {
		return statusAnexo;
	}

	public void setStatusAnexo(Boolean statusAnexo) {
		this.statusAnexo = statusAnexo;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    Anexo other = (Anexo) obj;
	    if (getCodAnexo() == null) {
	        if (other.getCodAnexo() != null)
	            return false;
	    } else if (getCodAnexo().equals(other.getCodAnexo()))
	        return true;
	    return false;
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((getCodAnexo() == null) ? 0 : getCodAnexo().hashCode());
	    return result;
	}

}
