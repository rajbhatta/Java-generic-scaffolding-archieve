## How to create a custom validation framework ?? ##
```java
package raj.custom.validation.framework;

public interface Resolver<T,R> {
	R resolve(T invalidInput);
}

```

```java
package raj.custom.validation.framework;

public interface ValidationFactory<T,R> {
	
	Validator<T> getValidator();
	Resolver<T,R> getResolver(ValidationResult validationResult);
}

```

```java
package raj.custom.validation.framework;

public interface Validator<T> {
	
	ValidationResult validate(T t);
}

```

```java
package raj.custom.validation.framework.validate;

import raj.custom.validation.framework.Resolver;
import raj.custom.validation.framework.ValidationFactory;
import raj.custom.validation.framework.ValidationResult;
import raj.custom.validation.framework.Validator;
import raj.custom.validation.framework.models.Customer;

public class CustomerValidationFactory implements ValidationFactory<Customer,String> {

	public Validator<Customer> getValidator() {
		return new CustomerValidator();
	}

	public Resolver<Customer, String> getResolver(ValidationResult validationResult) {
		// TODO Auto-generated method stub
		return null;
	}

}

```

```java
package raj.custom.validation.framework.validate;

import java.util.ArrayList;

import raj.custom.validation.framework.ConstrainViolation;
import raj.custom.validation.framework.ValidationResult;
import raj.custom.validation.framework.Validator;
import raj.custom.validation.framework.models.Customer;

public class CustomerValidator implements Validator<Customer> {

	public ValidationResult validate(Customer t) {
		
		if(t.getPhoneNumber()==null) {
			ArrayList<ConstrainViolation> violation=new ArrayList();
			violation.add(new ConstrainViolation("","Phone number can not be null"));
			return new ValidationResult(violation);
		}
		
		return null;
	}

}

```

```java
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

```

```java
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

```

```java
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

```

```java
package raj.custom.validation.framework.models;

public class Customer 
{
	private String name;
	private String phoneNumber;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}

```

```java
package raj.custom.validation.framework.runner;

import raj.custom.validation.framework.ValidationWrapper;
import raj.custom.validation.framework.models.Customer;
import raj.custom.validation.framework.validate.CustomerValidationFactory;

public class NexusController {

	public static void main(String[] args) {
		
		Customer customer=new Customer();
		customer.setName(null);
		customer.setPhoneNumber(null);
		
		ValidationWrapper validationWrapper=new ValidationWrapper(customer,new CustomerValidationFactory());
		validationWrapper.validate();
		
	}

}
```