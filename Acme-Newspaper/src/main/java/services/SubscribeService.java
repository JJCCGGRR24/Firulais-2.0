
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SubscribeRepository;
import security.LoginService;
import domain.Customer;
import domain.Subscribe;

;

@Service
@Transactional
public class SubscribeService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private SubscribeRepository	subscribeRepository;

	@Autowired
	private NewspaperService	newspaperService;

	@Autowired
	private LoginService		loginService;




	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public SubscribeService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Subscribe create(final int newspaperId) {

		final Subscribe r = new Subscribe();
		r.setNewspaper(this.newspaperService.findOne(newspaperId));
		r.setCustomer((Customer) this.loginService.getPrincipalActor());
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
		subscribe.setCustomer((Customer) this.loginService.getPrincipalActor());
		subscribe.getCustomer().getSubscribes().add(subscribe);
		subscribe.getNewspaper().getSubscribes().add(subscribe);
		return this.subscribeRepository.save(subscribe);
	}

	public void delete(final Subscribe subscribe) {
		this.subscribeRepository.delete(subscribe);
	}

	public void flush() {
		this.subscribeRepository.flush();
	}

	// Other business methods -------------------------------------------------

	@SuppressWarnings("deprecation")
	public String validate(final Subscribe c) {
		String b = null;
		final Date now = new Date();
		final Date cc = new Date(c.getCreditCard().getExpirationYear() - 1900, c.getCreditCard().getExpirationMonth(), 0);
		final long days = (cc.getTime() - now.getTime()) / (1000 * 60 * 60 * 24);
		if (days < 30)
			b = "subscribe.error.cc.dates";
		return b;
	}
}
