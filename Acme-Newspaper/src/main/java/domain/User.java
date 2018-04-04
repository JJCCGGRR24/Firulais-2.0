
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class User extends Actor {

	//Relationships
	private Collection<User>		follows;
	private Collection<User>		followers;
	private Collection<Newspaper>	newspaper;
	private Collection<Chirp>		chirps;
	private Collection<Article>		articles;


	// Constructor
	public User() {
		super();
	}

	@OneToMany(mappedBy = "user")
	@ElementCollection
	public Collection<Newspaper> getNewspaper() {
		return this.newspaper;
	}

	public void setNewspaper(final Collection<Newspaper> newspaper) {
		this.newspaper = newspaper;
	}
	@OneToMany(mappedBy = "user")
	@ElementCollection
	public Collection<Chirp> getChirps() {
		return this.chirps;
	}

	public void setChirps(final Collection<Chirp> chirps) {
		this.chirps = chirps;
	}

	@OneToMany()
	@ElementCollection
	public Collection<User> getFollows() {
		return this.follows;
	}

	public void setFollows(final Collection<User> follows) {
		this.follows = follows;
	}

	@OneToMany(mappedBy = "follows")
	@ElementCollection
	public Collection<User> getFollowers() {
		return this.followers;
	}

	public void setFollowers(final Collection<User> followers) {
		this.followers = followers;
	}

	@OneToMany(mappedBy = "user")
	@NotNull
	@Valid
	public Collection<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(final Collection<Article> articles) {
		this.articles = articles;
	}

}
