
package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import security.LoginService;
import domain.Comment;
import domain.Rendezvous;
import domain.User;

;

@Service
@Transactional
public class CommentService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private CommentRepository	commentRepository;

	@Autowired
	private LoginService		loginService;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public CommentService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Comment create(final Rendezvous rend) {

		final Comment r = new Comment();
		r.setUser((User) this.loginService.getPrincipalActor());
		r.setRendezvous(rend);
		r.setMoment(new Date(System.currentTimeMillis() - 1000));
		return r;
	}

	public Collection<Comment> findAll() {
		final Collection<Comment> res = this.commentRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Comment findOne(final int commentId) {
		return this.commentRepository.findOne(commentId);
	}

	public Comment save(final Comment comment) {
		Assert.notNull(comment);
		Assert.isTrue(this.checkPrincipal(comment));
		comment.setMoment(new Date(System.currentTimeMillis() - 1000));
		Assert.isTrue(comment.getRendezvous().getUsers().contains(this.loginService.getPrincipalActor()));
		return this.commentRepository.save(comment);
	}

	public void delete(final Comment comment) {
		this.commentRepository.delete(comment);
	}

	public void flush() {
		this.commentRepository.flush();
	}

	// Other business methods -------------------------------------------------

	public Boolean checkPrincipal(final Comment cmmnt) {
		Boolean res = false;
		Assert.isTrue(cmmnt.getUser().getUserAccount().equals(LoginService.getPrincipal()));
		res = true;
		return res;
	}

	public Double[] queryA3() {
		return this.commentRepository.queryA3();
	}

	public List<Comment> commentsByRendezvous(final int rendezvousId) {
		return this.commentRepository.commentsByRendezvous(rendezvousId);
	}
}
