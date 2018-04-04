
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SubscribeRepository;
import domain.Subscribe;

;

@Service
@Transactional
public class SubscribeService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private SubscribeRepository	subscribeRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public SubscribeService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Subscribe create() {

		final Subscribe r = new Subscribe();
		return r;
	}

	public Collection<Subscribe> findAll() {
		final Collection<Subscribe> res = this.subscribeRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Subscribe findOne(final int subscribeId) {
		return this.subscribeRepository.findOne(subscribeId);
	}

	public Subscribe save(final Subscribe subscribe) {
		Assert.notNull(subscribe);
		return this.subscribeRepository.save(subscribe);
	}

	public void delete(final Subscribe subscribe) {
		this.subscribeRepository.delete(subscribe);
	}

	public void flush() {
		this.subscribeRepository.flush();
	}

	// Other business methods -------------------------------------------------

}
