package br.com.contabilizei.dao;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.contabilizei.model.NotaFiscal;

public class NotaFiscalDAO extends GenericDAO<NotaFiscal> {
	
	private static final long serialVersionUID = 8434530235683601623L;

	public NotaFiscalDAO() {
		super(NotaFiscal.class);
	}

	public List<NotaFiscal> findByCodCliente(Long codCliente) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("codCliente", codCliente);
		
		List<NotaFiscal> notasFiscais = findManyResults(NotaFiscal.FIND_BY_COD_CLIENTE, params);
		return notasFiscais;
	}

	public List<NotaFiscal> findByCodClienteAndMes(Long codCliente, LocalDate dataInicio, LocalDate dataFim) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("codCliente", codCliente);
		params.put("dataInicial", dataInicio);
		params.put("dataFinal", dataFim);
		
		List<NotaFiscal> notasFiscais = findManyResults(NotaFiscal.FIND_BY_COD_CLIENTE_PERIODO, params);
		return notasFiscais;
	}
}

