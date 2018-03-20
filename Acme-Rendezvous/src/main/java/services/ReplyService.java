
package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ReplyRepository;
import security.LoginService;
import domain.Comment;
import domain.Reply;
import domain.User;

;

@Service
@Transactional
public class ReplyService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ReplyRepository	replyRepository;

	@Autowired
	private LoginService	loginService;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public ReplyService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Reply create(final Comment cmmnt) {

		final Reply r = new Reply();
		r.setUser((User) this.loginService.getPrincipalActor());
		r.setComment(cmmnt);
		return r;
	}

	public Collection<Reply> findAll() {
		final Collection<Reply> res = this.replyRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Reply findOne(final int replyId) {
		return this.replyRepository.findOne(replyId);
	}

	public Reply save(final Reply reply) {
		Assert.notNull(reply);
		Assert.isTrue(this.checkPrincipal(reply));
		return this.replyRepository.save(reply);
	}

	public void delete(final Reply reply) {
		this.replyRepository.delete(reply);
	}

	public void flush() {
		this.replyRepository.flush();
	}

	// Other business methods -------------------------------------------------

	public Boolean checkPrincipal(final Reply r) {
		Boolean res = false;
		Assert.isTrue(r.getUser().getUserAccount().equals(LoginService.getPrincipal()));
		res = true;
		return res;
	}

	public List<Reply> repliesByComment(final int commentId) {
		return this.replyRepository.repliesByComment(commentId);
	}

}
