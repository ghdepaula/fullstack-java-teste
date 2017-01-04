package br.com.contabilizei.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo_empresa")
public class TipoEmpresa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, length = 11)
	private Long codTipoEmpresa;
	
	@Column
	private String descricaoTipoEmpresa;
	
	@Column
	private Boolean statusTipoEmpresa;

	public Long getCodTipoEmpresa() {
		return codTipoEmpresa;
	}

	public void setCodTipoEmpresa(Long codTipoEmpresa) {
		this.codTipoEmpresa = codTipoEmpresa;
	}

	public String getDescricaoTipoEmpresa() {
		return descricaoTipoEmpresa;
	}

	public void setDescricaoTipoEmpresa(String descricaoTipoEmpresa) {
		this.descricaoTipoEmpresa = descricaoTipoEmpresa;
	}

	public Boolean getStatusTipoEmpresa() {
		return statusTipoEmpresa;
	}

	public void setStatusTipoEmpresa(Boolean statusTipoEmpresa) {
		this.statusTipoEmpresa = statusTipoEmpresa;
	}
	

}
