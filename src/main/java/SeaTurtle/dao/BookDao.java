package SeaTurtle.dao;

import java.sql.*;
import java.util.*;

public interface BookDao<T, K> {

    void create(T object, K key) throws SQLException;
    T read(T object, K key) throws SQLException;
    void delete(T object, K key) throws SQLException;
    List<T> list(K key) throws SQLException;
}
