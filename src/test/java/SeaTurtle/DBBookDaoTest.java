package SeaTurtle;

import SeaTurtle.dao.DBBookDao;
import SeaTurtle.Book;
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

public class DBBookDaoTest {

    private DBBookDao dbBookDao;

    @Before
    public void startConAndSetUp() {
        dbBookDao = new DBBookDao("jdbc:sqlite:seaturtletest.db");
    }

    @After
    public void emptyDb() {
        try {
            dbBookDao.dropTable();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ArrayList<Book> exampleBooks() {
        ArrayList<Book> books = new ArrayList<>();        
        books.add(new Book("history of apple", "someone at apple", "20"));
        books.add(new Book("test", "tester", "666"));
        books.add(new Book("computers r cool", "dijkstra", "1000"));
        return books;
    }

    
    @Test
    public void listAllBooks() {
        ArrayList<Book> expectedBooks = exampleBooks();
        for (int i = 0; i < expectedBooks.size(); i++) {
            try {
                dbBookDao.create(expectedBooks.get(i));
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        
        try {
            assertEquals(expectedBooks, dbBookDao.list());
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
/*
    @Test
    public void createBookAndRetrieveFromDB() throws Exception {
        //ArrayList<Book> expectedAL = new ArrayList<>();
        Book testBook = new Book("test", "tester", "666", "42");
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
