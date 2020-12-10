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
            + "type TEXT,"
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
        + "(tag, type, bookid)"
        + "VALUES (?, ?, ?)");
        prepstmt.setString(1, tag.getTag());
        prepstmt.setString(2, tag.getType());
        prepstmt.setString(3, tag.getBookId());
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
            returnTag = new Tag(rs.getString("type"), rs.getString("tag"), rs.getInt("id"), rs.getString("bookid"));
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

    @Override
    public void deleteById(Integer id) throws SQLException {
        startCon();
        prepstmt = con.prepareStatement("DELETE FROM Tag WHERE id = ?");
        prepstmt.setInt(1, id);
        prepstmt.executeUpdate();
        prepstmt.close();
        closeCon();

    }
    

    @Override
    public ArrayList<Tag> list() throws SQLException {
        startCon();
        ArrayList<Tag> tagList = new ArrayList<>();
        prepstmt = con.prepareStatement("SELECT * FROM Tag");
        ResultSet rs = prepstmt.executeQuery();
        while (rs.next()) {
            tagList.add(new Tag(rs.getString("type"), rs.getString("tag"), rs.getInt("id"), rs.getString("bookid")));
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
            findTagList.add(new Tag(rs.getString("type"), rs.getString("tag"), rs.getInt("id"), rs.getString("bookid")));
        }
        prepstmt.close();
        closeCon();

        return findTagList;
    }
    
    @Override
    public ArrayList<Integer> findIdsByTag(String searchWord) throws SQLException {
        startCon();
        ArrayList<Integer> bookIds = new ArrayList<>();
        prepstmt = con.prepareStatement("SELECT bookid FROM Tag WHERE tag LIKE ?" );
        prepstmt.setString(1, "%"+searchWord+"%");
        ResultSet rs = prepstmt.executeQuery();
        while (rs.next()) {
            bookIds.add(Integer.parseInt(rs.getString("bookid")));
        }
        prepstmt.close();
        closeCon();

        return bookIds;
    }

    @Override
    public ArrayList<Tag> findTagsByBookId(Integer bookId) throws SQLException {
        startCon();
        ArrayList<Tag> findTagList = new ArrayList<>();
        prepstmt = con.prepareStatement("SELECT * FROM Tag WHERE bookid IS ?" );
        prepstmt.setInt(1, bookId);
        ResultSet rs = prepstmt.executeQuery();
        while (rs.next()) {
            findTagList.add(new Tag(rs.getString("type"), rs.getString("tag"), rs.getInt("id"), rs.getString("bookid")));
        }
        prepstmt.close();
        closeCon();
        return findTagList;
    }

    @Override
    public ArrayList<Tag> findTagsByIdAndType(Integer id, String type) throws SQLException {
        startCon();
        ArrayList<Tag> findTagList = new ArrayList<>();
        prepstmt = con.prepareStatement("SELECT * FROM Tag WHERE bookid IS ? AND type IS ?" );
        prepstmt.setInt(1, id);
        prepstmt.setString(2, type);
        ResultSet rs = prepstmt.executeQuery();
        while (rs.next()) {
            findTagList.add(new Tag(rs.getString("type"), rs.getString("tag"), rs.getInt("id"), rs.getString("bookid")));
        }
        prepstmt.close();
        closeCon();
        return findTagList;
    }




}
