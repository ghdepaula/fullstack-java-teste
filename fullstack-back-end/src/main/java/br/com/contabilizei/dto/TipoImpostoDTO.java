package br.com.contabilizei.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TipoImpostoDTO {
	
	private Long codTipoImposto;
	
	private String descricaoTipoImposto;
	
	private Double aliquotaTipoImposto; 
	
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




