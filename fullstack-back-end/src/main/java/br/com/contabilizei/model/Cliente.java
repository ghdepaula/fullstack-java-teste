package br.com.contabilizei.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, length = 11)
	private Long idCliente;
	
	@Column(length = 18)
	private String cnpjCliente;
	
	@Column(nullable = false, length = 80)
	private String nomeRazaoSocial;
	
	@Column(nullable = false, length = 80)
	private String email;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
    @JoinTable(name="cliente_anexos", joinColumns={@JoinColumn(name="idCliente")}, inverseJoinColumns={@JoinColumn(name="codAnexo")})
	private List<Anexo> anexos;
	
	public Long getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Anexo> getAnexos() {
		return anexos;
	}

	public void setAnexos(List<Anexo> anexos) {
		this.anexos = anexos;
	}
}
