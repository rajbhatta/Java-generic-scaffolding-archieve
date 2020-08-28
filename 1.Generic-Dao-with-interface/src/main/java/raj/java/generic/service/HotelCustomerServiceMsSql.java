package raj.java.generic.service;

import raj.java.generic.config.PropertyService;
import raj.java.generic.dao.MsSqlDao;
import raj.java.generic.dao.SqlDao;
import raj.java.generic.exception.MySqlException;
import raj.java.generic.model.Customer;

import java.util.List;

public class HotelCustomerServiceMsSql implements CustomerService {

    PropertyService propertyService;
    SqlDao sqlDao;

    public HotelCustomerServiceMsSql(PropertyService propertyService) {
        this.propertyService = propertyService;
        sqlDao=new MsSqlDao(propertyService.getMysqlHostUrl(),propertyService.getMysqlUserName(),propertyService.getMySqlPassword());
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
