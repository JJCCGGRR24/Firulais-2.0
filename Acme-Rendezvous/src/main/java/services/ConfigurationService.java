
package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ConfigurationRepository;
import security.LoginService;
import domain.Configuration;

@Service
@Transactional
public class ConfigurationService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ConfigurationRepository	configurationRepository;


	//	@Autowired
	//	private LoginService			loginService;

	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public ConfigurationService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Collection<Configuration> findAll() {
		final Collection<Configuration> res = this.configurationRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Configuration find() {
		final List<Configuration> res = (List<Configuration>) this.findAll();
		return res.get(0);
	}

	public Configuration save(Configuration config) {
		Assert.notNull(config);
		this.checkAdmin();
		config = this.configurationRepository.save(config);
		return config;
	}

	public void flush() {
		this.configurationRepository.flush();
	}

	// Other business methods -------------------------------------------------

	public void checkAdmin() {
		Assert.isTrue(LoginService.isPrincipalAdmin());
	}

}
