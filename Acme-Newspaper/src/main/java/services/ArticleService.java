
package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ArticleRepository;
import domain.Article;
import domain.User;

;

@Service
@Transactional
public class ArticleService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ArticleRepository	articleRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public ArticleService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Article create() {

		final Article r = new Article();
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
		return this.articleRepository.save(article);
	}

	public void delete(final Article article) {
		this.articleRepository.delete(article);
	}

	public void flush() {
		this.articleRepository.flush();
	}

	// Other business methods -------------------------------------------------

	public List<Article> findByUser(final User user) {
		return this.articleRepository.findByUser(user);
	}

}
