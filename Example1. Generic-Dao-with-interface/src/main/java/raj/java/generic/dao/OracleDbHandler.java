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
