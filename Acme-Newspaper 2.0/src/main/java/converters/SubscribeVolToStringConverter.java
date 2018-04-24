/*
 * SubscribeVolToStringConverter.java
 * 
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.SubscribeVol;

@Component
@Transactional
public class SubscribeVolToStringConverter implements Converter<SubscribeVol, String> {

	@Override
	public String convert(final SubscribeVol subscribeVol) {
		String result;

		if (subscribeVol == null)
			result = null;
		else
			result = String.valueOf(subscribeVol.getId());

		return result;
	}

}
