
package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RequestRepository;
import security.LoginService;
import domain.Rendezvous;
import domain.Request;

;

@Service
@Transactional
public class RequestService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private RequestRepository	requestRepository;


	//	@Autowired
	//	private ServicceService		servicceService;

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
		Assert.isTrue(LoginService.isPrincipalUser());
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

	public List<Request> requestsByRendezvous(final int rendezvousId) {
		return this.requestRepository.requestsByRendezvous(rendezvousId);
	}

	//	public Request reconstruct(RequestForm rf){
	//		
	//		Request r = null;
	//		if(rf.getRequestId()==0)
	//		 r = new Request();
	//		else{
	//			r = findOne(rf.getRequestId());
	//		}
	//		r.setComment(rf.getComment());
	//		r.setServicce(servicceService.findOne(rf.getServicceId()));
	//	}

}
