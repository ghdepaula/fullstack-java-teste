package br.com.contabilizei.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.contabilizei.model.RegimeTributario;

public class RegimeTributarioDAO extends GenericDAO<RegimeTributario> {

	private static final long serialVersionUID = -3360713208338313833L;

	public RegimeTributarioDAO() {
		super(RegimeTributario.class);
	}
	
	public List<RegimeTributario> findByStatus(Boolean status) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("statusRegTributario", status);
		List<RegimeTributario> regsTributarios = findManyResults(RegimeTributario.FIND_BY_STATUS, params);
		return regsTributarios;
	}
}
