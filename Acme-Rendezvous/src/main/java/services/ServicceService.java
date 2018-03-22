
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Manager;
import domain.Request;
import domain.Servicce;
import repositories.ServicceRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class ServicceService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ServicceRepository	servicceRepository;

	@Autowired
	private LoginService		loginService;

	// Supporting services ----------------------------------------------------


	// Constructors -----------------------------------------------------------
	public ServicceService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Servicce create() {
		final Servicce r = new Servicce();

		r.setCancelled(false);
		r.setManager((Manager) this.loginService.getPrincipalActor());

		final ArrayList<Request> requests = new ArrayList<>();
		r.setRequests(requests);

		return r;
	}

	public Collection<Servicce> findAll() {
		final Collection<Servicce> res = this.servicceRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Servicce findOne(final int serviceId) {
		return this.servicceRepository.findOne(serviceId);
	}

	public Servicce save(final Servicce service) {
		Assert.notNull(service);
		this.checkIsPrincipal(service);
		Assert.isTrue(service.getRequests().size() == 0, "Cannot modifie service");
		return this.servicceRepository.save(service);
	}

	public void delete(final Servicce service) {
		this.checkIsPrincipal(service);
		Assert.isTrue(service.getRequests().size() == 0, "Cannot modifie service");
		service.setCategory(null);

		this.save(service);
		this.servicceRepository.delete(service);
	}
	public void flush() {
		this.servicceRepository.flush();
	}

	// Other business methods -------------------------------------------------

	public void cancell(final Servicce servicce) {
		Assert.isTrue(servicce.getCancelled() == false, "The service is cancelled already");
		servicce.setCancelled(true);
		this.servicceRepository.save(servicce);
	}

	public List<Servicce> servicesByRendezvous(final int rendezvousId) {
		return this.servicceRepository.servicesByRendezvous(rendezvousId);
	}

	public Collection<Servicce> queryNewC1B4() {
		return this.servicceRepository.queryNewC1B4();
	}

	public void checkIsPrincipal(final Servicce servicce) {
		final UserAccount principal = LoginService.getPrincipal();
		Assert.isTrue(servicce.getManager().getUserAccount().getUsername().equals(principal.getUsername()));
	}

	public Collection<Servicce> serviccesVisibles() {
		return this.servicceRepository.serviccesVisibles();
	}
}
