
package forms;

import javax.validation.Valid;

import domain.CreditCard;

public class SubscribeForm {

	private CreditCard	creditCard;
	private int			newspaperId;


	// Constructor
	public SubscribeForm() {
		super();
	}

	@Valid
	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public int getNewspaperId() {
		return this.newspaperId;
	}

	public void setNewspaperId(final int newspaperId) {
		this.newspaperId = newspaperId;
	}

}
