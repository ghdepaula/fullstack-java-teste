package br.com.contabilizei.dao;

import br.com.contabilizei.model.RegimeTributario;

/**
 * Classe responsável por executar as operações de acesso e modificação de dados da entidade {@link RegimeTributario} na base de dados. 
 * 
 * @author Guilherme Henrique de Paula 
 * 
 */
public class RegimeTributarioDAO extends GenericDAO<RegimeTributario> {

	private static final long serialVersionUID = -3360713208338313833L;

	public RegimeTributarioDAO() {
		super(RegimeTributario.class);
	}
	
}
