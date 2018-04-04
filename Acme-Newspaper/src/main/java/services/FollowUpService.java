
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FollowUpRepository;
import domain.FollowUp;

;

@Service
@Transactional
public class FollowUpService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private FollowUpRepository	followUpRepository;


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
		return this.followUpRepository.save(followUp);
	}

	public void delete(final FollowUp followUp) {
		this.followUpRepository.delete(followUp);
	}

	public void flush() {
		this.followUpRepository.flush();
	}

	// Other business methods -------------------------------------------------

}
