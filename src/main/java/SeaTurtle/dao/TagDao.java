package SeaTurtle.dao;

import java.sql.*;
import java.util.ArrayList;

public interface TagDao<T, K> {

    void createTable() throws SQLException;
    void dropTable() throws SQLException;
    void create(T object) throws SQLException;
    T read(T object) throws SQLException;
    void delete(T object) throws SQLException;
    ArrayList<T> list() throws SQLException;
    ArrayList<T> findAndList(String L) throws SQLException;
    ArrayList<Integer> findBookIdsByTag(String searchWord) throws SQLException;
    ArrayList<T> findTagsByBookId(Integer I) throws SQLException;
    void deleteById(Integer I) throws SQLException;
    ArrayList<T> findTagsByIdAndType(Integer I, String S) throws SQLException;
}
