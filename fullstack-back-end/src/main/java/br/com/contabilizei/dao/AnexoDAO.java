package br.com.contabilizei.dao;

import br.com.contabilizei.model.Anexo;

/**
 * Classe responsável por executar as operações de acesso e modificação de dados da entidade {@link Anexo} na base de dados. 
 * 
 * @author Guilherme Henrique de Paula 
 * 
 */
public class AnexoDAO extends GenericDAO<Anexo> {

	private static final long serialVersionUID = -1059838417695732920L;
	
	public AnexoDAO() {
		super(Anexo.class);
	}
}
