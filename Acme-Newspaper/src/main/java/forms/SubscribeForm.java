
package forms;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class SubscribeForm {

	// Constructor
	public SubscribeForm() {
		super();
	}


	private int		newspaperId;
	private String	holderName;
	private String	brandName;
	private String	number;
	private int		expirationYear;
	private int		expirationMonth;
	private int		CVV;


	public int getNewspaperId() {
		return this.newspaperId;
	}
	public void setNewspaperId(final int newspaperId) {
		this.newspaperId = newspaperId;
	}

	@NotBlank
	public String getHolderName() {
		return this.holderName;
	}
	public void setHolderName(final String holderName) {
		this.holderName = holderName;
	}
	@NotBlank
	public String getBrandName() {
		return this.brandName;
	}
	public void setBrandName(final String brandName) {
		this.brandName = brandName;
	}
	@NotBlank
	@CreditCardNumber
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
