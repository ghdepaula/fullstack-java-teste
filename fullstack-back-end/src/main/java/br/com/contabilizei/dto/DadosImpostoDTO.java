package br.com.contabilizei.dto;

import java.time.YearMonth;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.contabilizei.adapter.YearMonthAdapter;

/**
 * Classe responsável pela transferẽncia de dados entre as camadas da aplicação referente a operação de cálculo de impostos. 
 * 
 * @author Guilherme Henrique de Paula 
 * 
 */
@XmlRootElement
public class DadosImpostoDTO {

	private Long codCliente;
	
	@XmlJavaTypeAdapter(value = YearMonthAdapter.class)
	private YearMonth yearMonth;

	public Long getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Long codCliente) {
		this.codCliente = codCliente;
	}

	public YearMonth getYearMonth() {
		return yearMonth;
	}
}