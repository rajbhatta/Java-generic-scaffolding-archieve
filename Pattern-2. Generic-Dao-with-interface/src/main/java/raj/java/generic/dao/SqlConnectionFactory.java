package raj.java.generic.dao;

import raj.java.generic.exception.MySqlException;

public interface SqlConnectionFactory<T> {
    SqlDao<T> getStatementForDatabase() throws MySqlException;
}
