
package forms;

import java.util.List;

import javax.validation.Valid;

import domain.Answer;

public class AnswersForm {

	private int				rid;
	private List<Answer>	las;


	@Valid
	public List<Answer> getLas() {
		return this.las;
	}

	public void setLas(final List<Answer> las) {
		this.las = las;
	}

	public int getRid() {
		return this.rid;
	}

	public void setRid(final int rid) {
		this.rid = rid;
	}
}
