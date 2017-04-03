package br.com.contabilizei.dao;

import br.com.contabilizei.model.Tributo;

/**
 * Classe responsável por executar as operações de acesso e modificação de dados da entidade {@link Tributo} na base de dados. 
 * 
 * @author Guilherme Henrique de Paula 
 * 
 */
public class TributoDAO extends GenericDAO<Tributo> {

	private static final long serialVersionUID = -3254491252707368203L;
	
	public TributoDAO() {
		super(Tributo.class);
	}

}
