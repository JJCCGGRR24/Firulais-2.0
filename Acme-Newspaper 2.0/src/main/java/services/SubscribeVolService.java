
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SubscribeVolRepository;
import security.LoginService;
import domain.Customer;
import domain.Newspaper;
import domain.SubscribeVol;
import domain.Volume;

;

@Service
@Transactional
public class SubscribeVolService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private SubscribeVolRepository	subscribeVolRepository;

	@Autowired
	private SubscribeService		subscribeService;

	@Autowired
	private VolumeService			volumeService;

	@Autowired
	private LoginService			loginService;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public SubscribeVolService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public SubscribeVol create(final int volumeId) {

		final SubscribeVol r = new SubscribeVol();
		r.setVolume(this.volumeService.findOne(volumeId));
		r.setCustomer((Customer) this.loginService.getPrincipalActor());
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
		subscribeVol.setCustomer((Customer) this.loginService.getPrincipalActor());
		subscribeVol.getCustomer().getSubscribesVol().add(subscribeVol);
		subscribeVol.getVolume().getSubscribesVol().add(subscribeVol);
		final SubscribeVol res = this.subscribeVolRepository.save(subscribeVol);
		for (final Newspaper n : res.getVolume().getNewspapers())
			this.subscribeService.subscribe(n, subscribeVol.getCreditCard());
		return res;
	}

	public void delete(final SubscribeVol subscribeVol) {
		this.subscribeVolRepository.delete(subscribeVol);
	}

	public void flush() {
		this.subscribeVolRepository.flush();
	}

	// Other business methods -------------------------------------------------

	@SuppressWarnings("deprecation")
	public String validate(final SubscribeVol c) {
		String b = null;
		final Date now = new Date();
		final Date cc = new Date(c.getCreditCard().getExpirationYear() - 1900, c.getCreditCard().getExpirationMonth(), 0);
		final long days = (cc.getTime() - now.getTime()) / (1000 * 60 * 60 * 24);
		if (days < 30)
			b = "subscribe.error.cc.dates";
		return b;
	}

	public boolean estaSubscrito(final Customer c, final Volume n) {
		boolean res = false;
		if (this.subscribeVolRepository.getSubscripcion(c, n) != null)
			res = true;
		return res;
	}
}
