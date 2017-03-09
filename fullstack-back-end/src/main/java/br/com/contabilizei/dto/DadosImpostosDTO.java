package br.com.contabilizei.dto;

import java.time.YearMonth;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import br.com.contabilizei.adapter.YearMonthAdapter;

@XmlRootElement
public class DadosImpostosDTO {
	
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

	public void setYearMonth(YearMonth yearMonth) {
		this.yearMonth = yearMonth;
	}

}
