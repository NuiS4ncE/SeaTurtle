package SeaTurtle.dao;

import java.sql.*;
import java.util.*;

public interface BookDao<T, K> {

    void createTable() throws SQLException;
    void create(T object) throws SQLException;
    T read(T object) throws SQLException;
    void delete(T object) throws SQLException;
    ArrayList<T> list() throws SQLException;
}
