
package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.LoginService;
import domain.Administrator;
import domain.Newspaper;

;

@Service
@Transactional
public class AdministratorService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private AdministratorRepository	administratorRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public AdministratorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Administrator create() {

		final Administrator r = new Administrator();
		return r;
	}

	public Collection<Administrator> findAll() {
		final Collection<Administrator> res = this.administratorRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Administrator findOne(final int administratorId) {
		return this.administratorRepository.findOne(administratorId);
	}

	public Administrator save(final Administrator administrator) {
		Assert.notNull(administrator);
		return this.administratorRepository.save(administrator);
	}

	public void delete(final Administrator administrator) {
		this.administratorRepository.delete(administrator);
	}

	public void flush() {
		this.administratorRepository.flush();
	}

	// Other business methods -------------------------------------------------

	public Double[] queryC1() {
		Assert.isTrue(LoginService.isPrincipalAdmin());
		return this.administratorRepository.queryC1();
	}

	public Double[] queryC2() {
		return this.administratorRepository.queryC2();
	}

	public Double[] queryC3() {
		return this.administratorRepository.queryC3();
	}

	public List<Newspaper> queryC4() {
		return this.administratorRepository.queryC4();
	}

	public List<Newspaper> queryC5() {
		return this.administratorRepository.queryC5();
	}

	public Double queryC6() {
		return this.administratorRepository.queryC6();
	}

	public Double queryC7() {
		return this.administratorRepository.queryC7();
	}

	public Double queryB1() {
		Assert.isTrue(LoginService.isPrincipalAdmin());
		return this.administratorRepository.queryB1();
	}

	public Double queryB2() {
		return this.administratorRepository.queryB2();
	}

	public Double queryB3() {
		return this.administratorRepository.queryB3();
	}

	public Double[] queryB4() {
		return this.administratorRepository.queryB4();
	}

	public Double queryB5() {
		return this.administratorRepository.queryB5();
	}

	public Double queryA1() {
		Assert.isTrue(LoginService.isPrincipalAdmin());
		return this.administratorRepository.queryA1();
	}

	public Double queryA2() {
		return this.administratorRepository.queryA2();
	}

	public Double queryA3() {
		return this.administratorRepository.queryA3();
	}

	public Double queryA4() {
		return this.administratorRepository.queryA4();
	}

	public Double queryA5() {
		return this.administratorRepository.queryA5();
	}

}
