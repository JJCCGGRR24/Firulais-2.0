
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Article;
import domain.FollowUp;
import domain.Taboo;
import domain.User;
import repositories.ArticleRepository;
import security.LoginService;

@Service
@Transactional
public class ArticleService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ArticleRepository	articleRepository;

	@Autowired
	private LoginService		loginService;

	@Autowired
	private TabooService		tabooService;

	// Supporting services ----------------------------------------------------


	// Constructors -----------------------------------------------------------
	public ArticleService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Article create() {

		final Article r = new Article();
		r.setUser((User) this.loginService.getPrincipalActor());
		final Collection<FollowUp> follow_ups = new ArrayList<FollowUp>();
		r.setFollowUps(follow_ups);
		return r;
	}
	public Collection<Article> findAll() {
		final Collection<Article> res = this.articleRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Article findOne(final int articleId) {
		return this.articleRepository.findOne(articleId);
	}

	public Article save(final Article article) {
		Assert.notNull(article);
		if (article.getId() != 0)
			Assert.isTrue(!this.findOne(article.getId()).isFinalMode());
		article.setUser((User) this.loginService.getPrincipalActor());
		Assert.isTrue(article.getNewspaper().getPublicationDate() == null);
		article.setTabooWord(this.isTaboo(article));
		article.getNewspaper().getArticles().add(article);
		article.getUser().getArticles().add(article);
		return this.articleRepository.save(article);
	}
	public void delete(final Article article) {
		Assert.isTrue(LoginService.isPrincipalAdmin() || article.getUser().getId() == this.loginService.getPrincipalActor().getId());
		this.articleRepository.delete(article);
	}

	public void flush() {
		this.articleRepository.flush();
	}

	// Other business methods -------------------------------------------------

	public List<Article> findByUser(final User user) {
		return this.articleRepository.findByUser(user);
	}

	public List<Article> publicArticles() {
		return this.articleRepository.publicArticles();
	}

	public Collection<Article> search(final String search) {
		return this.articleRepository.search(search);

	}

	public String validate(final Article c) {
		String b = null;
		if (c.getMoment() != null)
			b = "article.error.articlePublicated";
		if (c.getNewspaper().getPublicationDate() != null)
			b = "article.error.newspaperPublicated";
		if (c.isFinalMode() == true)
			b = "article.error.finalMode";
		return b;
	}

	public boolean isTaboo(final Article article) {
		boolean b = false;
		for (final Taboo t : this.tabooService.findAll()) {
			final String s = t.getWord().toLowerCase();
			if (article.getTitle().toLowerCase().contains(s) || article.getBody().toLowerCase().contains(s) || article.getSummary().toLowerCase().contains(s)) {
				b = true;
				break;
			}
		}
		return b;
	}

	public List<Article> getArticlesTabooWords() {
		return this.articleRepository.getArticlesTabooWords();

	}

	public List<FollowUp> getPublishedArticlesByUser(final int id) {
		return this.articleRepository.getPublishedArticlesByUser(id);
	}

	public List<Article> getPublishedArticles() {
		return this.articleRepository.getPublishedArticles();
	}

}
