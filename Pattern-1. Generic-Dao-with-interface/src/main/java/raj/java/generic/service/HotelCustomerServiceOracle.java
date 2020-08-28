package raj.java.generic.service;

import raj.java.generic.config.PropertyService;
import raj.java.generic.dao.OracleSqlDao;
import raj.java.generic.dao.SqlDao;
import raj.java.generic.exception.MySqlException;
import raj.java.generic.model.Customer;

import java.util.List;

public class HotelCustomerServiceOracle implements CustomerService {

    PropertyService propertyService;
    SqlDao sqlDao;

    public HotelCustomerServiceOracle(PropertyService propertyService) {
        this.propertyService = propertyService;
        sqlDao=new OracleSqlDao(propertyService.getMysqlHostUrl(),propertyService.getMysqlUserName(),propertyService.getMySqlPassword());
    }

    public void checkCustomerCredit(Customer customer) {

    }

    public void save(Customer customer) throws MySqlException {

    }

    public List<Customer> get() throws MySqlException {
        return null;
    }

    public Customer getById(Customer customer) throws MySqlException {
        return null;
    }

    public void delete(Customer customer) throws MySqlException {

    }
}
