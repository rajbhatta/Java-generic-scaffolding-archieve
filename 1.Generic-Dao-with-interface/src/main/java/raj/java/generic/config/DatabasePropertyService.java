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
