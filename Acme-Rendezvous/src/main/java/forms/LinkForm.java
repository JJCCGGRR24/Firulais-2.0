
package forms;

import javax.validation.constraints.NotNull;

import domain.Rendezvous;

public class LinkForm {

	private int			id;
	private Rendezvous	r;


	@NotNull
	public Rendezvous getR() {
		return this.r;
	}
	public void setR(final Rendezvous r) {
		this.r = r;
	}

	public int getId() {
		return this.id;
	}
	public void setId(final int id) {
		this.id = id;
	}

}
