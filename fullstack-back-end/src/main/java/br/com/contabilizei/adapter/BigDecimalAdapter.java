package br.com.contabilizei.adapter;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class BigDecimalAdapter extends XmlAdapter<String, BigDecimal> {

	@Override
	public String marshal(BigDecimal value) throws Exception {
		if (value != null) {
			return value.toString();
		}
		return null;
	}

	@Override
	public BigDecimal unmarshal(String s) throws Exception {

		NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));
		BigDecimal value = new BigDecimal(nf.parse(s).toString());

		return value;
	}
}
