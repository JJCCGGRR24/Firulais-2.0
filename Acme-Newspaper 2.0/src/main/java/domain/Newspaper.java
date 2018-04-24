
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
	@Index(name = "index_deprived", columnList = "deprived"), @Index(name = "index_publication", columnList = "publicationDate"), @Index(name = "index_title", columnList = "title"), @Index(name = "index_taboo", columnList = "tabooWord"),
	@Index(name = "index_description", columnList = "description")
})
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
	@Size(max = 255)
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
	@Size(max = 65000)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@URL
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Size(max = 255)
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

	public boolean getDeprived() {
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
