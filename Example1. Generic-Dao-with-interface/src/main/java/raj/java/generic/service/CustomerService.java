package raj.java.generic.service;

import raj.java.generic.dao.SqlDao;
import raj.java.generic.model.Customer;

import java.sql.SQLException;

public interface CustomerService extends SqlDao<Customer> {
  void checkCustomerCredit(Customer customer) throws SQLException;
}
