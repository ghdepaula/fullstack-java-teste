package br.com.contabilizei.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TipoEmpresaDTO {
	
	private Long codTipoEmpresa;
	
	private String descricaoTipoEmpresa;
	
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
