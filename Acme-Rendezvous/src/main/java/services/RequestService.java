
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RequestRepository;
import domain.Request;
import domain.Rendezvous;

;

@Service
@Transactional
public class RequestService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private RequestRepository	requestRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public RequestService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Request create(final Rendezvous rendezvous) {
		final Request r = new Request();
		r.setRendezvous(rendezvous);
		return r;
	}

	public Collection<Request> findAll() {
		final Collection<Request> res = this.requestRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Request findOne(final int requestId) {
		return this.requestRepository.findOne(requestId);
	}

	public Request save(final Request request) {
		Assert.notNull(request);
		return this.requestRepository.save(request);
	}

	public void delete(final Request request) {
		this.requestRepository.delete(request);
	}

	public void flush() {
		this.requestRepository.flush();
	}

	// Other business methods -------------------------------------------------

}
