
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SubscribeRepository;
import security.LoginService;
import domain.Article;
import domain.Customer;
import domain.Newspaper;
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
		Assert.isTrue(subscribe.getNewspaper().getDeprived() == true, "newspaperNotPrivated");
		Assert.isTrue(subscribe.getNewspaper().getPublicationDate() != null, "newspaperNotPublished");

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
		if (this.newspaperService.getNewspaperSubscribes((Customer) this.loginService.getPrincipalActor()).contains(c.getNewspaper()))
			b = "subscribe.error.already";
		return b;
	}

	public boolean estaSubscrito(final Customer c, final Newspaper n) {
		boolean res = false;
		if (this.subscribeRepository.getSubscripcion(c, n) != null)
			res = true;
		return res;
	}

	public boolean tienePermisoParaVerArticulos(final Article a) {
		boolean res = true;
		if (a.getNewspaper().getDeprived())
			//Cuando no esta autenticado
			if (!LoginService.isPrincipalUser() && !LoginService.isPrincipalCustomer() && !LoginService.isPrincipalAdmin())
				res = false;
			//Cuando es usuario y no es el propietario del periodico o del articulo
			else if (LoginService.isPrincipalUser() && (!a.getUser().equals(this.loginService.getPrincipalActor()) && !a.getNewspaper().getUser().equals(this.loginService.getPrincipalActor())))
				res = false;
			//Cuando es customer y no esta subscrito
			else if (LoginService.isPrincipalCustomer() && !this.estaSubscrito((Customer) this.loginService.getPrincipalActor(), a.getNewspaper()))
				res = false;
		return res;
	}
}
