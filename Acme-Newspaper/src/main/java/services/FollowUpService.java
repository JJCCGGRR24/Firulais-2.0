
package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FollowUpRepository;
import security.LoginService;
import domain.FollowUp;

;

@Service
@Transactional
public class FollowUpService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private FollowUpRepository	followUpRepository;

	@Autowired
	private LoginService		loginService;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public FollowUpService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public FollowUp create() {

		final FollowUp r = new FollowUp();
		return r;
	}

	public Collection<FollowUp> findAll() {
		final Collection<FollowUp> res = this.followUpRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public FollowUp findOne(final int followUpId) {
		return this.followUpRepository.findOne(followUpId);
	}

	public FollowUp save(final FollowUp followUp) {
		Assert.notNull(followUp);
		Assert.isTrue(followUp.getArticle().getNewspaper().getUser().getId() == this.loginService.getPrincipalActor().getId());
		followUp.setPublicationMoment(new Date());
		followUp.getArticle().getFollowUps().add(followUp);
		return this.followUpRepository.save(followUp);
	}

	public void delete(final FollowUp followUp) {
		this.followUpRepository.delete(followUp);
	}

	public void flush() {
		this.followUpRepository.flush();
	}

	public List<FollowUp> getFollowUpsByUser(final int userId) {
		return this.followUpRepository.getFollowUpsByUser(userId);
	}

	// Other business methods -------------------------------------------------

	public String validate(final FollowUp c) {
		String b = null;
		if (c.getArticle().getMoment() == null)
			b = "followUp.error.articlePublicated";
		if (c.getArticle().getNewspaper().getPublicationDate() == null)
			b = "followUp.error.newspaperPublicated";
		if (c.getPublicationMoment() != null)
			b = "followUp.error.followUpPublicated";
		return b;
	}
}
