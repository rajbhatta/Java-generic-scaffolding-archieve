package raj.java.generic.service;

import raj.java.generic.dao.SqlDao;
import raj.java.generic.model.Customer;

public interface CustomerService extends SqlDao<Customer> {
     void checkCustomerCredit(Customer customer);
}
