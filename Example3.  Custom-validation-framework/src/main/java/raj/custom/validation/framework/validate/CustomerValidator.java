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
