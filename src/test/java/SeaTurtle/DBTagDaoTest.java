package SeaTurtle;

import SeaTurtle.model.Tag;
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

import SeaTurtle.dao.DBTagDao;

import static org.junit.Assert.*;

public class DBTagDaoTest {

    private DBTagDao dbTagDao;

    @Before
    public void setUp() {
        dbTagDao = new DBTagDao("jdbc:sqlite:seaturtletest.db");
    }

    @After
    public void emptyDb() throws SQLException {
        dbTagDao.dropTable();
    }

    public ArrayList<Tag> exampleTags() {
        ArrayList<Tag> tags = new ArrayList<>();        
        tags.add(new Tag("BOOK", "tagi", 1, "3"));

        return tags;
    }

    @Test
    public void createTagAndRetrievesFromDB() throws Exception {
        Tag testTag = exampleTags().get(0);
        dbTagDao.create(testTag);
        
        assertTrue(dbTagDao.list().contains(testTag));
    }

    @Test
    public void createAndFindTagByTitleFromDB() throws SQLException {
        Tag testTag = new Tag("BOOK", "tagi", 1, "3");
        dbTagDao.create(testTag);

        assertTrue(dbTagDao.findAndList(testTag.getTag()).contains(testTag));
    }

    @Test
    public void createAndFindTagByBookIdFromDB() throws SQLException {
        Tag testTag = new Tag("BOOK", "tagi", 1, "3");
        dbTagDao.create(testTag);

        assertTrue(dbTagDao.findTagsByBookId(Integer.parseInt(testTag.getBookId())).contains(testTag));
    }



}