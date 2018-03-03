
package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class User extends Actor {

	// Attributes -------------------------------------------------------------

	// Relationships ----------------------------------------------------------
	private List<Comment>		comments;
	private List<Rendezvous>	rendezvouses;
	private List<Rendezvous>	RSVPd;
	private List<Answer>		answers;
	private List<Reply>			replies;


	@NotNull
	@ElementCollection
	@OneToMany(mappedBy = "user")
	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(final List<Comment> comments) {
		this.comments = comments;
	}

	@NotNull
	@ElementCollection
	@OneToMany(mappedBy = "user")
	public List<Rendezvous> getRendezvouses() {
		return this.rendezvouses;
	}

	public void setRendezvouses(final List<Rendezvous> rendezvouses) {
		this.rendezvouses = rendezvouses;
	}

	@NotNull
	@ManyToMany(mappedBy = "users")
	public List<Rendezvous> getRSVPd() {
		return this.RSVPd;
	}

	public void setRSVPd(final List<Rendezvous> rSVPd) {
		this.RSVPd = rSVPd;
	}

	@NotNull
	@ElementCollection
	@OneToMany(mappedBy = "user")
	public List<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(final List<Answer> answers) {
		this.answers = answers;
	}

	@NotNull
	@ElementCollection
	@OneToMany(mappedBy = "user")
	public List<Reply> getReplies() {
		return this.replies;
	}

	public void setReplies(final List<Reply> replies) {
		this.replies = replies;
	}

}
