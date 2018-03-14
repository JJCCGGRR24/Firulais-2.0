
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RendezvousRepository;
import security.LoginService;
import security.UserAccount;
import domain.Announcement;
import domain.Comment;
import domain.Question;
import domain.Rendezvous;
import domain.User;
import forms.LinkForm;

@Service
@Transactional
public class RendezvousService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private RendezvousRepository	rendezvousRepository;

	@Autowired
	private LoginService			loginService;

	// Supporting services ----------------------------------------------------
	@Autowired
	private UserService				userService;


	//	@Autowired
	//	private Validator				validator;

	// Constructors -----------------------------------------------------------
	public RendezvousService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Rendezvous create() {
		final Rendezvous r = new Rendezvous();

		final User user = this.userService.findByPrincipal();
		r.setUser(user);

		final List<Rendezvous> rendezvous = new ArrayList<>();
		r.setLinkedIn(rendezvous);
		r.setLinkedTo(rendezvous);

		final List<User> users = new ArrayList<>();
		r.setUsers(users);

		final List<Comment> comments = new ArrayList<>();
		r.setComments(comments);

		final List<Announcement> announcements = new ArrayList<>();
		r.setAnnouncements(announcements);

		final List<Question> questions = new ArrayList<>();
		r.setQuestions(questions);

		r.setFinalMode(false);
		r.setDeleted(false);
		r.setAdultsOnly(false);

		return r;
	}

	public Collection<Rendezvous> findAll() {
		final Collection<Rendezvous> res = this.rendezvousRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Rendezvous findOne(final int rendezvousId) {
		return this.rendezvousRepository.findOne(rendezvousId);
	}
	public Rendezvous update(final Rendezvous rendezvous) {
		Assert.notNull(rendezvous);
		Assert.isTrue(rendezvous.getUser().getUserAccount().getUsername().equals(LoginService.getPrincipal().getUsername()));

		Rendezvous saved;
		saved = this.rendezvousRepository.save(rendezvous);

		return saved;
	}

	public Rendezvous save(final Rendezvous rendezvous) {
		Assert.notNull(rendezvous);
		final User user = rendezvous.getUser();
		Rendezvous saved;

		saved = this.rendezvousRepository.save(rendezvous);

		user.getRSVPd().add(saved);
		user.getRendezvouses().add(saved);
		this.userService.save(user);

		return saved;
	}

	public void delete(final Rendezvous rendezvous) {
		final User user = rendezvous.getUser();

		user.getRendezvouses().remove(rendezvous);
		this.rendezvousRepository.delete(rendezvous);

		this.userService.save(user);

	}

	public void flush() {
		this.rendezvousRepository.flush();
	}

	// Other business methods -------------------------------------------------

	public List<Rendezvous> listRSVPdPast(final int userId) {
		return this.rendezvousRepository.listRSVPdPast(userId);
	}

	public List<Rendezvous> listRSVPdFuture(final int userId) {
		return this.rendezvousRepository.listRSVPdFuture(userId);
	}

	public List<Rendezvous> listRendezvousPast() {
		return this.rendezvousRepository.listRendezvousPast();
	}

	public List<Rendezvous> listRendezvousFuture() {
		return this.rendezvousRepository.listRendezvousFuture();
	}

	public List<Rendezvous> listRendezvousPastFF() {
		return this.rendezvousRepository.listRendezvousPastFF();
	}

	public List<Rendezvous> listRendezvousFutureFF() {
		return this.rendezvousRepository.listRendezvousFutureFF();
	}

	public void checkIsPrincipal(final Rendezvous rendezvous) {
		final UserAccount principal = LoginService.getPrincipal();
		Assert.isTrue(rendezvous.getUser().getUserAccount().getUsername().equals(principal.getUsername()));
	}

	public Double[] queryC3() {
		Assert.isTrue(LoginService.isPrincipalAdmin());
		return this.rendezvousRepository.queryC3();
	}

	public Collection<Rendezvous> queryC5() {
		Assert.isTrue(LoginService.isPrincipalAdmin());
		Collection<Rendezvous> result;
		Page<Rendezvous> resPage;
		Pageable pageable;
		pageable = new PageRequest(0, 10);
		resPage = this.rendezvousRepository.queryC5(pageable);
		result = resPage.getContent();
		return result;
	}

	public Double[] queryB1() {
		Assert.isTrue(LoginService.isPrincipalAdmin(), "Principal login isn't admin");
		return this.rendezvousRepository.queryB1();
	}

	public Collection<Rendezvous> queryB2() {
		Assert.isTrue(LoginService.isPrincipalAdmin(), "Principal login isn't admin");
		return this.rendezvousRepository.queryB2();
	}

	public Collection<Rendezvous> queryB3() {
		Assert.isTrue(LoginService.isPrincipalAdmin(), "Principal login isn't admin");
		return this.rendezvousRepository.queryB3();
	}

	public Double[] queryA1() {
		Assert.isTrue(LoginService.isPrincipalAdmin(), "Principal login isn't admin");
		return this.rendezvousRepository.queryA1();
	}

	public static double calculateAvg(final Collection<Long> numbers) {
		double sum = 0;
		final int N = numbers.size();
		for (final Long num : numbers)
			sum += num;
		final double avg = sum / N;
		return avg;
	}
	public static double calculateStdev(final Collection<Long> numbers) {
		double sum = 0.0;
		double sttdev = 0.0;
		final int N = numbers.size();
		final double avg = RendezvousService.calculateAvg(numbers);
		for (final double num : numbers)
			sum += Math.pow(num - avg, 2);
		sttdev = Math.sqrt(sum / N);
		return sttdev;
	}
	public Double[] queryA2() {
		final double answers = this.rendezvousRepository.queryA2partAnswer();
		final double rendezvous = this.rendezvousRepository.queryA2partRendezvous();
		final double sttdev = RendezvousService.calculateStdev(this.rendezvousRepository.queryA2partSttdev());
		final Double[] res = {
			answers / rendezvous, sttdev
		};
		return res;
	}

	public Double queryNewB1() {
		Assert.isTrue(LoginService.isPrincipalAdmin(), "Principal login isn't admin");
		return this.rendezvousRepository.queryNewB1();
	}

	//	public List<Rendezvous> listRSVPd() {
	//		return this.rendezvousRepository.listRSVPd(this.loginService.getPrincipalActor().getId());
	//	}

	public Collection<Rendezvous> listMyPastRendezvous() {
		return this.rendezvousRepository.listMyPastRendezvous(this.loginService.getPrincipalActor().getId());
	}

	public Collection<Rendezvous> listMyFutureRendezvous() {
		return this.rendezvousRepository.listMyFutureRendezvous(this.loginService.getPrincipalActor().getId());
	}

	public Collection<Rendezvous> isNotAthenticated(final Collection<Rendezvous> rendezvouses) {
		final ArrayList<Rendezvous> result = new ArrayList<>();

		for (final Rendezvous rendezvous : rendezvouses)
			if (rendezvous.getAdultsOnly() == false)
				result.add(rendezvous);

		return result;
	}

	public List<Rendezvous> posibleToLink(final Rendezvous r) {
		final List<Rendezvous> rs = this.rendezvousRepository.posibleToLink();
		rs.removeAll(r.getLinkedTo());
		rs.remove(r);
		return rs;
	}

	public Rendezvous link(final LinkForm l) {
		Assert.isTrue(LoginService.isPrincipalUser());
		final Rendezvous r = this.findOne(l.getId());
		final List<Rendezvous> rs = r.getLinkedTo();
		rs.add(this.findOne(l.getR().getId()));
		r.setLinkedTo(rs);
		final Rendezvous saved = this.save(r);
		return saved;
	}
}
