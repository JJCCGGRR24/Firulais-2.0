
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Embeddable
@Access(AccessType.PROPERTY)
public class CreditCard {

	// Constructors -------------------------------------------------------------
	public CreditCard() {
		super();
	}


	// Attributes -------------------------------------------------------------
	private String	holderName;
	private String	brandName;
	private String	number;
	private int		expirationYear;
	private int		expirationMonth;
	private int		CVV;


	@NotBlank
	@Size(max = 255)
	public String getHolderName() {
		return this.holderName;
	}
	public void setHolderName(final String holderName) {
		this.holderName = holderName;
	}

	@NotBlank
	@Pattern(regexp = "^(VISA)||(MASTERCARD)||(DISCOVER)||(DINNERS)||(AMEX)$")
	@Size(max = 255)
	public String getBrandName() {
		return this.brandName;
	}
	public void setBrandName(final String brandName) {
		this.brandName = brandName;
	}
	@NotBlank
	@CreditCardNumber
	@Size(max = 255)
	public String getNumber() {
		return this.number;
	}
	public void setNumber(final String number) {
		this.number = number;
	}
	@NotNull
	@Range(min = 2018, max = 3000)
	public int getExpirationYear() {
		return this.expirationYear;
	}
	public void setExpirationYear(final int expirationYear) {
		this.expirationYear = expirationYear;
	}
	@NotNull
	@Range(min = 1, max = 12)
	public int getExpirationMonth() {
		return this.expirationMonth;
	}
	public void setExpirationMonth(final int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}
	@NotNull
	@Range(min = 100, max = 999)
	public int getCVV() {
		return this.CVV;
	}
	public void setCVV(final int CVV) {
		this.CVV = CVV;
	}
}
