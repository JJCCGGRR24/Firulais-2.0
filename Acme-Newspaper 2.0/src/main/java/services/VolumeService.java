
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.VolumeRepository;
import domain.Newspaper;
import domain.SubscribeVol;
import domain.Volume;

;

@Service
@Transactional
public class VolumeService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private VolumeRepository	volumeRepository;

	@Autowired
	private SubscribeService	subscribeService;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public VolumeService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Volume create() {
		final Volume r = new Volume();
		return r;
	}

	public Collection<Volume> findAll() {
		final Collection<Volume> res = this.volumeRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Volume findOne(final int volumeId) {
		return this.volumeRepository.findOne(volumeId);
	}

	public Volume save(final Volume volume) {
		Assert.notNull(volume);
		this.updateSubscribes(volume);
		return this.volumeRepository.save(volume);
	}

	private void updateSubscribes(final Volume volume) {
		for (final SubscribeVol c : volume.getSubscribesVol())
			for (final Newspaper n : volume.getNewspapers())
				if (!this.subscribeService.estaSubscrito(c.getCustomer(), n))
					this.subscribeService.subscribe(n, c.getCreditCard());
	}

	public void delete(final Volume volume) {
		this.volumeRepository.delete(volume);
	}

	public void flush() {
		this.volumeRepository.flush();
	}

	// Other business methods -------------------------------------------------

}
