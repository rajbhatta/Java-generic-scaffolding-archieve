package raj.java.generic;

import raj.java.generic.config.DatabasePropertyService;
import raj.java.generic.config.PropertyService;
import raj.java.generic.exception.MySqlException;
import raj.java.generic.model.Customer;
import raj.java.generic.service.CustomerService;
import raj.java.generic.service.HotelCustomerService;

import java.sql.SQLException;
import java.util.UUID;

public class NexusController {

    public static void main(String[] args) throws MySqlException {

        //Custom Dependency Injection
        PropertyService propertyService=new DatabasePropertyService();

        //Property service is injected here, we can use Dagger2 or other dependency injection framework here.
        CustomerService customerService=new HotelCustomerService(propertyService);

        Customer customer=new Customer();
        customer.setFirstName(UUID.randomUUID().toString());
        customer.setLastName(UUID.randomUUID().toString());

        customerService.save(customer);


    }
}
