/*
 * StringToServiceConverter.java
 * 
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ServicceRepository;
import domain.Servicce;

@Component
@Transactional
public class StringToServicceConverter implements Converter<String, Servicce> {

	@Autowired
	ServicceRepository	servicceRepository;


	@Override
	public Servicce convert(final String text) {
		Servicce result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.servicceRepository.findOne(id);
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
