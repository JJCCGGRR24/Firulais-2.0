
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Actor {

	//Relationships
	private Collection<Subscribe>	subscribes;


	@OneToMany(mappedBy = "customer")
	@ElementCollection
	public Collection<Subscribe> getSubscribes() {
		return this.subscribes;
	}

	public void setSubscribes(final Collection<Subscribe> subscribes) {
		this.subscribes = subscribes;
	}

}
