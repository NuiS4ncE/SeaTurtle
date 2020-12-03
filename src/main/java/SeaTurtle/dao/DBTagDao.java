package SeaTurtle.dao;

import SeaTurtle.model.Tag;

import java.sql.*;
//import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DBTagDao implements TagDao<Tag, Integer> {

    private Connection con;
    private PreparedStatement prepstmt;
    private Statement stmt;
    private String dbFile;

    public DBTagDao() {
        this.dbFile = "jdbc:sqlite:seaturtle.db";
        try {
            createTable();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }

    public DBTagDao(String dbFile) {
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
            + "Tag (" 
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "tag TEXT, "
            + "bookid INTEGER"
            + ")"
        );

        prepstmt.executeUpdate();
        prepstmt.close();
        closeCon();
    }
    
    @Override
    public void dropTable() throws SQLException {
        startCon();
        prepstmt = con.prepareStatement("DROP TABLE Tag");

        prepstmt.executeUpdate();
        prepstmt.close();
        closeCon();
    }


    @Override
    public void create(Tag tag) throws SQLException {
        startCon();
        prepstmt = con.prepareStatement("INSERT INTO Tag "
        + "(tag)"
        + "VALUES (?)");
        prepstmt.setString(1, tag.getTag());
        prepstmt.executeUpdate();
        prepstmt.close();
        closeCon();
    }

    @Override
    public Tag read(Tag tag) throws SQLException {
        startCon();
        Tag returnTag;
        prepstmt = con.prepareStatement("SELECT * FROM Tag WHERE tag = ?");
        prepstmt.setString(1, tag.getTag());
        ResultSet rs = prepstmt.executeQuery();
        if (!rs.next()) {
            return null;
        } else {
            returnTag = new Tag(rs.getString("tag"), rs.getInt("id"), rs.getInt("bookid"));
        }
        rs.close();
        prepstmt.close();
        closeCon();
        return returnTag;
    }

    @Override
    public void delete(Tag tag) throws SQLException {
        startCon();
        prepstmt = con.prepareStatement("DELETE FROM Tag WHERE tag = ?");
        prepstmt.setString(1, tag.getTag());
        prepstmt.executeUpdate();
        prepstmt.close();
        closeCon();
    }
    
    /*
    @Override
    public void updateBookmark(Tag tag) throws SQLException {
        startCon();
        prepstmt = con.prepareStatement("UPDATE Tag SET bookmark = ? WHERE id = ?");
        prepstmt.setString(1, book.getBookmark());
        prepstmt.setInt(2, book.getId());
        prepstmt.executeUpdate();
        prepstmt.close();
        closeCon();
    }
    */

    @Override
    public ArrayList<Tag> list() throws SQLException {
        startCon();
        ArrayList<Tag> tagList = new ArrayList<>();
        prepstmt = con.prepareStatement("SELECT * FROM Tag");
        ResultSet rs = prepstmt.executeQuery();
        while (rs.next()) {
            tagList.add(new Tag(rs.getString("tag"), rs.getInt("id"), rs.getInt("bookid")));
        }
        prepstmt.close();
        closeCon();
        return tagList;
    }

    @Override
    public ArrayList<Tag> findAndList(String searchWord) throws SQLException {
        startCon();
        ArrayList<Tag> findTagList = new ArrayList<>();
        prepstmt = con.prepareStatement("SELECT * FROM Tag WHERE tag LIKE ?" );
        prepstmt.setString(1, "%"+searchWord+"%");
        ResultSet rs = prepstmt.executeQuery();
        while (rs.next()) {
            findTagList.add(new Tag(rs.getString("tag"), rs.getInt("id"), rs.getInt("bookid")));
        }
        prepstmt.close();
        closeCon();

        return findTagList;
    }

}
