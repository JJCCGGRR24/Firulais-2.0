
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ManagerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Manager;
import domain.Servicce;
import forms.RegisterManagerForm;

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
	public Manager create() {
		final Manager r = new Manager();

		final UserAccount userAccount = new UserAccount();
		final Authority aut = new Authority();
		aut.setAuthority(Authority.MANAGER);
		final Collection<Authority> authorities = userAccount.getAuthorities();
		authorities.add(aut);
		userAccount.setAuthorities(authorities);

		final List<Servicce> servicces = new ArrayList<>();

		r.setUserAccount(userAccount);
		r.setServicces(servicces);

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

	public Collection<Manager> queryNewC2() {
		Assert.isTrue(LoginService.isPrincipalAdmin(), "Principal login isn't admin");
		return this.managerRepository.queryNewC2();
	}

	public Collection<Manager> queryNewC3() {
		Assert.isTrue(LoginService.isPrincipalAdmin(), "Principal login isn't admin");
		final List<Manager> pre = this.managerRepository.queryNewC3();
		final List<Manager> post = new ArrayList<Manager>();
		for (final Manager m : pre)
			if (m != null)
				post.add(m);
		return post;
	}
	public Manager reconstruct(final RegisterManagerForm form) {
		final Manager m = this.create();

		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final UserAccount u = m.getUserAccount();
		u.setPassword(encoder.encodePassword(form.getPassword(), null));
		u.setUsername(form.getUsername());
		m.setUserAccount(u);

		m.setName(form.getName());
		m.setSurname(form.getSurname());
		m.setEmail(form.getEmail());
		m.setPhone(form.getPhone());
		m.setBirthdate(form.getBirthdate());
		m.setPostalAddress(form.getPostalAddress());
		m.setVat(form.getVat());

		return m;
	}
}
