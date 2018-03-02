
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AnnouncementRepository;
import security.LoginService;
import domain.Announcement;
import domain.Rendezvous;
import domain.User;

;

@Service
@Transactional
public class AnnouncementService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private AnnouncementRepository	announcementRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private LoginService			loginService;


	//	@Autowired
	//	private Validator				validator;

	// Constructors -----------------------------------------------------------
	public AnnouncementService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Announcement create(final Rendezvous rendezvous) {

		Assert.isTrue(((User) this.loginService.getPrincipalActor()).equals(rendezvous.getUser()));
		final Announcement r = new Announcement();
		r.setRendezvous(rendezvous);
		r.setMoment(new Date(System.currentTimeMillis() - 1000));
		return r;
	}
	public Collection<Announcement> findAll() {
		final Collection<Announcement> res = this.announcementRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Announcement findOne(final int announcementId) {
		return this.announcementRepository.findOne(announcementId);
	}

	public Announcement save(final Announcement announcement) {
		Assert.notNull(announcement);
		announcement.setMoment(new Date(System.currentTimeMillis() - 1000));
		return this.announcementRepository.save(announcement);
	}

	public void delete(final Announcement announcement) {
		this.announcementRepository.delete(announcement);
	}

	public void flush() {
		this.announcementRepository.flush();
	}

	// Other business methods -------------------------------------------------

	public Collection<Announcement> getAnnouncementsByRendezvousId(final int rendezvousId) {
		return this.announcementRepository.getAnnouncementsByRendezvousId(rendezvousId);
	}

}
