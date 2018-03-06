
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Configuration extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String	nameBusiness;
	private String	banner;
	private String	messageES;
	private String	messageEN;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getNameBusiness() {
		return this.nameBusiness;
	}

	public void setNameBusiness(final String nameBusiness) {
		this.nameBusiness = nameBusiness;
	}

	@NotBlank
	@URL
	public String getBanner() {
		return this.banner;
	}

	public void setBanner(final String banner) {
		this.banner = banner;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getMessageES() {
		return this.messageES;
	}

	public void setMessageES(final String messageES) {
		this.messageES = messageES;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getMessageEN() {
		return this.messageEN;
	}

	public void setMessageEN(final String messageEN) {
		this.messageEN = messageEN;
	}

}
