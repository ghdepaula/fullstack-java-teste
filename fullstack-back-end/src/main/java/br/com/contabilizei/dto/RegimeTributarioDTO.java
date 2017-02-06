package br.com.contabilizei.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RegimeTributarioDTO {
	
	private Long codRegimeTributario;
	
	private String descricaoRegimeTributario;
	
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
