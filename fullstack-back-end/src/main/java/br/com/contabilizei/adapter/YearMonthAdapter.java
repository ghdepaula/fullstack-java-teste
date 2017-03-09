package br.com.contabilizei.adapter;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class YearMonthAdapter extends XmlAdapter<String, YearMonth>{
	
	private DateTimeFormatter formatter;
	
	private static final String LOCALDATE_FORMAT = "MMMM/yyyy";
	
	public YearMonthAdapter() {
		formatter = DateTimeFormatter.ofPattern(LOCALDATE_FORMAT, new Locale("pt", "BR"));
	}

    @Override
    public YearMonth unmarshal(String yearMonthString) throws Exception {
    	YearMonth yearMonth = formatter.parse(yearMonthString, YearMonth::from);
        return yearMonth;
    }

    @Override
    public String marshal(YearMonth yearMonth) throws Exception {
        String s = yearMonth.format(this.formatter);
        return s;
    }
}
