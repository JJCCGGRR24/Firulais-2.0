
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Taboo extends DomainEntity {

	//Attributes
	private String	word;


	@NotBlank
	@Size(max = 255)
	public String getWord() {
		return this.word;
	}

	public void setWord(final String word) {
		this.word = word;
	}
}
