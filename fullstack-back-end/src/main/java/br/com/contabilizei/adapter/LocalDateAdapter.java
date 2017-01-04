package br.com.contabilizei.adapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
	
	private DateTimeFormatter formatter;
	
	private static final String LOCALDATE_FORMAT = "dd/MM/yyyy";
	
	public LocalDateAdapter() {
		formatter = DateTimeFormatter.ofPattern(LOCALDATE_FORMAT);
	}

    @Override
    public LocalDate unmarshal(String dateString) throws Exception {
        return LocalDate.parse(dateString, formatter);
    }

    @Override
    public String marshal(LocalDate localDate) throws Exception {
        return formatter.format(localDate);
    }
}
