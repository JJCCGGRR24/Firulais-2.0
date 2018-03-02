
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ServicceRepository;
import domain.Rendezvous;
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
	public Servicce create(final Rendezvous rendezvous) {
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
		this.servicceRepository.delete(service);
	}

	public void flush() {
		this.servicceRepository.flush();
	}

	// Other business methods -------------------------------------------------

}
