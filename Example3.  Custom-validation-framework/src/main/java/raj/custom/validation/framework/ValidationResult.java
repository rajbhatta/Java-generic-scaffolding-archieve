package raj.custom.validation.framework;

import java.util.List;

public class ValidationResult 
{
	private List<ConstrainViolation> constrainViolation;

	public ValidationResult(List<ConstrainViolation> constrainViolation) {
		super();
		this.constrainViolation = constrainViolation;
	}

	public List<ConstrainViolation> getConstrainViolation() {
		return constrainViolation;
	}

	public void setConstrainViolation(List<ConstrainViolation> constrainViolation) {
		this.constrainViolation = constrainViolation;
	}
	
	public boolean isValid()
	{
		return constrainViolation.isEmpty();
	}
	
}
