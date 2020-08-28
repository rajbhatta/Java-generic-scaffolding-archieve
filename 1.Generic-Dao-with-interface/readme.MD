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

import java.sql.SQLException;
import java.util.List;

public interface SqlDao<T> {
    void save(T t) throws SQLException;
    List<T> get();
    T getById(T t);
    void delete(T t);
}
```

```js
4. 
package raj.java.generic.dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import raj.java.generic.config.PropertyService;

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

    public void save(T t) throws SQLException {
        Statement statement=createStatement();

        //TODO: excecute here

    }

    public List<T> get() {
        return null;
    }

    public T getById(T t) {
        return null;
    }

    public void delete(T t) {

    }

    private MysqlDataSource createMysqlDataSource(){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(userName);
        dataSource.setPassword(password);
        dataSource.setServerName(hostUrl);
        return dataSource;
    }

    private Connection createConnection() throws SQLException {
        Connection conn = createMysqlDataSource().getConnection();
        return conn;
    }

    private Statement createStatement() throws SQLException {
        return createConnection().createStatement();
    }

    private void closeConnection() throws SQLException {
        createConnection().close();
        createStatement().close();;
    }

}
```

```js
5.
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
6. 
package raj.java.generic.service;

import raj.java.generic.dao.SqlDao;
import raj.java.generic.model.Customer;

public interface CustomerService extends SqlDao<Customer> {
     void checkCustomerCredit(Customer customer);
}
```

```js
7.
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



Reference:
https://dzone.com/articles/dao-layer-generics-rescue

https://stackoverflow.com/questions/2839321/connect-java-to-a-mysql-database
