
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import domain.Category;
import domain.Servicce;

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
	public Category create() {
		final Category c = new Category();
		c.setCategoriesChildren(new ArrayList<Category>());
		c.setServicces(new ArrayList<Servicce>());
		return c;
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

	public void delete(final Category c) {
		this.deleteAux(c);
	}

	private void deleteAux(final Category c) {
		for (final Category cat : c.getCategoriesChildren())
			this.deleteAux(cat);
		for (final Servicce t : c.getServicces())
			t.setCategory(null);
		//		System.out.println("Intento de borrar: " + c.getName());
		c.setCategoriesChildren(new ArrayList<Category>());
		this.categoryRepository.delete(c);
		//		System.out.println(c.getName() + " eliminado.");
		//		System.out.println("");
	}

	public List<Category> getChildrenOf(final Category c) {
		final List<Category> l = this.getChildrenOfAux(c, new ArrayList<Category>());
		l.add(c);
		return l;
	}

	private List<Category> getChildrenOfAux(final Category c, final List<Category> lc) {
		if (c.getCategoriesChildren() != null)
			for (final Category cat : c.getCategoriesChildren()) {
				lc.add(cat);
				this.getChildrenOfAux(cat, lc);
			}
		return lc;
	}

	public List<Category> getFathersOf(final Category c) {
		final List<Category> l = this.getFathersOfAux(c, new ArrayList<Category>());
		l.add(c);
		return l;
	}

	private List<Category> getFathersOfAux(final Category c, final List<Category> lc) {
		if (c.getCategoryFather() != null) {
			lc.add(c.getCategoryFather());
			this.getFathersOfAux(c.getCategoryFather(), lc);
		}
		return lc;
	}

	public void flush() {
		this.categoryRepository.flush();
	}

	// Other business methods -------------------------------------------------

	@SuppressWarnings("unused")
	private boolean isDeleteable(final Category c) {
		return this.isDeleteableAux(c, true, 1);
	}

	private boolean isDeleteableAux(final Category c, boolean isDeleteable, int i) {
		final boolean isBasic = c.getCategoriesChildren().isEmpty();
		i++;
		if (!isBasic)
			for (final Category cat : c.getCategoriesChildren()) {
				i++;
				isDeleteable = isDeleteable & cat.getServicces().isEmpty() & this.isDeleteableAux(cat, isDeleteable, i);
			}
		else
			isDeleteable = isDeleteable & c.getServicces().isEmpty();
		return isDeleteable;
	}

	public List<Category> validFathers(final Category c) {
		final List<Category> lc = ((List<Category>) this.findAll());
		lc.removeAll(this.getChildrenOf(c));
		return lc;
	}

	public Category get(final String string) {
		return this.categoryRepository.get(string);
	}

	public String validate(final Category category) {
		String s = null;
		String name = null;
		if (category.getId() != 0) {
			final Category cBBDD = this.findOne(category.getId());
			name = cBBDD.getName();
			Assert.isTrue(category.getCategoryFather() == null || (category.getCategoryFather() != null && this.validFathers(cBBDD).contains(category.getCategoryFather())));
		}
		if (category.getId() == 0 || !category.getName().equals(name)) {
			List<Category> lcs = new ArrayList<Category>();
			if (category.getCategoryFather() == null)
				lcs = this.getFathers();
			else
				lcs = category.getCategoryFather().getCategoriesChildren();
			for (final Category cat : lcs)
				if (cat.getName().toUpperCase().equals(category.getName().toUpperCase())) {
					s = "category.used";
					break;
				}
		}
		return s;
	}
	public List<Category> getFathers() {
		return this.categoryRepository.getFathers();
	}

	public Double queryNewB2() {
		return this.categoryRepository.queryNewB2();
	}

	public Double[] queryNewB3() {
		return this.categoryRepository.queryNewB3();
	}
}
