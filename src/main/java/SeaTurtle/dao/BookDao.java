package SeaTurtle.dao;

import java.sql.*;
import java.util.ArrayList;

public interface BookDao<T, K> {

    void createTable() throws SQLException;
    void dropTable() throws SQLException;
    void create(T object) throws SQLException;
    T read(T object) throws SQLException;
    void delete(T object) throws SQLException;
    void update(T object) throws SQLException;
    ArrayList<T> list() throws SQLException;
    ArrayList<T> findAndList(String L) throws SQLException;
}
