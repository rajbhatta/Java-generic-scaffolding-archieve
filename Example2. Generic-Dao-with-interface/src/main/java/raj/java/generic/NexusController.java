package raj.java.generic;

import raj.java.generic.config.DatabasePropertyService;
import raj.java.generic.config.PropertyService;
import raj.java.generic.dao.MySqlDao;
import raj.java.generic.dao.SqlConnectionFactory;
import raj.java.generic.dao.SqlDao;
import raj.java.generic.exception.MySqlException;
import raj.java.generic.model.Customer;
import raj.java.generic.service.HotelCustomerServiceMySql;

import java.sql.Statement;
import java.util.UUID;

public class NexusController {

    public static void main(String[] args) throws MySqlException {

        /**
         * Custom dependency injection is performed here.
         *
         */
        PropertyService propertyService=new DatabasePropertyService();


        /**
         *  Property service is injected here, we can use Dagger2 or other dependency injection framework here.
         *
         *  Since we implemented SqlConnectionFactory inside MySqlDao. Thus, we can do like this.
         *
         */
        SqlConnectionFactory sqlConnectionFactory=new MySqlDao(propertyService.getMysqlHostUrl(),propertyService.getMysqlHostUrl(),propertyService.getMySqlPassword());

        /**
         *
         * SqlConnectionFactory.getStatementForDatabase()
         * is creating a new instance and returning that SqlDao<Customer> sqlDao so.
         * SqlDao has all the capabilities of executing all the method located inside HotelCustomerServiceMySql.
         *
         * This statement is creating instance of  ===== HotelCustomerServiceMySql hotelCustomerServiceMySql=new HotelCustomerServiceMySql<T>(statement); ======
         *
         * So it is capable of calling the method inside HotelCustomerServiceMySql inside
         *
         */
        SqlDao<Customer> sqlDao= sqlConnectionFactory.getStatementForDatabase();


        Customer customer=new Customer();
        customer.setFirstName(UUID.randomUUID().toString());
        customer.setLastName(UUID.randomUUID().toString());

        /**
         * This will call the method of HotelCustomerServiceMySql Save.
         */
        sqlDao.save(customer);




    }
}
