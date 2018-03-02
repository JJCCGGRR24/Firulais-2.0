
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdminRepository;
import domain.Admin;

;

@Service
@Transactional
public class AdminService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private AdminRepository	adminRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public AdminService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Admin create() {

		final Admin r = new Admin();
		return r;
	}

	public Collection<Admin> findAll() {
		final Collection<Admin> res = this.adminRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Admin findOne(final int adminId) {
		return this.adminRepository.findOne(adminId);
	}

	public Admin save(final Admin admin) {
		Assert.notNull(admin);
		return this.adminRepository.save(admin);
	}

	public void delete(final Admin admin) {
		this.adminRepository.delete(admin);
	}

	public void flush() {
		this.adminRepository.flush();
	}

	// Other business methods -------------------------------------------------

}
