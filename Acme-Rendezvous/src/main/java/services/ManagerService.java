
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ManagerRepository;
import domain.Manager;
import domain.Rendezvous;

;

@Service
@Transactional
public class ManagerService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ManagerRepository	managerRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public ManagerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Manager create(final Rendezvous rendezvous) {
		final Manager r = new Manager();
		return r;
	}

	public Collection<Manager> findAll() {
		final Collection<Manager> res = this.managerRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Manager findOne(final int managerId) {
		return this.managerRepository.findOne(managerId);
	}

	public Manager save(final Manager manager) {
		Assert.notNull(manager);
		return this.managerRepository.save(manager);
	}

	public void delete(final Manager manager) {
		this.managerRepository.delete(manager);
	}

	public void flush() {
		this.managerRepository.flush();
	}

	// Other business methods -------------------------------------------------

}
