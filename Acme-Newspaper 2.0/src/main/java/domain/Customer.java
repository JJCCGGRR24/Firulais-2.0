
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Actor {

	//Relationships
	private Collection<Subscribe>		subscribes;
	private Collection<SubscribeVol>	subscribesVol;


	@Valid
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "customer")
	public Collection<SubscribeVol> getSubscribesVol() {
		return this.subscribesVol;
	}

	public void setSubscribesVol(final Collection<SubscribeVol> subscribesVol) {
		this.subscribesVol = subscribesVol;
	}

	@OneToMany(mappedBy = "customer")
	@Valid
	@NotNull
	public Collection<Subscribe> getSubscribes() {
		return this.subscribes;
	}

	public void setSubscribes(final Collection<Subscribe> subscribes) {
		this.subscribes = subscribes;
	}

}
