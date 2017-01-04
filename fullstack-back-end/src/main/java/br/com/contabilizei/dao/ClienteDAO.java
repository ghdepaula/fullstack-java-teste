package br.com.contabilizei.dao;

import br.com.contabilizei.model.Cliente;

public class ClienteDAO extends GenericDAO<Cliente> {
	
	private static final long serialVersionUID = 8434530235683601623L;

	public ClienteDAO() {
		super(Cliente.class);
	}
}
