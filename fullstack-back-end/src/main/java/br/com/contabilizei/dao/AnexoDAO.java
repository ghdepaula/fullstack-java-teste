package br.com.contabilizei.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.contabilizei.model.Anexo;

public class AnexoDAO extends GenericDAO<Anexo> {

	private static final long serialVersionUID = -1059838417695732920L;
	
	public AnexoDAO() {
		super(Anexo.class);
	}

	public List<Anexo> findByStatus(Boolean status) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("statusAnexo", status);
		List<Anexo> anexos = findManyResults(Anexo.FIND_BY_STATUS, params);
		return anexos;
	}

}
