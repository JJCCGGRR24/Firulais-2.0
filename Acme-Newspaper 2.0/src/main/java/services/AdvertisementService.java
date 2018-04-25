
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Advertisement;
import repositories.AdvertisementRepository;

;

@Service
@Transactional
public class AdvertisementService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private AdvertisementRepository advertisementRepository;

	// Supporting services ----------------------------------------------------


	// Constructors -----------------------------------------------------------
	public AdvertisementService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Advertisement create() {
		final Advertisement r = new Advertisement();
		return r;
	}

	public Collection<Advertisement> findAll() {
		final Collection<Advertisement> res = this.advertisementRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Advertisement findOne(final int advertisementId) {
		return this.advertisementRepository.findOne(advertisementId);
	}

	public Advertisement save(final Advertisement advertisement) {
		Assert.notNull(advertisement);
		return this.advertisementRepository.save(advertisement);
	}

	public void delete(final Advertisement advertisement) {
		this.advertisementRepository.delete(advertisement);
	}

	public void flush() {
		this.advertisementRepository.flush();
	}

	// Other business methods -------------------------------------------------

	public Collection<Advertisement> getAdvertisementsWithTabbooWords() {
		return this.advertisementRepository.getAdvertisementsWithTabooWords();
	}

	public Advertisement chooseOne() {
		final ArrayList<Advertisement> advertisements = new ArrayList<>(this.findAll());
		Advertisement advertisement = advertisements.get(0);
		final int ramdon = (int) (Math.random() * advertisements.size());

		if (ramdon < advertisements.size())
			advertisement = advertisements.get(ramdon);

		return advertisement;
	}

}
