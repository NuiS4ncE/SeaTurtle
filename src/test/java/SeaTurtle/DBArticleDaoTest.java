package SeaTurtle;

import SeaTurtle.dao.DBArticleDao;
import SeaTurtle.model.Article;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

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

    public ArrayList<Article> exampleArticles() {
        ArrayList<Article> articles = new ArrayList<>();
        articles.add(new Article("test", "http://tester.net"));
        articles.add(new Article("history of apple's history", "http://apple.com"));
        articles.add(new Article("i like turtles", "http://joe.fi"));
        return articles;
    }

    @Test
    public void createsAnArticleAndRetrievesFromDB() throws Exception {
        Article testArticle = exampleArticles().get(0);
        dbArticleDao.create(testArticle);

        assertTrue(dbArticleDao.list().contains(testArticle));
    }

    @Test
    public void createsMultipleArticlesAndRetrievesFromDB() throws SQLException{
        ArrayList<Article> expectedArticles = exampleArticles();
        for (int i = 0; i < expectedArticles.size(); i++) {
            dbArticleDao.create(expectedArticles.get(i));
        }

        assertEquals(expectedArticles, dbArticleDao.list());
    }

    @Test
    public void createAndFindArticleByTitleFromDB() throws SQLException {
        Article testArticle = new Article("testingit", "http://testr.net");
        dbArticleDao.create(testArticle);

        assertTrue(dbArticleDao.findAndList(testArticle.getTitle()).contains(testArticle));
    }


    @Test
    public void articleCanBeReadFromDB() throws SQLException {
        Article testArticle = new Article("testingit", "http://testr.net");
        dbArticleDao.create(testArticle);
        Article readArticle = dbArticleDao.read(testArticle);

        assertEquals(testArticle, readArticle);
    }

    @Test
    public void articleCanBeUpdatedInDB() throws SQLException {
        Article testArticle = new Article("testingit", "http://testr.net");
        dbArticleDao.create(testArticle);
        testArticle.setUrl("http://test.fi");
        dbArticleDao.update(testArticle);

        assertEquals(dbArticleDao.findAndList(testArticle.getTitle()).get(0).getUrl(), "http://test.fi");
    }

    @Test
    public void articleCanBeDeletedFromDB() throws SQLException {
        Article testArticle = new Article("testingit", "http://testr.net");
        
        dbArticleDao.create(testArticle);
        assertTrue(dbArticleDao.findAndList(testArticle.getTitle()).contains(testArticle));
        
        dbArticleDao.delete(testArticle.getTitle());
        assertFalse(dbArticleDao.findAndList(testArticle.getTitle()).contains(testArticle));
    }

    @Test
    public void createAndFindArticleByUrlFromDB() throws SQLException {
        Article testArticle = new Article("testingitagain", "http://testragain.net");
        dbArticleDao.create(testArticle);

        assertTrue(dbArticleDao.findAndList(testArticle.getUrl()).contains(testArticle));
    }


}
