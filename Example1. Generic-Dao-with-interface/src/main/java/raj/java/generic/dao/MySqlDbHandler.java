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
