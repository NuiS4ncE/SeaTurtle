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
        + "pagecount TEXT" 
        + ")"
        );

        prepstmt.executeUpdate();
        closeCon();
    }

    @Override
    public void create(Book kirja) throws SQLException {
        createTable();

        startCon();
        prepstmt = con.prepareStatement("INSERT INTO Book "
        + "(title, author, pagecount)"
        + "VALUES (?,?,?)");
        prepstmt.setString(1, kirja.getTitle());
        prepstmt.setString(2, kirja.getAuthor());
        String pCount = "" + kirja.getPageCount();
        prepstmt.setString(3, pCount);
        prepstmt.executeUpdate();
        closeCon();
    }

    @Override
    public Book read(Book kirja, Integer id) throws SQLException {
        startCon();
        prepstmt = con.prepareStatement("SELECT id FROM Book WHERE id = ?");
        prepstmt.setInt(1, id);
        ResultSet rs = prepstmt.executeQuery();
        int rsId;
        if (!rs.next()) {
            return null;
        } else {
            rsId = rs.getInt("id");
        }
        if (rsId == id) {
            rs.close();
            closeCon();
            return kirja;
        }
        rs.close();
        prepstmt.close();
        closeCon();
        return null;
    }

    @Override
    public void delete(Book kirja, Integer id) throws SQLException {
        startCon();
        prepstmt = con.prepareStatement("DELETE FROM Book WHERE id = ?");
        prepstmt.setInt(1, id);
        prepstmt.executeUpdate();
        prepstmt.close();
        closeCon();
    }

    @Override
    public List<Book> list(Integer id) throws SQLException {
        startCon();
        List<Book> kirjaList = new ArrayList<>();
        prepstmt = con.prepareStatement("SELECT * FROM Book WHERE id = ?");
        prepstmt.setInt(1, id);
        ResultSet rs = prepstmt.executeQuery();
        while (rs.next()) {
//            kirjaList.add(new Book(rs.getInt("id"), rs.getString("otsikko"), rs.getString("kirjoittaja"), rs.getInt("sivumaara")));
        }
        prepstmt.close();
        closeCon();
        return kirjaList;
    }
}
