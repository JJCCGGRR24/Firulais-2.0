
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SubscribeVolRepository;
import domain.SubscribeVol;

;

@Service
@Transactional
public class SubscribeVolService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private SubscribeVolRepository	subscribeVolRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public SubscribeVolService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public SubscribeVol create() {
		final SubscribeVol r = new SubscribeVol();
		return r;
	}

	public Collection<SubscribeVol> findAll() {
		final Collection<SubscribeVol> res = this.subscribeVolRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public SubscribeVol findOne(final int subscribeVolId) {
		return this.subscribeVolRepository.findOne(subscribeVolId);
	}

	public SubscribeVol save(final SubscribeVol subscribeVol) {
		Assert.notNull(subscribeVol);
		return this.subscribeVolRepository.save(subscribeVol);
	}

	public void delete(final SubscribeVol subscribeVol) {
		this.subscribeVolRepository.delete(subscribeVol);
	}

	public void flush() {
		this.subscribeVolRepository.flush();
	}

	// Other business methods -------------------------------------------------

}
