package raj.custom.validation.framework;

public class ValidationWrapper<T> 
{
	Validator<T> validator;
	T objectToValidate;
	ValidationFactory validationFactory;
	
	public Validator<T> getValidator() {
		return validator;
	}
	public T getObjectToValidate() {
		return objectToValidate;
	}
	
	public ValidationWrapper(T objectToValidate, ValidationFactory validationFactory) {
		this.objectToValidate = objectToValidate;
		this.validationFactory = validationFactory;
		this.validator=validationFactory.getValidator();
	}
	
	public String validate()
	{
		ValidationResult validationResult=validator.validate(objectToValidate);
		if(!validationResult.isValid())
		{
			StringBuffer messageError=new StringBuffer();
			for(ConstrainViolation violation:validationResult.getConstrainViolation())
			{
				messageError.append(violation.getConstrainField());
				messageError.append(" ");
				messageError.append(violation.getConstrainNotice());
				messageError.append("\n");
			}
			return messageError.toString();
		}
		return "";
	}
}
