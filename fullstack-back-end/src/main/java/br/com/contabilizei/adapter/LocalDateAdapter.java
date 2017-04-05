package br.com.contabilizei.adapter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Classe responsável pela serialização/deserialização do tipo Java {@link LocalDate} para XML/JSON e vice-versa.
 * 
 * @author Guilherme Henrique de Paula
 *
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
	
	private DateTimeFormatter formatter;
	
	private static final String LOCALDATE_FORMAT = "dd/MM/yyyy";
	
	public LocalDateAdapter() {
		formatter = DateTimeFormatter.ofPattern(LOCALDATE_FORMAT);
	}
	
	/**
	 * Implementação do método marshal da classe abstrata {@link XmlAdapter} que serializa instâncias do tipo {@link BigDecimal}
	 * 
	 */
    @Override
    public String marshal(LocalDate localDate) throws Exception {
        return formatter.format(localDate);
    }

	/**
	 * Implementação do método unmarshal da classe abstrata {@link XmlAdapter} que deserializa uma instância de {@link String}.
	 * 
	 */
    @Override
    public LocalDate unmarshal(String dateString) throws Exception {
        return LocalDate.parse(dateString, formatter);
    }

}
