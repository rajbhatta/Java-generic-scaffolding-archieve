## How the code is designed inside this repo ##

```js
1.

package raj.java.generic.config;

public interface PropertyService {
    String getMysqlUserName();
    String getMySqlPassword();
    String getMysqlHostUrl();

}
```

```js
2.
package raj.java.generic.config;

public class DatabasePropertyService implements PropertyService {

    public String getMysqlUserName() {
        return "testUserName";
    }

    public String getMySqlPassword() {
        return "testPassword";
    }

    public String getMysqlHostUrl() {
        return "dbc:mysql://localhost:3306/";
    }


}
```

```js
3. 

package raj.java.generic.dao;

import raj.java.generic.exception.MySqlException;

import java.sql.SQLException;
import java.util.List;

public interface SqlDao<T> {
    void save(T t) throws MySqlException;
    List<T> get() throws MySqlException;
    T getById(T t) throws MySqlException;
    void delete(T t) throws MySqlException;
}

```

```js
4. 
package raj.java.generic.dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import raj.java.generic.config.PropertyService;
import raj.java.generic.exception.MySqlException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MySqlDao<T> implements SqlDao<T> {

    private String hostUrl;
    private String userName;
    private String password;

    public MySqlDao(String hostUrl, String userName, String password) {
        this.hostUrl = hostUrl;
        this.userName = userName;
        this.password = password;
    }

    public void save(T t) throws MySqlException {
        Statement statement=createStatement();

        //TODO: excecute here

    }

    public List<T> get() throws MySqlException {
        return null;
    }

    public T getById(T t) throws MySqlException {
        return null;
    }

    public void delete(T t) throws MySqlException {

    }

    private MysqlDataSource createMysqlDataSource(){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(userName);
        dataSource.setPassword(password);
        dataSource.setServerName(hostUrl);
        return dataSource;
    }

    private Connection createConnection() throws MySqlException {

        try {
            return createMysqlDataSource().getConnection();
        } catch (SQLException e) {
           throw new MySqlException("Error creating a connection",e.getCause());
        }
    }

    private Statement createStatement() throws MySqlException {
        try {
            return createConnection().createStatement();
        } catch (SQLException e) {
            throw new MySqlException("Error creating a statement");
        }
    }

    private void closeConnection() throws MySqlException {

        try {
            createConnection().close();
            createStatement().close();
        } catch (SQLException e) {
            throw new MySqlException("Error closing a connection");
        }
    }
}
```

```js
5.
package raj.java.generic.dao;

import raj.java.generic.exception.MySqlException;

import java.util.List;

public class OracleSqlDao<T> implements SqlDao<T> {

    private String hostUrl;
    private String userName;
    private String password;

    public OracleSqlDao(String hostUrl, String userName, String password) {
        this.hostUrl = hostUrl;
        this.userName = userName;
        this.password = password;
    }


    public void save(T t) throws MySqlException {

    }

    public List<T> get() throws MySqlException {
        return null;
    }

    public T getById(T t) throws MySqlException {
        return null;
    }

    public void delete(T t) throws MySqlException {

    }
}
```

```js
6.
package raj.java.generic.dao;

import raj.java.generic.exception.MySqlException;

import java.util.List;

public class MsSqlDao<T> implements SqlDao<T> {

    private String hostUrl;
    private String userName;
    private String password;

    public MsSqlDao(String hostUrl, String userName, String password) {
        this.hostUrl = hostUrl;
        this.userName = userName;
        this.password = password;
    }

    public void save(T t) throws MySqlException {

    }

    public List<T> get() throws MySqlException {
        return null;
    }

    public T getById(T t) throws MySqlException {
        return null;
    }

    public void delete(T t) throws MySqlException {

    }
}
```



```js
7.
package raj.java.generic.model;

public class Customer {
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
```

```js
8. 
package raj.java.generic.service;

import raj.java.generic.dao.SqlDao;
import raj.java.generic.model.Customer;

public interface CustomerService extends SqlDao<Customer> {
     void checkCustomerCredit(Customer customer);
}
```

```js
8.
package raj.java.generic.service;

import raj.java.generic.config.PropertyService;
import raj.java.generic.dao.MySqlDao;
import raj.java.generic.dao.SqlDao;
import raj.java.generic.model.Customer;

import java.sql.SQLException;
import java.util.List;

public class HotelCustomerService implements CustomerService{

    PropertyService propertyService;
    SqlDao sqlDao;

    public HotelCustomerService(PropertyService propertyService) {
        this.propertyService = propertyService;
        sqlDao=new MySqlDao(propertyService.getMysqlHostUrl(),propertyService.getMysqlUserName(),propertyService.getMySqlPassword());
    }


    public void checkCustomerCredit(Customer customer) {
    }

    public void save(Customer customer) throws SQLException {
        sqlDao.save(customer);
    }

    public List<Customer> get() {
        return null;
    }

    public Customer getById(Customer customer) {
        return null;
    }

    public void delete(Customer customer) {

    }
}
```

```js
9.

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
```

```js
10.
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
```

```js
11.

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
```




Reference:
https://dzone.com/articles/dao-layer-generics-rescue

https://stackoverflow.com/questions/2839321/connect-java-to-a-mysql-database
