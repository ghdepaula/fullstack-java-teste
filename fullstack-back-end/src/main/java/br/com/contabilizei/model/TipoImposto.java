package br.com.contabilizei.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo_imposto")
public class TipoImposto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, length = 11)
	private Long codTipoImposto;
	
	@Column
	private String descricaoTipoImposto;
	
	@Column
	private Double aliquotaTipoImposto; 
	
	@Column
	private Boolean statusTipoImposto;

	public Long getCodTipoImposto() {
		return codTipoImposto;
	}

	public void setCodTipoImposto(Long codTipoImposto) {
		this.codTipoImposto = codTipoImposto;
	}

	public String getDescricaoTipoImposto() {
		return descricaoTipoImposto;
	}

	public void setDescricaoTipoImposto(String descricaoTipoImposto) {
		this.descricaoTipoImposto = descricaoTipoImposto;
	}

	public Double getAliquotaTipoImposto() {
		return aliquotaTipoImposto;
	}

	public void setAliquotaTipoImposto(Double aliquotaTipoImposto) {
		this.aliquotaTipoImposto = aliquotaTipoImposto;
	}

	public Boolean getStatusTipoImposto() {
		return statusTipoImposto;
	}

	public void setStatusTipoImposto(Boolean statusTipoImposto) {
		this.statusTipoImposto = statusTipoImposto;
	}

}
