package br.com.contabilizei.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="regime_tributario")
@NamedQueries({
	 @NamedQuery(name="RegimeTributario.findByStatus", query="SELECT r FROM RegimeTributario r WHERE r.statusRegimeTributario = :statusRegTributario")
})
public class RegimeTributario implements Serializable {
	
	private static final long serialVersionUID = 4840779789660211865L;

	public static final String FIND_BY_STATUS = "RegimeTributario.findByStatus";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, length = 11)
	private Long codRegimeTributario;
	
	@Column
	private String descricaoRegimeTributario;
	
	@Column
	private Boolean statusRegimeTributario;

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

	public Boolean getStatusRegimeTributario() {
		return statusRegimeTributario;
	}

	public void setStatusRegimeTributario(Boolean statusRegimeTributario) {
		this.statusRegimeTributario = statusRegimeTributario;
	}

}
