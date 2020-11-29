package SeaTurtle;

import SeaTurtle.dao.DBArticleDao;
import SeaTurtle.model.Article;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DBArticleDaoTest {

    private DBArticleDao dbArticleDao;

    @Before
    public void setUp() {
        dbArticleDao = new DBArticleDao("jdbc:sqlite:seaturtletest.db");
    }

    @After
    public void emptyDb() throws SQLException {
        dbArticleDao.dropTable();
    }

    public ArrayList<Article> exampleBooks() {
        ArrayList<Article> articles = new ArrayList<>();
        articles.add(new Article("test", "tester.test"));
        articles.add(new Article("history of apple's history", "apple.history"));
        articles.add(new Article("i like turtles", "joe mama"));
        return articles;
    }

    @Test
    public void createsABookAndRetrievesFromDB() throws Exception {
        Article testArticle = exampleBooks().get(0);
        dbArticleDao.create(testArticle);

        assertTrue(dbArticleDao.list().contains(testArticle));
    }

    @Test
    public void createsMultipleBooksAndRetrievesFromDB() throws SQLException{
        ArrayList<Article> expectedArticles = exampleBooks();
        for (int i = 0; i < expectedArticles.size(); i++) {
            dbArticleDao.create(expectedArticles.get(i));
        }

        assertEquals(expectedArticles, dbArticleDao.list());
    }

    @Test
    public void createAndFindBookByTitleFromDB() throws SQLException {
        Article testBook = new Article("testingit", "testr.tetetest");
        dbArticleDao.create(testBook);

        assertTrue(dbArticleDao.findAndList(testBook.getTitle()).contains(testBook));
    }

}
