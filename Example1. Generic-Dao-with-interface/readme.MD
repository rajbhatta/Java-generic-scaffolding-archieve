## How the code is designed inside this repo ##

1. 
```js
package raj.java.generic.config;

public interface PropertyService {
  String getMysqlUserName();

  String getMySqlPassword();

  String getMysqlHostUrl();

  String getOracleUrl();

  String getOracleUser();

  String getOraclePassword();
}

```

```js
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

  public String getOracleUrl() {
    return "jdbc:oracle:thin:@localhost:1521:db-raj";
  }

  public String getOracleUser() {
    return "raj-admin";
  }

  public String getOraclePassword() {
    return "raj-admin";
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
package raj.java.generic.dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import raj.java.generic.config.PropertyService;
import raj.java.generic.exception.MySqlException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MySqlDbHandler {

  private String hostUrl;
  private String userName;
  private String password;

  public MySqlDbHandler(String hostUrl, String userName, String password) {
    this.hostUrl = hostUrl;
    this.userName = userName;
    this.password = password;
  }

  private MysqlDataSource createMysqlDataSource() {
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
      throw new MySqlException("Error creating a connection", e.getCause());
    }
  }

  public Statement createStatement() throws MySqlException {
    try {
      return createConnection().createStatement();
    } catch (SQLException e) {
      throw new MySqlException("Error creating a statement");
    }
  }

  public void closeConnection() throws MySqlException {

    try {
      createConnection().close();
      createStatement().close();
    } catch (SQLException e) {
      throw new MySqlException("Error closing a connection");
    }
    ;
  }
}
```

```js
package raj.java.generic.dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import oracle.jdbc.datasource.OracleDataSource;
import raj.java.generic.exception.MySqlException;
import raj.java.generic.exception.OracleException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class OracleDbHandler {

  private String hostUrl;
  private String userName;
  private String password;

  public OracleDbHandler(String hostUrl, String userName, String password) {
    this.hostUrl = hostUrl;
    this.userName = userName;
    this.password = password;
  }

  private Connection createConnection() throws ClassNotFoundException, OracleException {
    Class.forName("oracle.jdbc.OracleDriver");
    try {
      return DriverManager.getConnection(hostUrl, userName, password);
    } catch (SQLException e) {
      throw new OracleException("Error creating a connection", e.getCause());
    }
  }

  public Statement createStatement() throws OracleException, ClassNotFoundException {
    try {
      return createConnection().createStatement();
    } catch (SQLException e) {
      throw new OracleException("Error creating a statement", e);
    }
  }

  public void closeConnection() throws OracleException, ClassNotFoundException {

    try {
      createConnection().close();
      createStatement().close();
    } catch (SQLException e) {
      throw new OracleException("Error closing a connection", e);
    }
    ;
  }
}

```

```js
package raj.java.generic.service;

import raj.java.generic.dao.SqlDao;
import raj.java.generic.model.Customer;

import java.sql.SQLException;

public interface CustomerService extends SqlDao<Customer> {
  void checkCustomerCredit(Customer customer) throws SQLException;
}
```



```js
package raj.java.generic.service;

import raj.java.generic.config.PropertyService;
import raj.java.generic.dao.SqlDao;
import raj.java.generic.exception.MySqlException;
import raj.java.generic.model.Customer;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class HotelCustomerServiceMsSql implements SqlDao<Customer>, CustomerService {


  Statement statement;

  public HotelCustomerServiceMsSql( Statement statement) {
    this.statement = statement;
  }

  public void checkCustomerCredit(Customer customer) throws SQLException {
    String sql = "construct sql here";
    statement.execute(sql);
  }

  public void save(Customer customer) throws MySqlException, SQLException {
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

```

```js
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

```

```js
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
package raj.java.generic.exception;

public class MySqlException extends Exception {
  public MySqlException() {}

  public MySqlException(String message) {
    super(message);
  }

  public MySqlException(String message, Throwable cause) {
    super(message, cause);
  }

  public MySqlException(Throwable cause) {
    super(cause);
  }

  public MySqlException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}

```

```js
1package raj.java.generic.exception;
 
 public class OracleException extends Exception {
   public OracleException() {}
 
   public OracleException(String message) {
     super(message);
   }
 
   public OracleException(String message, Throwable cause) {
     super(message, cause);
   }
 
   public OracleException(Throwable cause) {
     super(cause);
   }
 
   public OracleException(
       String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
     super(message, cause, enableSuppression, writableStackTrace);
   }
 }

```

```js
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

```

