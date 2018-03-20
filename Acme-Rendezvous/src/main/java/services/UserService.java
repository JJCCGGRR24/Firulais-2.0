
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
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
import domain.Actor;
import domain.Answer;
import domain.Comment;
import domain.Rendezvous;
import domain.Reply;
import domain.User;
import forms.RegisterForm;

@Service
@Transactional
public class UserService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private UserRepository	userRepository;


	// Supporting services ----------------------------------------------------
	//	@Autowired
	//	private Validator		validator;

	// Constructors -----------------------------------------------------------
	public UserService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public User create() {

		final User r = new User();

		final UserAccount userAccount = new UserAccount();
		final Authority aut = new Authority();
		aut.setAuthority(Authority.USER);
		final Collection<Authority> authorities = userAccount.getAuthorities();
		authorities.add(aut);
		userAccount.setAuthorities(authorities);

		final List<Answer> answers = new ArrayList<>();
		final List<Comment> comments = new ArrayList<>();
		final List<Rendezvous> rendezvouses = new ArrayList<>();
		final List<Rendezvous> rSVPd = new ArrayList<>();
		final List<Reply> replies = new ArrayList<>();

		r.setAnswers(answers);
		r.setComments(comments);
		r.setRendezvouses(rendezvouses);
		r.setRSVPd(rSVPd);
		r.setUserAccount(userAccount);
		r.setReplies(replies);

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

	public User findOneToEdit(final int userId) {
		Assert.isTrue(this.findOne(userId).getUserAccount().equals(LoginService.getPrincipal()));
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

	public User findByPrincipal() {
		final UserAccount userAccount = LoginService.getPrincipal();
		final User user = this.userRepository.findByPrincipal(userAccount.getId());
		Assert.isTrue(user.getUserAccount().equals(userAccount));
		return user;
	}

	public Boolean isAuthenticated() {
		Boolean res = true;
		try {
			LoginService.getPrincipal();
		} catch (final Exception e) {
			res = false;
		}

		return res;
	}

	public Boolean isUser() {
		Boolean res = false;
		try {
			final Authority aut = new Authority();
			aut.setAuthority(Authority.USER);

			res = LoginService.getPrincipal().getAuthorities().contains(aut);
		} catch (final Exception e) {
			res = false;
		}

		return res;
	}

	public Double[] queryC1() {
		Assert.isTrue(LoginService.isPrincipalAdmin());
		final Double[] d = this.userRepository.queryC1();
		return d;
	}
	public Double queryC2() {
		Assert.isTrue(LoginService.isPrincipalAdmin());
		Double res = 0.;
		if (!(this.userRepository.queryC2denominador() == 0))
			res = this.userRepository.queryC2();
		return res;
	}

	public Double[] queryC4() {
		Assert.isTrue(LoginService.isPrincipalAdmin());
		return this.userRepository.queryC4();
	}

	public int getAge(final Date dateOfBirth) {
		//code from: http://memorynotfound.com/calculate-age-from-date-of-birth-in-java/
		final Calendar today = Calendar.getInstance();
		final Calendar birthDate = Calendar.getInstance();
		birthDate.setTime(dateOfBirth);
		if (birthDate.after(today))
			throw new IllegalArgumentException("You don't exist yet");
		final int todayYear = today.get(Calendar.YEAR);
		final int birthDateYear = birthDate.get(Calendar.YEAR);
		final int todayDayOfYear = today.get(Calendar.DAY_OF_YEAR);
		final int birthDateDayOfYear = birthDate.get(Calendar.DAY_OF_YEAR);
		final int todayMonth = today.get(Calendar.MONTH);
		final int birthDateMonth = birthDate.get(Calendar.MONTH);
		final int todayDayOfMonth = today.get(Calendar.DAY_OF_MONTH);
		final int birthDateDayOfMonth = birthDate.get(Calendar.DAY_OF_MONTH);
		int age = todayYear - birthDateYear;

		// If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year
		if ((birthDateDayOfYear - todayDayOfYear > 3) || (birthDateMonth > todayMonth))
			age--;
		else if ((birthDateMonth == todayMonth) && (birthDateDayOfMonth > todayDayOfMonth))
			age--;
		return age;
	}

	public Boolean isAdult(final Actor actor) {
		Boolean result = false;

		final int age = this.getAge(actor.getBirthdate());

		if (age >= 18)
			result = true;

		return result;

	}

	public Collection<Rendezvous> isNotAdultUser(final Collection<Rendezvous> rendezvouses) {
		final Collection<Rendezvous> result = new ArrayList<>();

		for (final Rendezvous rendezvous : rendezvouses)
			if (this.isAuthenticated()) {
				final User user = this.findByPrincipal();
				if (!this.isAdult(user) && rendezvous.getAdultsOnly() == false)
					result.add(rendezvous);
			} else if (rendezvous.getAdultsOnly() == false)
				result.add(rendezvous);

		return result;
	}

	public List<Rendezvous> removeAdultsOnly(final List<Rendezvous> rendezvouses) {
		final List<Rendezvous> result = new ArrayList<Rendezvous>();
		for (final Rendezvous r : rendezvouses)
			if (!r.getAdultsOnly())
				result.add(r);
		return result;
	}

	public User reconstruct(final RegisterForm form) {
		final User user = this.create();

		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final UserAccount u = user.getUserAccount();
		u.setPassword(encoder.encodePassword(form.getPassword(), null));
		u.setUsername(form.getUsername());
		user.setUserAccount(u);

		user.setName(form.getName());
		user.setSurname(form.getSurname());
		user.setEmail(form.getEmail());
		user.setPhone(form.getPhone());
		user.setBirthdate(form.getBirthdate());
		user.setPostalAddress(form.getPostalAddress());

		return user;
	}

}
