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
