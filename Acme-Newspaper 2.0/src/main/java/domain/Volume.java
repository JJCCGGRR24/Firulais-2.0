
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class Volume extends DomainEntity {

	//Attributes

	private String	title;
	private String	description;
	private int		year;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}
	@Range(min = 2018, max = 3000)
	@NotNull
	public int getYear() {
		return this.year;
	}

	public void setYear(final int year) {
		this.year = year;
	}


	//Relationships
	private Collection<SubscribeVol>	subscribesVol;
	private User						user;
	private Collection<Newspaper>		newspapers;


	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	@ManyToMany(mappedBy = "volumes")
	public Collection<Newspaper> getNewspapers() {
		return this.newspapers;
	}

	public void setNewspapers(final Collection<Newspaper> newspapers) {
		this.newspapers = newspapers;
	}

	@Valid
	@OneToMany(mappedBy = "volume", cascade = CascadeType.REMOVE)
	public Collection<SubscribeVol> getSubscribesVol() {
		return this.subscribesVol;
	}

	public void setSubscribesVol(final Collection<SubscribeVol> subscribesVol) {
		this.subscribesVol = subscribesVol;
	}

}
