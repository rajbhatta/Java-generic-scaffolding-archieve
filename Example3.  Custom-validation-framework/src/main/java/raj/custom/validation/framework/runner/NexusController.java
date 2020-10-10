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
