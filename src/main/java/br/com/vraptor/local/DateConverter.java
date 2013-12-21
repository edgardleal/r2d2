/**
 * 
 */
package br.com.vraptor.local;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.ioc.ApplicationScoped;

/**
 * 
 * @author Edgard Leal
 * @since 08/07/2013
 */
@Convert(Date.class)
@ApplicationScoped
public class DateConverter implements Converter<Date> {

	private DateFormat dateTimeFormater = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	private DateFormat dateFormater = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public Date convert(String data, Class<? extends Date> arg1,
			ResourceBundle arg2) {
		
		try {
			if (data.matches("\\d{2}/\\d{2}/\\d{4}"))
				return dateFormater .parse(data);
			else if (data
					.matches("\\d\\d/\\d\\d/\\d\\d/\\d{4} \\d\\d:\\d\\d:\\d\\d"))
				return dateTimeFormater .parse(data);
			else if (data.matches("\\d{2}/\\d{2}/\\d{2}"))
				return new SimpleDateFormat("d/M/yy").parse(data);
			else if (data
					.matches("\\w{3}, \\d{2} \\w{3} \\d{4} \\d{2}:\\d{2}:\\d{2} .*"))
				return new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z")
						.parse(data);
			else if (data.matches("\\d+"))
				return new Date(Long.valueOf(data));

		} catch (ParseException e) {
			System.out.println("Formato de data inválido " + data);
			throw new IllegalArgumentException(String.format(
					"Formato inválido para data [%s]", data));
		}
		return null;
	}

}
