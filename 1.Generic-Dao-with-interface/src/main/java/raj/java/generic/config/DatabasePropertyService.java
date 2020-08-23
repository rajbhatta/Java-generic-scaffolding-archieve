package raj.java.generic.config;

public class DatabasePropertyService implements PropertyService {

    public String getMySqlDbName() {
        return "DB_TEST";
    }

    public String getMysqlHostUrl() {
        return "dbc:mysql://localhost:3306/";
    }
}
