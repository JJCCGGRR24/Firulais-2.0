
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
	private Collection<SubscribeVol>	suscribesVol;


	@Valid
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "customer")
	public Collection<SubscribeVol> getSuscribesVol() {
		return this.suscribesVol;
	}

	public void setSuscribesVol(final Collection<SubscribeVol> suscribesVol) {
		this.suscribesVol = suscribesVol;
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
