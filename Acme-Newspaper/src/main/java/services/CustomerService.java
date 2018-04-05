
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.Authority;
import security.UserAccount;
import domain.Customer;
import domain.Subscribe;
import forms.RegisterForm;

;

@Service
@Transactional
public class CustomerService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private CustomerRepository	customerRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public CustomerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Customer create() {

		final Customer r = new Customer();
		final UserAccount uA = new UserAccount();
		final Authority au = new Authority();
		au.setAuthority("CUSTOMER");
		final List<Authority> authorities = new ArrayList<Authority>();
		authorities.add(au);
		uA.setAuthorities(authorities);
		r.setUserAccount(uA);

		r.setSubscribes(new ArrayList<Subscribe>());
		return r;
	}

	public Collection<Customer> findAll() {
		final Collection<Customer> res = this.customerRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Customer findOne(final int customerId) {
		return this.customerRepository.findOne(customerId);
	}

	public Customer save(final Customer customer) {
		Assert.notNull(customer);
		return this.customerRepository.save(customer);
	}

	public void delete(final Customer customer) {
		this.customerRepository.delete(customer);
	}

	public void flush() {
		this.customerRepository.flush();
	}

	public Customer reconstruct(final RegisterForm registerForm) {
		final Customer custo = this.create();
		final String pass = registerForm.getPassword();
		final String username = registerForm.getUsername();

		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final UserAccount u = custo.getUserAccount();
		u.setUsername(username);
		u.setPassword(encoder.encodePassword(pass, null));
		custo.setUserAccount(u);

		custo.setName(registerForm.getName());
		custo.setPhone(registerForm.getPhone());
		custo.setPostalAddress(registerForm.getPostalAddress());
		custo.setSurname(registerForm.getUsername());
		custo.setEmail(registerForm.getEmail());

		return custo;
	}

	// Other business methods -------------------------------------------------

}
