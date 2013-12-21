/**
 * 
 */
package br.com.vraptor.local;

import java.util.ResourceBundle;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;

/**
 * 
 * @author Edgard Leal
 * @since 12/07/2013
 */
@Convert(Float.class)
public class FloatConverter implements Converter<Float> {

	@Override
	public Float convert(String value, Class<? extends Float> arg1,
			ResourceBundle arg2) {
		if (value==null || value.isEmpty())
			return 0F;
		if (value.matches("\\d+,\\d\\d"))
			value = value.replaceAll(",", ".").trim();
		if (value.matches("\\d+(\\.\\d+)?"))
			return Float.valueOf(value);
		
		return null;
	}

}
