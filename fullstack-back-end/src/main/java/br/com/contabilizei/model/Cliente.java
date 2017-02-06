package br.com.contabilizei.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = -5529688842104642296L;

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
	
	@OneToMany(fetch=FetchType.EAGER)
    @JoinTable(name="cliente_anexos",
    		   joinColumns={@JoinColumn(name="idCliente", referencedColumnName="idCliente", table="clientes", insertable=false, updatable=false)}, 
    		   inverseJoinColumns={@JoinColumn(name="codAnexo", referencedColumnName="codAnexo", table="anexos", insertable=false, updatable=false)})
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
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    Cliente other = (Cliente) obj;
	    if (getIdCliente() == null) {
	        if (other.getIdCliente() != null)
	            return false;
	    } else if (getIdCliente().equals(other.getIdCliente()))
	        return true;
	    return false;
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((getIdCliente() == null) ? 0 : getIdCliente().hashCode());
	    return result;
	}
}
