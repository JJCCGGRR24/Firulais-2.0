/*
 * CoordinatesToStringConverter.java
 * 
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package converters;

import java.net.URLDecoder;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Coordinates;

@Component
@Transactional
public class StringToCoordinatesConverter implements Converter<String, Coordinates> {

	@Override
	public Coordinates convert(final String text) {
		Coordinates result;
		final String parts[];

		if (text == null)
			result = null;
		else
			try {
				parts = text.split("\\|");
				result = new Coordinates();
				result.setLatitude(Double.valueOf(URLDecoder.decode(parts[0], "UTF-8")));
				result.setLongitude(Double.valueOf(URLDecoder.decode(parts[1], "UTF-8")));
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			}
		return result;
	}
}
