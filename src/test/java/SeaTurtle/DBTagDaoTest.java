package SeaTurtle;

import SeaTurtle.model.Tag;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;

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
        tags.add(new Tag("BOOK", "tag2", 2, "3"));


        return tags;
    }

    @Test
    public void createTagAndRetrievesFromDB() throws Exception {
        Tag testTag = exampleTags().get(0);
        dbTagDao.create(testTag);
        
        assertTrue(dbTagDao.list().contains(testTag));
    }

    @Test
    public void createTagAndReadFromDB() throws Exception {
        Tag testTag = exampleTags().get(0);
        dbTagDao.create(testTag);
        
        assertEquals(dbTagDao.read(testTag), testTag);
    }

    @Test
    public void createTagAndReadNotFoundFromDB() throws Exception {
        Tag testTag = exampleTags().get(0);
        dbTagDao.create(testTag);
        
        assertEquals(dbTagDao.read(new Tag("BOOK", "togi", 7, "666")), null);
    }
 
    @Test
    public void createAndDeleteFromDB() throws SQLException {
        Tag testTag = exampleTags().get(0);
        dbTagDao.create(testTag);
        assertTrue(dbTagDao.list().contains(testTag));

        dbTagDao.delete(testTag);
        assertTrue(!dbTagDao.list().contains(testTag));
    }

    @Test
    public void createAndDeleteByIdFromDB() throws SQLException {
        Tag testTag = exampleTags().get(0);
        dbTagDao.create(testTag);
        assertTrue(dbTagDao.list().contains(testTag));

        dbTagDao.deleteById(1);
        assertTrue(!dbTagDao.list().contains(testTag));
    }

    @Test
    public void createAndFindTagByTitleFromDB() throws SQLException {
        Tag testTag = exampleTags().get(0);
        dbTagDao.create(testTag);

        assertTrue(dbTagDao.findAndList(testTag.getTag()).contains(testTag));
    }

    @Test
    public void createAndFindTagByBookIdFromDB() throws SQLException {
        Tag testTag = exampleTags().get(0);
        dbTagDao.create(testTag);

        assertTrue(dbTagDao.findTagsByBookId(Integer.parseInt(testTag.getBookId())).contains(testTag));
    }
/*
    @Test
    public void createAndFindIdByTagFromDB() throws SQLException {
        Tag testTag = exampleTags().get(0);
        dbTagDao.create(testTag);

        assertTrue(dbTagDao.findIdsByTag("tagi").contains("3"));
    }
*/



}