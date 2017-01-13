package br.com.contabilizei.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="anexos")
@NamedQueries({
	 @NamedQuery(name="Anexo.findByStatus", query="SELECT a FROM Anexo a WHERE a.statusAnexo = :statusAnexo")
})
public class Anexo {
	
	public static final String FIND_BY_STATUS = "Anexo.findByStatus";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, length = 11)
	private Long codAnexo;
	
	@Column
	private String descricaoAnexo;
	
	@Column
	private Double aliquotaAnexo;
	
	@Column
	private Boolean statusAnexo;
	
	@ManyToMany(mappedBy="anexos")
	private List<Cliente> clientes;
	
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

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

}
