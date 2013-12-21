package br.com.vraptor.local;

import java.util.ResourceBundle;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.ioc.ApplicationScoped;

/**
 * 
 * 
 * @author Edgard Leal
 * @since 08/07/2013
 */
@Convert(Double.class)
@ApplicationScoped
public class DoubleConverter implements Converter<Double> {

	@Override
	public Double convert(String value, Class<? extends Double> arg1,
			ResourceBundle arg2) {
		value = value.replaceAll("R\\$", "").replaceAll(" ", "");
		if (value.matches("\\d+(\\.\\d{3})*(,\\d+)"))
			value = value.replaceAll("\\.", "")
					.replace(',', '.');

		if (value.matches("\\d+(\\.\\d+)?"))
			return Double.valueOf(value);

		return null;
	}

	public String getAsString(Double prcProm) {
		return String.valueOf(prcProm);
	}

}
