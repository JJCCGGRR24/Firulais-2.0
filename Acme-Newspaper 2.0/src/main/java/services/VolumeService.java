
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.VolumeRepository;
import domain.Volume;

;

@Service
@Transactional
public class VolumeService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private VolumeRepository	volumeRepository;


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
		return this.volumeRepository.save(volume);
	}

	public void delete(final Volume volume) {
		this.volumeRepository.delete(volume);
	}

	public void flush() {
		this.volumeRepository.flush();
	}

	// Other business methods -------------------------------------------------

}
