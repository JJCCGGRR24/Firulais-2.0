
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AgentRepository;
import domain.Agent;

;

@Service
@Transactional
public class AgentService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private AgentRepository	agentRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public AgentService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Agent create() {
		final Agent r = new Agent();
		return r;
	}

	public Collection<Agent> findAll() {
		final Collection<Agent> res = this.agentRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Agent findOne(final int agentId) {
		return this.agentRepository.findOne(agentId);
	}

	public Agent save(final Agent agent) {
		Assert.notNull(agent);
		return this.agentRepository.save(agent);
	}

	public void delete(final Agent agent) {
		this.agentRepository.delete(agent);
	}

	public void flush() {
		this.agentRepository.flush();
	}

	// Other business methods -------------------------------------------------

}
