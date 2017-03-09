package br.com.contabilizei.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RegimeTributarioDTO {
	
	private Long codRegimeTributario;
	
	private String descricaoRegimeTributario;
	
	private Boolean enabledAnexos;
	
	private List<TributoDTO> tributos;

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

	public List<TributoDTO> getTributos() {
		return tributos;
	}

	public void setTributos(List<TributoDTO> tributos) {
		this.tributos = tributos;
	}

}
