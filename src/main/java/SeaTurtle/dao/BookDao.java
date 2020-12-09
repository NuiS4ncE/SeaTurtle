package SeaTurtle.dao;

import java.sql.*;
import java.util.ArrayList;
import SeaTurtle.model.Book;

public interface BookDao<T, K> {

    void createTable() throws SQLException;
    void dropTable() throws SQLException;
    String create(T object) throws SQLException;
    T read(T object) throws SQLException;
    void delete(String L, String M) throws SQLException;
    void updateBookmark(T object) throws SQLException;
    ArrayList<T> list() throws SQLException;
    ArrayList<T> findAndList(String L) throws SQLException;
    Book findBookById(Integer I) throws SQLException;
}
