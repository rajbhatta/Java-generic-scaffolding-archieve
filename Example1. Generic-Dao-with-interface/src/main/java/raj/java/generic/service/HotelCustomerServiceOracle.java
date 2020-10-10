package raj.java.generic.service;

import raj.java.generic.config.PropertyService;
import raj.java.generic.dao.OracleDbHandler;
import raj.java.generic.dao.SqlDao;
import raj.java.generic.exception.MySqlException;
import raj.java.generic.model.Customer;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class HotelCustomerServiceOracle implements SqlDao<Customer>, CustomerService {

  Statement statement;

  public HotelCustomerServiceOracle( Statement statement) {
    this.statement = statement;
  }

  public void checkCustomerCredit(Customer customer) throws SQLException {
    String sql = "construct sql here";
    statement.execute(sql);
  }

  public void save(Customer customer) throws SQLException {
    String sql = "construct sql here";
    statement.execute(sql);
  }

  public List<Customer> get() throws MySqlException {
    return null;
  }

  public Customer getById(Customer customer) throws MySqlException {
    return null;
  }

  public void delete(Customer customer) throws MySqlException {}
}
