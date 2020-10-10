package raj.custom.validation.framework;

public class ConstrainViolation 
{
	private String constrainField;
	private String constrainNotice;
	
	public ConstrainViolation(String constrainField, String constrainNotice) {
		super();
		this.constrainField = constrainField;
		this.constrainNotice = constrainNotice;
	}

	public String getConstrainField() {
		return constrainField;
	}

	public void setConstrainField(String constrainField) {
		this.constrainField = constrainField;
	}

	public String getConstrainNotice() {
		return constrainNotice;
	}

	public void setConstrainNotice(String constrainNotice) {
		this.constrainNotice = constrainNotice;
	}
	
	
}
