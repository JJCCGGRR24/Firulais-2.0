
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.NewspaperRepository;
import domain.Article;
import domain.Newspaper;
import domain.Subscribe;
import domain.User;

;

@Service
@Transactional
public class NewspaperService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private NewspaperRepository	newspaperRepository;

	@Autowired
	private UserService			userService;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public NewspaperService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Newspaper create() {

		final Newspaper r = new Newspaper();
		final List<Article> articles = new ArrayList<>();
		r.setArticles(articles);
		r.setDeprived(false);
		final List<Subscribe> subscribes = new ArrayList<>();
		r.setSubscribes(subscribes);
		r.setTabooWord(false);
		final User user = this.userService.findByPrincipal();
		r.setUser(user);
		return r;
	}

	public Collection<Newspaper> findAll() {
		final Collection<Newspaper> res = this.newspaperRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Newspaper findOne(final int newspaperId) {
		return this.newspaperRepository.findOne(newspaperId);
	}

	public Newspaper save(final Newspaper newspaper) {
		Assert.notNull(newspaper);
		Assert.isTrue(newspaper.getPublicationDate() == null);
		return this.newspaperRepository.save(newspaper);
	}

	public void delete(final Newspaper newspaper) {
		Assert.notNull(newspaper);
		this.newspaperRepository.delete(newspaper);
	}

	public void flush() {
		this.newspaperRepository.flush();
	}

	// Other business methods -------------------------------------------------

	public List<Newspaper> getPublishedNewspapers() {
		return this.newspaperRepository.getPublishedNewspapers();
	}

	public boolean isAllArticlesPublished(final Newspaper n) {
		boolean res = false;
		if (this.getArticlesNoPublished(n).size() == 0)
			res = true;
		return res;
	}

	public List<Newspaper> getArticlesNoPublished(final Newspaper n) {
		return this.newspaperRepository.getArticlesNoPublished(n);
	}

	public void publish(final Newspaper newspaper) {
		Assert.notNull(newspaper);
		Assert.isTrue(newspaper.getPublicationDate() == null);
		Assert.isTrue(this.isAllArticlesPublished(newspaper));
		newspaper.setPublicationDate(new Date());
		this.actualizaFechasArticles(newspaper);
		this.newspaperRepository.save(newspaper);
	}
	private void actualizaFechasArticles(final Newspaper n) {
		for (final Article a : n.getArticles())
			a.setMoment(n.getPublicationDate());
	}
}
