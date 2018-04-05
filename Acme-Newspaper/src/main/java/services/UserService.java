
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Article;
import domain.Chirp;
import domain.Newspaper;
import domain.User;
import forms.RegisterForm;

;

@Service
@Transactional
public class UserService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private UserRepository	userRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public UserService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public User create() {

		final User r = new User();

		final UserAccount uA = new UserAccount();
		final Authority au = new Authority();
		au.setAuthority("USER");
		final List<Authority> authorities = new ArrayList<Authority>();
		authorities.add(au);
		uA.setAuthorities(authorities);
		r.setUserAccount(uA);

		final Collection<Newspaper> newspapers = new ArrayList<Newspaper>();
		r.setNewspapers(newspapers);

		final Collection<User> follows = new ArrayList<User>();
		r.setFollows(follows);

		final Collection<User> followers = new ArrayList<User>();
		r.setFollowers(followers);

		final Collection<Chirp> chirps = new ArrayList<Chirp>();
		r.setChirps(chirps);

		final Collection<Article> articles = new ArrayList<Article>();
		r.setArticles(articles);

		return r;
	}

	public Collection<User> findAll() {
		final Collection<User> res = this.userRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public User findOne(final int userId) {
		return this.userRepository.findOne(userId);
	}

	public User save(final User user) {
		Assert.notNull(user);
		return this.userRepository.save(user);
	}

	public void delete(final User user) {
		this.userRepository.delete(user);
	}

	public void flush() {
		this.userRepository.flush();
	}

	// Other business methods -------------------------------------------------
	public User reconstruct(final RegisterForm registerForm) {

		final User user = this.create();
		final String pass = registerForm.getPassword();
		final String username = registerForm.getUsername();

		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final UserAccount u = user.getUserAccount();
		u.setUsername(username);
		u.setPassword(encoder.encodePassword(pass, null));
		user.setUserAccount(u);

		user.setName(registerForm.getName());
		user.setPhone(registerForm.getPhone());
		user.setPostalAddress(registerForm.getPostalAddress());
		user.setSurname(registerForm.getUsername());
		user.setEmail(registerForm.getEmail());

		return user;
	}
	public User findByPrincipal() {
		final UserAccount userAccount = LoginService.getPrincipal();
		final User user = this.userRepository.findByPrincipal(userAccount.getId());
		Assert.isTrue(user.getUserAccount().equals(userAccount));
		return user;
	}
}
