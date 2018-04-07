
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Newspaper extends DomainEntity {

	//Attributes

	private String	title;
	private Date	publicationDate;
	private String	description;
	private String	picture;
	private boolean	deprived;
	private boolean	tabooWord;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getPublicationDate() {
		return this.publicationDate;
	}

	public void setPublicationDate(final Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@URL
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

	public boolean isDeprived() {
		return this.deprived;
	}

	public void setDeprived(final boolean deprived) {
		this.deprived = deprived;
	}

	public boolean isTabooWord() {
		return this.tabooWord;
	}

	public void setTabooWord(final boolean tabooWord) {
		this.tabooWord = tabooWord;
	}


	//Relationships

	private Collection<Article>		articles;
	private User					user;
	private Collection<Subscribe>	subscribes;


	@OneToMany(mappedBy = "newspaper", cascade = CascadeType.REMOVE)
	@Valid
	@NotNull
	public Collection<Subscribe> getSubscribes() {
		return this.subscribes;
	}

	public void setSubscribes(final Collection<Subscribe> subscribes) {
		this.subscribes = subscribes;
	}

	@OneToMany(mappedBy = "newspaper", cascade = CascadeType.REMOVE)
	@Valid
	@NotNull
	public Collection<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(final Collection<Article> articles) {
		this.articles = articles;
	}

	@ManyToOne(optional = true)
	@Valid
	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

}
