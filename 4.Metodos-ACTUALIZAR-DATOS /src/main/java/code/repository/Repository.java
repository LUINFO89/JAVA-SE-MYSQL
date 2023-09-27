package code.repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository <T>{
    List<T> findAll() throws SQLException;
    T getById(Integer id);
    void save(T t) throws SQLException;
    void delete(Integer id) throws SQLException;
}
