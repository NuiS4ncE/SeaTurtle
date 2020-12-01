package SeaTurtle.dao;

import SeaTurtle.model.Book;

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
    private String dbFile;

    public DBBookDao() {
        this.dbFile = "jdbc:sqlite:seaturtle.db";
        try {
            createTable();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }

    public DBBookDao(String dbFile) {
        this.dbFile = dbFile;
        try {
            createTable();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }

    private void startCon() throws SQLException {
        con = DriverManager.getConnection(dbFile);
        stmt = con.createStatement();
    }

    private void closeCon() throws SQLException {
        stmt.close();
        con.close();
    }

    @Override
    public void createTable() throws SQLException {
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
        prepstmt.close();
        closeCon();
    }
    
    @Override
    public void dropTable() throws SQLException {
        startCon();
        prepstmt = con.prepareStatement("DROP TABLE Book");

        prepstmt.executeUpdate();
        prepstmt.close();
        closeCon();
    }


    @Override
    public void create(Book book) throws SQLException {
        startCon();
        prepstmt = con.prepareStatement("INSERT INTO Book "
        + "(title, author, pagecount, bookmark)"
        + "VALUES (?,?,?,?)");
        prepstmt.setString(1, book.getTitle());
        prepstmt.setString(2, book.getAuthor());
        prepstmt.setString(3, book.getPageCount());
        prepstmt.setString(4, book.getBookmark());
        prepstmt.executeUpdate();
        prepstmt.close();
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
            returnBook = new Book(rs.getString("title"), rs.getString("author"), rs.getString("pagecount"), rs.getString("bookmark"), rs.getInt("id"));
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
    
    @Override
    public void updateBookmark(Book book) throws SQLException {
        startCon();
        prepstmt = con.prepareStatement("UPDATE Book SET bookmark = ? WHERE id = ?");
        prepstmt.setString(1, book.getBookmark());
        prepstmt.setInt(2, book.getId());
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
            bookList.add(new Book(rs.getString("title"), rs.getString("author"), rs.getString("pagecount"), rs.getString("bookmark"), rs.getInt("id")));
        }
        prepstmt.close();
        closeCon();
        return bookList;
    }

    @Override
    public ArrayList<Book> findAndList(String searchWord) throws SQLException {
        startCon();
        ArrayList<Book> findBookList = new ArrayList<>();
        prepstmt = con.prepareStatement("SELECT * FROM Book WHERE title LIKE ?" );
        prepstmt.setString(1, "%"+searchWord+"%");
        ResultSet rs = prepstmt.executeQuery();
        while (rs.next()) {
            findBookList.add(new Book(rs.getString("title"), rs.getString("author"), rs.getString("pagecount"), rs.getString("bookmark"), rs.getInt("id")));
        }
        if(findBookList.isEmpty()){
            prepstmt = con.prepareStatement("SELECT * FROM Book WHERE author LIKE ?");
            prepstmt.setString(1, "%"+searchWord+"%");
            rs = prepstmt.executeQuery();
            while (rs.next()) {
                findBookList.add(new Book(rs.getString("title"), rs.getString("author"), rs.getString("pagecount"), rs.getString("bookmark"), rs.getInt("id")));
                //findBookList.add(new Book(rs.getString("title"), rs.getString("author"), rs.getString("pagecount")));
            }
        }
        prepstmt.close();
        closeCon();

        return findBookList;
    }

}
