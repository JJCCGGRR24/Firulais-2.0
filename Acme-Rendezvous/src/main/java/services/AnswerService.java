
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AnswerRepository;
import security.LoginService;
import domain.Answer;
import domain.Question;
import domain.Rendezvous;
import domain.User;
import forms.AnswersForm;

;

@Service
@Transactional
public class AnswerService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private AnswerRepository	answerRepository;

	@Autowired
	private LoginService		loginService;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public AnswerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Answer create(final Question question) {
		final Answer r = new Answer();
		r.setQuestion(question);
		r.setUser((User) this.loginService.getPrincipalActor());
		return r;
	}
	public Collection<Answer> findAll() {
		final Collection<Answer> res = this.answerRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Answer findOne(final int answerId) {
		return this.answerRepository.findOne(answerId);
	}

	public Answer save(final Answer answer) {
		Assert.notNull(answer);
		return this.answerRepository.save(answer);
	}

	public void delete(final Answer answer) {
		this.answerRepository.delete(answer);
	}

	public void flush() {
		this.answerRepository.flush();
	}

	// Other business methods -------------------------------------------------

	public AnswersForm create(final Rendezvous r) {
		final AnswersForm af = new AnswersForm();
		final List<Answer> las = new ArrayList<Answer>();
		for (final Question q : r.getQuestions())
			las.add(this.create(q));
		af.setLas(las);
		af.setRid(r.getId());
		return af;
	}
}
