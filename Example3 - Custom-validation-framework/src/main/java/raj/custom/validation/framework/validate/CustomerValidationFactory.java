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
