package br.com.contabilizei.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe responsável pela transferẽncia de dados entre as camadas da aplicação referente as operações do serviço de tributos. 
 * 
 * @author Guilherme Henrique de Paula 
 * 
 */
@XmlRootElement
public class TributoDTO {
	
	private Long codTributo;
	
	private String descricaoTributo;
	
	private Double aliquotaTributo; 
	
	public Long getCodTributo() {
		return codTributo;
	}

	public void setCodTributo(Long codTributo) {
		this.codTributo = codTributo;
	}

	public String getDescricaoTributo() {
		return descricaoTributo;
	}

	public void setDescricaoTributo(String descricaoTributo) {
		this.descricaoTributo = descricaoTributo;
	}

	public Double getAliquotaTributo() {
		return aliquotaTributo;
	}

	public void setAliquotaTributo(Double aliquotaTributo) {
		this.aliquotaTributo = aliquotaTributo;
	}

}




