package br.com.contabilizei.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ClienteDTO {
	private Long idCliente;
	private String cnpjCliente;
	private Integer tipoEmpresa;
	private String nomeRazaoSocial;
	private String email;
	private List<AnexoDTO> anexos;

	public Long getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCnpjCliente() {
		return this.cnpjCliente;
	}

	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}

	public String getNomeRazaoSocial() {
		return this.nomeRazaoSocial;
	}

	public void setNomeRazaoSocial(String nomeRazaoSocial) {
		this.nomeRazaoSocial = nomeRazaoSocial;
	}

	public Integer getTipoEmpresa() {
		return tipoEmpresa;
	}

	public void setTipoEmpresa(Integer tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	public List<AnexoDTO> getAnexos() {
		return anexos;
	}

	public void setAnexos(List<AnexoDTO> anexos) {
		this.anexos = anexos;
	}
}
