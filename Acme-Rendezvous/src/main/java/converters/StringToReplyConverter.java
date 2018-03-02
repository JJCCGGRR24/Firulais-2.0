/*
 * StringToReplyConverter.java
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

import repositories.ReplyRepository;
import domain.Reply;

@Component
@Transactional
public class StringToReplyConverter implements Converter<String, Reply> {

	@Autowired
	ReplyRepository	replyRepository;


	@Override
	public Reply convert(final String text) {
		Reply result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.replyRepository.findOne(id);
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
