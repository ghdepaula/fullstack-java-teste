package br.com.contabilizei.adapter;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Classe responsável pela serialização/deserialização do tipo Java {@link YearMonth} para XML/JSON e vice-versa.
 * 
 * @author Guilherme Henrique de Paula
 *
 */
public class YearMonthAdapter extends XmlAdapter<String, YearMonth>{
	
	private DateTimeFormatter formatter;
	
	private static final String LOCALDATE_FORMAT = "MMMM/yyyy";
	
	public YearMonthAdapter() {
		formatter = DateTimeFormatter.ofPattern(LOCALDATE_FORMAT, new Locale("pt", "BR"));
	}
	

	/**
	 * Implementação do método marshal da classe abstrata {@link XmlAdapter} que serializa instâncias do tipo {@link YearMonth}
	 * 
	 */
    @Override
    public String marshal(YearMonth yearMonth) throws Exception {
        String s = yearMonth.format(this.formatter);
        return s;
    }

	/**
	 * Implementação do método unmarshal da classe abstrata {@link XmlAdapter} que deserializa uma instância de {@link String}.
	 * 
	 */
    @Override
    public YearMonth unmarshal(String yearMonthString) throws Exception {
    	YearMonth yearMonth = formatter.parse(yearMonthString, YearMonth::from);
        return yearMonth;
    }

}
