
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import domain.Category;
import domain.Rendezvous;

;

@Service
@Transactional
public class CategoryService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private CategoryRepository	categoryRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public CategoryService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Category create(final Rendezvous rendezvous) {
		final Category r = new Category();
		return r;
	}

	public Collection<Category> findAll() {
		final Collection<Category> res = this.categoryRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Category findOne(final int categoryId) {
		return this.categoryRepository.findOne(categoryId);
	}

	public Category save(final Category category) {
		Assert.notNull(category);
		return this.categoryRepository.save(category);
	}

	public void delete(final Category category) {
		this.categoryRepository.delete(category);
	}

	public void flush() {
		this.categoryRepository.flush();
	}

	// Other business methods -------------------------------------------------

}
