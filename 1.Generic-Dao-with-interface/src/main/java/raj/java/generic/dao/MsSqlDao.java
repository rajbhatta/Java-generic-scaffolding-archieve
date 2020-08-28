package raj.java.generic.dao;

import raj.java.generic.exception.MySqlException;

import java.util.List;

public class MsSqlDao<T> implements SqlDao<T> {

    private String hostUrl;
    private String userName;
    private String password;

    public MsSqlDao(String hostUrl, String userName, String password) {
        this.hostUrl = hostUrl;
        this.userName = userName;
        this.password = password;
    }

    public void save(T t) throws MySqlException {

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
