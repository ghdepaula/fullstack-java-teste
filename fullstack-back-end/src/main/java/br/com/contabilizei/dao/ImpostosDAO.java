package br.com.contabilizei.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.contabilizei.model.Imposto;

public class ImpostosDAO extends GenericDAO<Imposto> {

	private static final long serialVersionUID = 5737425229063072679L;
	
	public ImpostosDAO() {
		super(Imposto.class);
	}

	public List<Imposto> findByCodCliente(Long codCliente) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("codCliente", codCliente);
		List<Imposto> impostos = findManyResults(Imposto.FIND_BY_COD_CLIENTE, params);
		return impostos;
	}

}
