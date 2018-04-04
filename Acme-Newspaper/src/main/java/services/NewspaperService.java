
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.NewspaperRepository;
import domain.Newspaper;

;

@Service
@Transactional
public class NewspaperService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private NewspaperRepository	newspaperRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public NewspaperService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Newspaper create() {

		final Newspaper r = new Newspaper();
		return r;
	}

	public Collection<Newspaper> findAll() {
		final Collection<Newspaper> res = this.newspaperRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Newspaper findOne(final int newspaperId) {
		return this.newspaperRepository.findOne(newspaperId);
	}

	public Newspaper save(final Newspaper newspaper) {
		Assert.notNull(newspaper);
		return this.newspaperRepository.save(newspaper);
	}

	public void delete(final Newspaper newspaper) {
		this.newspaperRepository.delete(newspaper);
	}

	public void flush() {
		this.newspaperRepository.flush();
	}

	// Other business methods -------------------------------------------------

}
