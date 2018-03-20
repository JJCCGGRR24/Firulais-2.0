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

import java.net.URLEncoder;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Coordinates;

@Component
@Transactional
public class CoordinatesToStringConverter implements Converter<Coordinates, String> {

	@Override
	public String convert(final Coordinates coordinates) {
		String result;
		StringBuilder builder;

		if (coordinates == null)
			result = null;
		else
			try {
				builder = new StringBuilder();
				builder.append(URLEncoder.encode(coordinates.getLatitude().toString(), "UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(coordinates.getLongitude().toString(), "UTF-8"));
				result = builder.toString();
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			}
		return result;
	}
}
