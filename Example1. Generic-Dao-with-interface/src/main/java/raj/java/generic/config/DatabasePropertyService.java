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
