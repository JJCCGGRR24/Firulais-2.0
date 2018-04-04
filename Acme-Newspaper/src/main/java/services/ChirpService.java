
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ChirpRepository;
import domain.Chirp;

;

@Service
@Transactional
public class ChirpService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ChirpRepository	chirpRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public ChirpService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Chirp create() {

		final Chirp r = new Chirp();
		return r;
	}

	public Collection<Chirp> findAll() {
		final Collection<Chirp> res = this.chirpRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Chirp findOne(final int chirpId) {
		return this.chirpRepository.findOne(chirpId);
	}

	public Chirp save(final Chirp chirp) {
		Assert.notNull(chirp);
		return this.chirpRepository.save(chirp);
	}

	public void delete(final Chirp chirp) {
		this.chirpRepository.delete(chirp);
	}

	public void flush() {
		this.chirpRepository.flush();
	}

	// Other business methods -------------------------------------------------

}
