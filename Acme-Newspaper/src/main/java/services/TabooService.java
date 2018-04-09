
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TabooRepository;
import domain.Taboo;

;

@Service
@Transactional
public class TabooService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private TabooRepository	tabooRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public TabooService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Taboo create() {

		final Taboo r = new Taboo();
		return r;
	}

	public Collection<Taboo> findAll() {
		final Collection<Taboo> res = this.tabooRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Taboo findOne(final int tabooId) {
		return this.tabooRepository.findOne(tabooId);
	}

	public Taboo save(final Taboo taboo) {
		Assert.notNull(taboo);
		Assert.isTrue(!this.findAll().contains(this.getTabooFromWord(taboo.getWord())), "Palabra taboo repetida");
		return this.tabooRepository.save(taboo);
	}

	public void delete(final Taboo taboo) {
		this.tabooRepository.delete(taboo);
	}

	public void flush() {
		this.tabooRepository.flush();
	}

	// Other business methods -------------------------------------------------

	public Taboo getTabooFromWord(final String word) {
		return this.tabooRepository.getTabooFromWord(word);
	}
}
