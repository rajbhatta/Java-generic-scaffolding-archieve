package raj.java.generic;

import raj.java.generic.config.DatabasePropertyService;
import raj.java.generic.config.PropertyService;
import raj.java.generic.dao.MySqlDbHandler;
import raj.java.generic.dao.OracleDbHandler;
import raj.java.generic.dao.SqlDao;
import raj.java.generic.exception.MySqlException;
import raj.java.generic.model.Customer;
import raj.java.generic.service.CustomerService;
import raj.java.generic.service.HotelCustomerServiceMsSql;
import raj.java.generic.service.HotelCustomerServiceOracle;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class NexusController {

  public static void main(String[] args) throws MySqlException, SQLException {

    /**
     *
     * Custom Dependency Injection
     * Property service is injected here, we can use Dagger2 or other dependency injection
     * framework here.
     */
    Customer customer = new Customer();
    customer.setFirstName(UUID.randomUUID().toString());
    customer.setLastName(UUID.randomUUID().toString());

    PropertyService propertyService = new DatabasePropertyService();

    /**
     * MYSQL
     */
    MySqlDbHandler mySqlDbHandler=new MySqlDbHandler(propertyService.getMysqlHostUrl(),propertyService.getMysqlUserName(),propertyService.getMySqlPassword());
    Statement mySqlDbHandlerStatement=mySqlDbHandler.createStatement();
    SqlDao<Customer> hotelCustomerMySql=new HotelCustomerServiceMsSql(mySqlDbHandlerStatement);
    hotelCustomerMySql.save(customer);

    /**
     * Oracle
     */
    OracleDbHandler oracleDbHandler=new OracleDbHandler(propertyService.getMysqlHostUrl(),propertyService.getMysqlUserName(),propertyService.getMySqlPassword());
    Statement oracleDbHandlerStatement=mySqlDbHandler.createStatement();
    SqlDao<Customer> hotelCustomerServiceOracle=new HotelCustomerServiceOracle(oracleDbHandlerStatement);
    hotelCustomerServiceOracle.save(customer);


  }
}
