## How code is designed here ## 
```js
public interface SqlDao<T> {
    void save(T t) throws MySqlException;
    List<T> get() throws MySqlException;
    T getById(T t) throws MySqlException;
    void delete(T t) throws MySqlException;
}
```

```js
package raj.java.generic.dao;

import raj.java.generic.exception.MySqlException;

public interface SqlConnectionFactory<T> {
    SqlDao<T> getStatementForDatabase() throws MySqlException;
}
```

```js
package raj.java.generic.dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import raj.java.generic.exception.MySqlException;
import raj.java.generic.service.HotelCustomerServiceMySql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlDao<T> implements SqlConnectionFactory<T> {

    private String hostUrl;
    private String userName;
    private String password;

    private MysqlDataSource mysqlDataSource=null;
    private Connection connection=null;
    private Statement statement=null;

    public MySqlDao(String hostUrl, String userName, String password) {
        this.hostUrl = hostUrl;
        this.userName = userName;
        this.password = password;
    }


    private MysqlDataSource createMysqlDataSource(){
        if(mysqlDataSource==null){
            mysqlDataSource=new MysqlDataSource();
            mysqlDataSource.setUser(userName);
            mysqlDataSource.setPassword(password);
            mysqlDataSource.setServerName(hostUrl);
        }
        return mysqlDataSource;
    }

    private Connection createConnection() throws MySqlException {

        if(connection==null) {
            try {
                return createMysqlDataSource().getConnection();
            } catch (SQLException e) {
                throw new MySqlException("Error creating a connection", e.getCause());
            }
        }
        return connection;
    }

    private Statement createStatement() throws MySqlException {
        if(statement==null){
                try {
                    return createConnection().createStatement();
                } catch (SQLException e) {
                    throw new MySqlException("Error creating a statement");
                }
        }
        return statement;
    }

    private void closeConnection() throws MySqlException {

        try {
            createConnection().close();
            createStatement().close();
        } catch (SQLException e) {
            throw new MySqlException("Error closing a connection");
        }
        ;
    }

    //HotelCustomer implements SqlDao. So, we can return instance of HotelCustomerDao
    public SqlDao<T> getStatementForDatabase() throws MySqlException {
        createConnection();
        createStatement();
        return new HotelCustomerServiceMySql<T>(statement);
    }
}
```

```js
package raj.java.generic.service;

import raj.java.generic.config.PropertyService;
import raj.java.generic.dao.MySqlDao;
import raj.java.generic.dao.SqlDao;
import raj.java.generic.exception.MySqlException;
import raj.java.generic.model.Customer;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class HotelCustomerServiceMySql<T> implements SqlDao<T>{

    Statement statement;

    public HotelCustomerServiceMySql(Statement statement) {
        this.statement = statement;
    }

    public void save(T t) throws MySqlException {
        try {
            statement.execute("Execute insert statement here");
        } catch (SQLException e) {
           throw new MySqlException("Error inserting record inside database");
        }
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
```

### How I have designed this project ? ###
<img src="How-this-project-is-designed.jpg">

