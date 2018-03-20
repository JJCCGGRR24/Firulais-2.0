
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.UserAccount;
import domain.Actor;

;

@Service
@Transactional
public class ActorService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ActorRepository	actorRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public ActorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Collection<Actor> findAll() {
		final Collection<Actor> res = this.actorRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Actor findOne(final int actorId) {
		return this.actorRepository.findOne(actorId);
	}

	public Actor save(final Actor actor) {
		Assert.notNull(actor);
		return this.actorRepository.save(actor);
	}

	public void delete(final Actor actor) {
		this.actorRepository.delete(actor);
	}

	public void flush() {
		this.actorRepository.flush();
	}

	public Actor findByUserAccount(final UserAccount u) {
		return this.actorRepository.findByUsername(u.getUsername());
	}

	// Other business methods -------------------------------------------------

	public boolean existe(final String username) {
		boolean res = false;
		final Actor s = this.actorRepository.findByUsername(username);
		if (s != null)
			res = true;
		return res;
	}

}
