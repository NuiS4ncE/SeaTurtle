package SeaTurtle.dao;

import SeaTurtle.model.Article;

import java.sql.*;
import java.util.*;

public class DBArticleDao implements ArticleDao<Article, Integer> {

    private Connection conn;
    private PreparedStatement prepstmt;
    private Statement stmt;
    private String dbFile;

    public DBArticleDao() {
        this.dbFile = "jdbc:sqlite:seaturtle.db";
        try {
            createTable();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }

    public DBArticleDao(String dbFile) {
        this.dbFile = dbFile;
        try {
            createTable();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }

    private void startCon() throws SQLException {
        conn = DriverManager.getConnection(dbFile);
        stmt = conn.createStatement();
    }

    private void closeCon() throws SQLException {
        stmt.close();
        conn.close();
    }

    @Override
    public void createTable() throws SQLException {
        startCon();
        prepstmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS " 
            + "Article (" 
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "title TEXT, "
            + "url TEXT "
            + ")"
        );

        prepstmt.executeUpdate();
        prepstmt.close();
        closeCon();
    }
    
    @Override
    public void dropTable() throws SQLException {
        startCon();
        prepstmt = conn.prepareStatement("DROP TABLE Article");
        prepstmt.executeUpdate();
        prepstmt.close();
        closeCon();
    }

    @Override
    public void create(Article article) throws SQLException {
        startCon();
        prepstmt = conn.prepareStatement("INSERT INTO Article"
                + "(title, url)"
                + "VALUES (?,?)");
        prepstmt.setString(1, article.getTitle());
        prepstmt.setString(2, article.getUrl());
        prepstmt.executeUpdate();
        prepstmt.close();
        closeCon();
    }

    @Override
    public Article read(Article article) throws SQLException {
        startCon();
        Article returnArticle;
        prepstmt = conn.prepareStatement("SELECT * FROM Article WHERE title = ? AND url = ?");
        prepstmt.setString(1, article.getTitle());
        prepstmt.setString(2, article.getUrl());
        ResultSet rs = prepstmt.executeQuery();
        if (!rs.next()) {
            return null;
        } else {
            returnArticle = new Article(rs.getString("title"), rs.getString("url"));
        }
        rs.close();
        prepstmt.close();
        closeCon();
        return returnArticle;
    }

    @Override
    public void delete(String title) throws SQLException {
        startCon();
        prepstmt = conn.prepareStatement("DELETE FROM Article WHERE title = ?");
        prepstmt.setString(1, title);
        prepstmt.executeUpdate();
        prepstmt.close();
        closeCon();
    }
    
    public void update(Article article) throws SQLException {
        startCon();
        prepstmt = conn.prepareStatement("UPDATE Article SET url = ? WHERE title = ?");
        prepstmt.setString(1, article.getUrl());
        prepstmt.setString(2, article.getTitle());
        prepstmt.executeUpdate();
        prepstmt.close();
        closeCon();
    }

    @Override
    public ArrayList<Article> list() throws SQLException {
        startCon();
        ArrayList<Article> articleList = new ArrayList<>();
        prepstmt = conn.prepareStatement("SELECT * FROM Article");
        ResultSet rs = prepstmt.executeQuery();
        while (rs.next()) {
            articleList.add(new Article(rs.getString("title"), rs.getString("url")));
        }
        prepstmt.close();
        closeCon();
        return articleList;
    }

    @Override
    public ArrayList<Article> findAndList(String searchWord) throws SQLException {
        startCon();
        ArrayList<Article> findArticleList = new ArrayList<>();
        prepstmt = conn.prepareStatement("SELECT * FROM Article WHERE title LIKE ?");
        prepstmt.setString(1, "%"+searchWord+"%");
        ResultSet rs = prepstmt.executeQuery();
        while (rs.next()) {
            findArticleList.add(new Article(rs.getString("title"), rs.getString("url")));
        }
        if(findArticleList.isEmpty()){
            prepstmt = conn.prepareStatement("SELECT * FROM Article WHERE url LIKE ?");
            prepstmt.setString(1, "%"+searchWord+"%");
            rs = prepstmt.executeQuery();
            while (rs.next()) {
                findArticleList.add(new Article(rs.getString("title"), rs.getString("url")));
            }
        }
        prepstmt.close();
        closeCon();

        return findArticleList;
    }

}
