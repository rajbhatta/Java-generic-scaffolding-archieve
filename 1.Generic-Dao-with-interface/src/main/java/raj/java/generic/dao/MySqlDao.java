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
