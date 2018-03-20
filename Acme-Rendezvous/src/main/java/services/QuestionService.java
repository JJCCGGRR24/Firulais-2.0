
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.QuestionRepository;
import security.LoginService;
import domain.Question;
import domain.Rendezvous;

;

@Service
@Transactional
public class QuestionService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private QuestionRepository	questionRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public QuestionService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Question create(final Rendezvous rendezvous) {
		final Question r = new Question();
		Assert.isTrue(LoginService.isPrincipalUser());
		r.setRendezvous(rendezvous);
		return r;
	}

	public Collection<Question> findAll() {
		final Collection<Question> res = this.questionRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Question findOne(final int questionId) {
		return this.questionRepository.findOne(questionId);
	}

	public Question save(final Question question) {
		Assert.notNull(question);
		return this.questionRepository.save(question);
	}

	public void delete(final Question question) {
		this.questionRepository.delete(question);
	}

	public void flush() {
		this.questionRepository.flush();
	}

	// Other business methods -------------------------------------------------

}
