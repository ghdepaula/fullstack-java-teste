package br.com.contabilizei.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe responsável pela transferẽncia de dados entre as camadas da aplicação referentes as operações do serviço de anexos. 
 * 
 * @author Guilherme Henrique de Paula 
 * 
 */
@XmlRootElement
public class AnexoDTO {
	
	private Long codAnexo;
	
	private String descricaoAnexo;
	
	private Double aliquotaAnexo;
	
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

}
