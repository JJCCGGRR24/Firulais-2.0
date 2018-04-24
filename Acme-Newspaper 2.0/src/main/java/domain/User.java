
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class User extends Actor {

	//Relationships
	private Collection<User>		follows;
	private Collection<User>		followers;
	private Collection<Newspaper>	newspapers;
	private Collection<Chirp>		chirps;
	private Collection<Article>		articles;
	private Collection<Volume>		volumes;


	@NotNull
	@Valid
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	public Collection<Volume> getVolumes() {
		return this.volumes;
	}

	public void setVolumes(final Collection<Volume> volumes) {
		this.volumes = volumes;
	}

	// Constructor
	public User() {
		super();
	}

	@OneToMany(mappedBy = "user")
	@Valid
	@NotNull
	public Collection<Newspaper> getNewspapers() {
		return this.newspapers;
	}

	public void setNewspapers(final Collection<Newspaper> newspapers) {
		this.newspapers = newspapers;
	}
	@OneToMany(mappedBy = "user")
	@Valid
	@NotNull
	public Collection<Chirp> getChirps() {
		return this.chirps;
	}

	public void setChirps(final Collection<Chirp> chirps) {
		this.chirps = chirps;
	}

	@ManyToMany()
	@Valid
	@NotNull
	public Collection<User> getFollows() {
		return this.follows;
	}

	public void setFollows(final Collection<User> follows) {
		this.follows = follows;
	}

	@ManyToMany(mappedBy = "follows")
	@Valid
	@NotNull
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
