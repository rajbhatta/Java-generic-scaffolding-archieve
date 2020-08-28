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
        return new HotelCustomerServiceMySql<T>(statement);
    }
}
