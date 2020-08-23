package raj.java.generic.dao;

import java.sql.SQLException;
import java.util.List;

public interface SqlDao<T> {
    void save(T t) throws SQLException;
    List<T> get();
    T getById(T t);
    void delete(T t);
}

