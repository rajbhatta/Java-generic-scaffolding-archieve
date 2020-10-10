package raj.java.generic.dao;

import raj.java.generic.exception.MySqlException;

import java.sql.SQLException;
import java.util.List;

public interface SqlDao<T> {
  void save(T t) throws MySqlException, SQLException;

  List<T> get() throws MySqlException;

  T getById(T t) throws MySqlException;

  void delete(T t) throws MySqlException;
}
