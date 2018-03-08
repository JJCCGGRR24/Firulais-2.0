
package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ServicceRepository;
import security.LoginService;
import domain.Servicce;

;

@Service
@Transactional
public class ServicceService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ServicceRepository	servicceRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public ServicceService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Servicce create() {
		final Servicce r = new Servicce();
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
		return this.servicceRepository.save(service);
	}

	public void delete(final Servicce service) {
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
		Assert.isTrue(LoginService.isPrincipalAdmin(), "Principal login isn't admin");
		return this.servicceRepository.queryNewC1B4();
	}
}
