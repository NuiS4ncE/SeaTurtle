package SeaTurtle;

import SeaTurtle.dao.DBBookDao;
import SeaTurtle.ui.TextUI;
import io.cucumber.messages.internal.com.google.gson.internal.bind.SqlDateTypeAdapter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import SeaTurtle.dao.DBBookDao;

public class DaoTest {

    private Connection con;
    private PreparedStatement prepstmt;
    private Statement stmt;
    private DBBookDao dbBookDao;

    @Before
    public void startConAndSetUp() {
        dbBookDao = new DBBookDao();
        try {
            con = DriverManager.getConnection("jdbc:sqlite:seaturtle.db");
            stmt = con.createStatement();
            prepstmt = con.prepareStatement("CREATE TABLE IF NOT EXISTS "
                    + "Book ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "title TEXT, "
                    + "author TEXT, "
                    + "pagecount TEXT"
                    + ")"
            );

            prepstmt.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e);
        }
    }

    @After
    public void closeCon() {
        try {
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /*
    @Test
    public void listAllBooks() {
        ArrayList<Book> expectedAL = new ArrayList<>();
        try {
            //assertTrue(dbBookDao.list().equals(expectedAL));
            assertEquals(dbBookDao.list(), expectedAL);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Test
    public void createBookAndCheckDB() {
        //ArrayList<Book> expectedAL = new ArrayList<>();
        Book testBook = new Book("test", "tester", "666");
        //expectedAL.add(testBook);
        try {
            dbBookDao.create(testBook);
            assertTrue(dbBookDao.list().contains(testBook));
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
*/

}
