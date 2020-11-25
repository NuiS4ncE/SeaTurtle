package SeaTurtle.dao;

import SeaTurtle.Book;
import java.io.File;
import java.sql.*;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.List;

public class DBBookDao implements BookDao<Book, Integer> {

    private Connection con;
    private PreparedStatement prepstmt;
    private Statement stmt;

    public DBBookDao() {
        try {
            createTable();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }

    private void startCon() throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:seaturtle.db");
//        System.out.println("Connection to SQLite has been established.");
        stmt = con.createStatement();
    }

    private void closeCon() throws SQLException {
        stmt.close();
        con.close();
    }

    @Override
    public void createTable() throws SQLException {
//        System.out.println("creating table Book");
        startCon();
        prepstmt = con.prepareStatement("CREATE TABLE IF NOT EXISTS " 
        + "Book (" 
        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "title TEXT, "
        + "author TEXT, "
        + "pagecount TEXT, "
        + "bookmark TEXT"
        + ")"
        );

        prepstmt.executeUpdate();
        closeCon();
    }

    @Override
    public void create(Book book) throws SQLException {
//        createTable();

        startCon();
        prepstmt = con.prepareStatement("INSERT INTO Book "
        + "(title, author, pagecount, bookmark)"
        + "VALUES (?,?,?,?)");
        prepstmt.setString(1, book.getTitle());
        prepstmt.setString(2, book.getAuthor());
        prepstmt.setString(3, book.getPageCount());
        prepstmt.setString(4, book.getBookmark());
        prepstmt.executeUpdate();
        closeCon();
    }

    @Override
    public Book read(Book book) throws SQLException {
        startCon();
        Book returnBook;
        prepstmt = con.prepareStatement("SELECT * FROM Book WHERE title = ? AND author = ? AND pagecount = ? AND bookmark = ?");
        prepstmt.setString(1, book.getTitle());
        prepstmt.setString(2, book.getAuthor());
        prepstmt.setString(3, book.getPageCount());
        prepstmt.setString(4, book.getBookmark());
        ResultSet rs = prepstmt.executeQuery();
        if (!rs.next()) {
            return null;
        } else {
            returnBook = new Book(rs.getString("title"), rs.getString("author"), rs.getString("pagecount"), rs.getString("bookmark"));
        }
        rs.close();
        prepstmt.close();
        closeCon();
        return returnBook;
    }

    @Override
    public void delete(Book book) throws SQLException {
        startCon();
        prepstmt = con.prepareStatement("DELETE FROM Book WHERE title = ? AND author = ? AND pagecount = ? AND bookmark = ?");
        prepstmt.setString(1, book.getTitle());
        prepstmt.setString(2, book.getAuthor());
        prepstmt.setString(3, book.getPageCount());
        prepstmt.setString(4, book.getBookmark());
        prepstmt.executeUpdate();
        //prepstmt.close();
        closeCon();
    }
    
    public void update(Book book) throws SQLException {
        startCon();
        prepstmt = con.prepareStatement("UPDATE Book SET author = ? AND pagecount = ? AND bookmark = ? WHERE title = ?");
        prepstmt.setString(1, book.getAuthor());
        prepstmt.setString(2, book.getPageCount());
        prepstmt.setString(3, book.getBookmark());
        prepstmt.setString(4, book.getTitle());
        prepstmt.executeUpdate();
        prepstmt.close();
        closeCon();
    }

    @Override
    public ArrayList<Book> list() throws SQLException {
        startCon();
        ArrayList<Book> bookList = new ArrayList<>();
        prepstmt = con.prepareStatement("SELECT * FROM Book");
        ResultSet rs = prepstmt.executeQuery();
        while (rs.next()) {
            bookList.add(new Book(rs.getString("title"), rs.getString("author"), rs.getString("pagecount"), rs.getString("bookmark")));
        }
        prepstmt.close();
        closeCon();
        return bookList;
    }

    @Override
    public ArrayList<Book> findAndList(String searchWord) throws SQLException {
        startCon();
        ArrayList<Book> findBookList = new ArrayList<>();
        prepstmt = con.prepareStatement("SELECT * FROM Book WHERE title LIKE ?");
        prepstmt.setString(1, searchWord);
        ResultSet rs = prepstmt.executeQuery();
        while (rs.next()) {
            findBookList.add(new Book(rs.getString("title"), rs.getString("author"), rs.getString("pagecount"), rs.getString("bookmark")));
        }
        prepstmt.close();
        closeCon();

        return findBookList;
    }

}
