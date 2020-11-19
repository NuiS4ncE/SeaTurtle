package SeaTurtle.dao;

import java.io.File;
import java.sql.*;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.List;

public class DBBookDao implements BookDao<Kirja, Integer> {

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
    public void create(Kirja kirja, Integer id) throws SQLException {
        startCon();
        prepstmt = con.prepareStatement("INSERT INTO Kirja"
        + "(otsikko, kirjoittaja, sivumaara)"
        + "VALUES (?,?,?)");
        prepstmt.setString(1, kirja.getNimi());
        prepstmt.setString(2, kirja.getKirjoittaja());
        prepstmt.setString(3, kirja.getSivumaara());
        prepstmt.executeUpdate();
        closeCon();
    }

    @Override
    public Kirja read(Kirja kirja, Integer id) throws SQLException {
        startCon();
        prepstmt = con.prepareStatement("SELECT id FROM Kirja WHERE id = ?");
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
    public void delete(Kirja kirja, Integer id) throws SQLException {
        startCon();
        prepstmt = con.prepareStatement("DELETE FROM Kirja WHERE id = ?");
        prepstmt.setInt(1, id);
        prepstmt.executeUpdate();
        prepstmt.close();
        closeCon();
    }

    @Override
    public List<Kirja> list(Integer id) throws SQLException {
        startCon();
        List<Kirja> kirjaList = new ArrayList<>();
        prepstmt = con.prepareStatement("SELECT * FROM Kirja WHERE id = ?");
        prepstmt.setInt(1, id);
        ResultSet rs = prepstmt.executeQuery();
        while (rs.next()) {
            kirjaList.add(new Kirja(rs.getInt("id"), rs.getString("otsikko"), rs.getString("kirjoittaja"), rs.getString("sivumaara")));
        }
        prepstmt.close();
        closeCon();
        return kirjaList;
    }
}
