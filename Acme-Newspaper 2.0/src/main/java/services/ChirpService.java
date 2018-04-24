
package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ChirpRepository;
import security.LoginService;
import domain.Chirp;
import domain.Taboo;
import domain.User;

;

@Service
@Transactional
public class ChirpService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ChirpRepository	chirpRepository;

	@Autowired
	private LoginService	loginService;

	@Autowired
	private TabooService	tabooService;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public ChirpService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Chirp create() {
		final Chirp r = new Chirp();
		r.setMoment(new Date());
		r.setUser((User) this.loginService.getPrincipalActor());
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
		Assert.isTrue(chirp.getId() == 0);
		chirp.setMoment(new Date());
		chirp.setUser((User) this.loginService.getPrincipalActor());
		chirp.setTabooWord(this.isTaboo(chirp));
		return this.chirpRepository.save(chirp);
	}

	public void delete(final Chirp chirp) {
		Assert.isTrue(LoginService.isPrincipalAdmin());
		this.chirpRepository.delete(chirp);
	}
	public void flush() {
		this.chirpRepository.flush();
	}

	public List<Chirp> getChirpsFromFolloweds() {
		return this.chirpRepository.getChirpsFromFolloweds(((User) this.loginService.getPrincipalActor()).getId());
	}

	// Other business methods -------------------------------------------------

	public List<Chirp> getChirpsTabooWords() {
		return this.chirpRepository.getChirpsTabooWords();
	}

	public boolean isTaboo(final Chirp c) {
		boolean b = false;
		for (final Taboo t : this.tabooService.findAll()) {
			final String s = t.getWord().toLowerCase();
			if (c.getTitle().toLowerCase().contains(s) || c.getDescription().toLowerCase().contains(s)) {
				b = true;
				break;
			}
		}
		return b;
	}
}
