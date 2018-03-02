
package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(indexes = {
	@Index(name = "my_index_moment2", columnList = "moment"), @Index(name = "my_index_deleted2", columnList = "deleted"), @Index(name = "my_index_adultsOnly2", columnList = "adultsOnly"), @Index(name = "my_index_finalMode2", columnList = "finalMode")
})
public class Rendezvous extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String		name;
	private String		description;
	private Date		moment;
	private String		picture;
	private Coordinates	coordinates;
	private boolean		finalMode;
	private boolean		deleted;
	private boolean		adultsOnly;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	@Column(columnDefinition = "BLOB")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	@URL
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

	public Coordinates getCoordinates() {
		return this.coordinates;
	}

	public void setCoordinates(final Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	@NotNull
	public boolean getFinalMode() {
		return this.finalMode;
	}

	public void setFinalMode(final boolean finalMode) {
		this.finalMode = finalMode;
	}

	@NotNull
	public boolean getDeleted() {
		return this.deleted;
	}

	public void setDeleted(final boolean deleted) {
		this.deleted = deleted;
	}

	@NotNull
	public boolean getAdultsOnly() {
		return this.adultsOnly;
	}

	public void setAdultsOnly(final boolean adultsOnly) {
		this.adultsOnly = adultsOnly;
	}


	// Relationships ----------------------------------------------------------
	private User				user;
	private List<Rendezvous>	linkedTo;
	private List<Rendezvous>	linkedIn;
	private List<User>			users;
	private List<Request>		requests;
	private List<Comment>		comments;
	private List<Announcement>	announcements;
	private List<Question>		questions;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	@NotNull
	@ManyToMany()
	public List<Rendezvous> getLinkedTo() {
		return this.linkedTo;
	}

	public void setLinkedTo(final List<Rendezvous> linkedTo) {
		this.linkedTo = linkedTo;
	}

	@NotNull
	@ManyToMany(mappedBy = "linkedTo", cascade = CascadeType.REMOVE)
	public List<Rendezvous> getLinkedIn() {
		return this.linkedIn;
	}

	public void setLinkedIn(final List<Rendezvous> linkedIn) {
		this.linkedIn = linkedIn;
	}

	@NotNull
	@ManyToMany
	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(final List<User> users) {
		this.users = users;
	}

	@NotNull
	@ElementCollection
	@OneToMany(mappedBy = "rendezvous", cascade = CascadeType.REMOVE)
	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(final List<Comment> comments) {
		this.comments = comments;
	}

	@NotNull
	@ElementCollection
	@OneToMany(mappedBy = "rendezvous")
	public List<Request> getRequests() {
		return this.requests;
	}

	public void setRequests(final List<Request> requests) {
		this.requests = requests;
	}

	@NotNull
	@ElementCollection
	@OneToMany(mappedBy = "rendezvous", cascade = CascadeType.REMOVE)
	public List<Announcement> getAnnouncements() {
		return this.announcements;
	}

	public void setAnnouncements(final List<Announcement> announcements) {
		this.announcements = announcements;
	}

	@NotNull
	@ElementCollection
	@OneToMany(mappedBy = "rendezvous", cascade = CascadeType.REMOVE)
	public List<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(final List<Question> questions) {
		this.questions = questions;
	}

}
