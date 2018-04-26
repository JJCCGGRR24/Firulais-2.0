
package forms;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class BroadcastForm {

	// Constructor
	public BroadcastForm() {
		super();
	}


	private String	subject;
	private String	body;
	private String	priority;


	@NotBlank
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	@NotBlank
	public String getBody() {
		return this.body;
	}

	public void setBody(final String body) {
		this.body = body;
	}

	@NotBlank
	@Pattern(regexp = "^((HIGH)|(NEUTRAL)|(LOW))$", message = "The priority only can be 'LOW', 'NEUTRAL' or 'HIGH'.")
	public String getPriority() {
		return this.priority;
	}

	public void setPriority(final String priority) {
		this.priority = priority;
	}

}
