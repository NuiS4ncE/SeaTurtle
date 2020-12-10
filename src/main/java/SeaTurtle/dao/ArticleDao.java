package SeaTurtle.dao;

import SeaTurtle.model.Article;
import java.sql.*;
import java.util.ArrayList;

import org.apache.commons.beanutils.converters.StringConverter;

public interface ArticleDao<T, K>  {

    void createTable() throws SQLException;
    void dropTable() throws SQLException;
    String create(T object) throws SQLException;
    T read(T object) throws SQLException;
    void delete(String L) throws SQLException;
    void update(T object) throws SQLException;
    ArrayList<T> list() throws SQLException;
    ArrayList<T> findAndList(String L) throws SQLException;
    Article findArticleById(Integer I) throws SQLException;
    
}