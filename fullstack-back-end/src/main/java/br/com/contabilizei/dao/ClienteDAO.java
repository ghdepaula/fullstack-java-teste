
package br.com.contabilizei.dao;

import br.com.contabilizei.model.Cliente;

/**
 * Classe responsável por executar as operações de acesso e modificação de dados da entidade {@link Cliente} na base de dados. 
 * 
 * @author Guilherme Henrique de Paula 
 * 
 */
public class ClienteDAO extends GenericDAO<Cliente> {
	
	private static final long serialVersionUID = 8434530235683601623L;

	public ClienteDAO() {
		super(Cliente.class);
	}
}
