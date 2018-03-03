/*
 * StringToRendezvousConverter.java
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

import repositories.RendezvousRepository;
import domain.Rendezvous;

@Component
@Transactional
public class StringToRendezvousConverter implements Converter<String, Rendezvous> {

	@Autowired
	RendezvousRepository	rendezvousRepository;


	@Override
	public Rendezvous convert(final String text) {
		Rendezvous result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.rendezvousRepository.findOne(id);
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
