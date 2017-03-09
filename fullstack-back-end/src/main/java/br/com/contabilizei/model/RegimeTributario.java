package br.com.contabilizei.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="regime_tributario")
public class RegimeTributario implements Serializable {
	
	private static final long serialVersionUID = 4840779789660211865L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, length = 11)
	private Long codRegimeTributario;
	
	@Column
	private String descricaoRegimeTributario;
	
	@Column
	private Boolean enabledAnexos;
	
	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="regime_tributo",
    		joinColumns={@JoinColumn(name="codRegimeTributario", referencedColumnName="codRegimeTributario", insertable=false, updatable=false)},
    		inverseJoinColumns={@JoinColumn(name="codTributo", referencedColumnName="codTributo", insertable=false, updatable=false)})
	private List<Tributo> tributos;
	
	public Long getCodRegimeTributario() {
		return codRegimeTributario;
	}

	public void setCodRegimeTributario(Long codRegimeTributario) {
		this.codRegimeTributario = codRegimeTributario;
	}

	public String getDescricaoRegimeTributario() {
		return descricaoRegimeTributario;
	}

	public void setDescricaoRegimeTributario(String descricaoRegimeTributario) {
		this.descricaoRegimeTributario = descricaoRegimeTributario;
	}

	public Boolean getEnabledAnexos() {
		return enabledAnexos;
	}

	public void setEnabledAnexos(Boolean enabledAnexos) {
		this.enabledAnexos = enabledAnexos;
	}

	public List<Tributo> getTributos() {
		return tributos;
	}

	public void setTributos(List<Tributo> tributos) {
		this.tributos = tributos;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    RegimeTributario other = (RegimeTributario) obj;
	    if (getCodRegimeTributario() == null) {
	        if (other.getCodRegimeTributario() != null)
	            return false;
	    } else if (getCodRegimeTributario().equals(other.getCodRegimeTributario()))
	        return true;
	    return false;
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((getCodRegimeTributario() == null) ? 0 : getCodRegimeTributario().hashCode());
	    return result;
	}

}
