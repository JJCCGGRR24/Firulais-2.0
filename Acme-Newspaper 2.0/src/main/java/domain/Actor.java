
package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Actor extends DomainEntity {

	//Attributes
	private String	name;
	private String	surname;
	private String	postalAddress;
	private String	phone;
	private String	email;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Size(max = 255)
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Size(max = 255)
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Size(max = 255)
	public String getPostalAddress() {
		return this.postalAddress;
	}

	public void setPostalAddress(final String postalAddress) {
		this.postalAddress = postalAddress;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Size(max = 255)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	@Email
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Size(max = 255)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}


	// Relationships ----------------------------------------------------------

	private UserAccount		userAccount;
	private List<Message>	messagesSents;
	private List<Message>	messagesReceiveds;
	private List<Folder>	folders;


	@NotNull
	@OneToMany(mappedBy = "sender")
	public List<Message> getMessagesSents() {
		return this.messagesSents;
	}

	public void setMessagesSents(final List<Message> messagesSents) {
		this.messagesSents = messagesSents;
	}

	@NotNull
	@OneToMany(mappedBy = "recipient")
	public List<Message> getMessagesReceiveds() {
		return this.messagesReceiveds;
	}

	public void setMessagesReceiveds(final List<Message> messagesReceiveds) {
		this.messagesReceiveds = messagesReceiveds;
	}

	@NotNull
	@OneToMany(mappedBy = "actor")
	public List<Folder> getFolders() {
		return this.folders;
	}

	public void setFolders(final List<Folder> folders) {
		this.folders = folders;
	}

	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

}
