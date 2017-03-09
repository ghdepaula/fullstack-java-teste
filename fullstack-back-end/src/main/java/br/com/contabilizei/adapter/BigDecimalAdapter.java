package br.com.contabilizei.adapter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class BigDecimalAdapter extends XmlAdapter<String, BigDecimal> {

	private NumberFormat numberFormat; 
	
	public BigDecimalAdapter() {
		this.numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));
	}
	
	@Override
	public String marshal(BigDecimal value) throws Exception {
		if (value != null) {
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) nf).getDecimalFormatSymbols();
			decimalFormatSymbols.setCurrencySymbol("");
			((DecimalFormat) nf).setDecimalFormatSymbols(decimalFormatSymbols);
			return nf.format(value).trim();
		}
		return null;
	}

	@Override
	public BigDecimal unmarshal(String s) throws Exception {
		BigDecimal value = new BigDecimal(numberFormat.parse(s).toString()).setScale(2, RoundingMode.HALF_UP);
		return value;
	}
}
