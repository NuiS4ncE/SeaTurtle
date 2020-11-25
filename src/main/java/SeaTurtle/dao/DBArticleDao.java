package SeaTurtle.dao;

import java.io.File;
import java.sql.*;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.List;

public class DBArticleDao implements BookDao<Artikkeli, Integer> {

    private Connection con;
    private PreparedStatement prepstmt;
    private Statement stmt;

    private void startCon() throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:database:db");
        stmt = con.createStatement();
    }

    private void closeCon() throws SQLException {
        stmt.close();
        con.close();
    }

    @Override
    public void create(Artikkeli artikkeli) throws SQLException {
        startCon();
        prepstmt = con.prepareStatement("INSERT INTO Artikkeli"
                + "(otsikko, url)"
                + "VALUES (?,?)");
        prepstmt.setString(1, artikkeli.getOtsikko());
        prepstmt.setString(2, artikkeli.getUrl());
        prepstmt.executeUpdate();
        closeCon();
    }

    @Override
    public Artikkeli read(Artikkeli artikkeli) throws SQLException {
        startCon();
        //prepstmt = con.prepareStatement("SELECT id FROM Artikkeli WHERE id = ?");
        //prepstmt.setInt(1, id);
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
            return artikkeli;
        }
        rs.close();
        prepstmt.close();
        closeCon();
        return null;
    }

    @Override
    public void delete(Artikkeli artikkeli) throws SQLException {
        startCon();
        prepstmt = con.prepareStatement("DELETE FROM Artikkeli WHERE id = ?");
        prepstmt.setInt(1, id);
        prepstmt.executeUpdate();
        prepstmt.close();
        closeCon();
    }

    @Override
    public ArrayList<Artikkeli> list() throws SQLException {
        startCon();
        ArrayList<Artikkeli> artikkeliList = new ArrayList<>();
        prepstmt = con.prepareStatement("SELECT * FROM Artikkeli WHERE id = ?");
        //prepstmt.setInt(1, id);
        ResultSet rs = prepstmt.executeQuery();
        while (rs.next()) {
            artikkeliList.add(new Artikkeli(rs.getInt("id"), rs.getString("otsikko"), rs.getString("kirjoittaja"), rs.getString("sivumaara")));
        }
        prepstmt.close();
        closeCon();
        return artikkeliList;
    }

}
