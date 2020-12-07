package SeaTurtle;

import SeaTurtle.model.Article;
import SeaTurtle.model.Book;
import SeaTurtle.ui.ConsoleColors;
import java.util.ArrayList;
import java.util.Collections;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ArticleTest {
    
    private Article article;
    
    @Before
    public void setUp() {
        article = new Article("Title");
    }
    
    @Test
    public void articleHasCorrectTitle() {
        assertEquals("Title", article.getTitle());
    }
    
    @Test
    public void articleHasNoUrl() {
        assertNull(article.getUrl());
    }
    
    @Test
    public void articleHasCorrectUrl() {
        article.setUrl("https://www.hs.fi/");
        assertEquals("https://www.hs.fi/", article.getUrl());
    }
    
    @Test
    public void articleHasCorrectStringWithJustTitle() {
        assertEquals("Artikkeli: " + ConsoleColors.YELLOW + "Title" + ConsoleColors.RESET + ".", article.toString());
    }
    
    @Test
    public void articleHasCorrectStringWithTitleAndUrl() {
        article.setUrl("https://www.hs.fi/");
        assertEquals("Artikkeli: " + ConsoleColors.YELLOW + "Title" + ConsoleColors.RESET + ". <https://www.hs.fi/>", article.toString());
    }
    
    @Test
    public void articleComparedCorrectly() {
        ArrayList<Article> articles = new ArrayList<>();
        articles.add(article);
        articles.add(new Article("Other"));
        Collections.sort(articles);
        assertEquals("Artikkeli: " + ConsoleColors.YELLOW + "Other" + ConsoleColors.RESET + ".", articles.get(0).toString());
    }
    
    @Test
    public void articlesAreEqualWhenSameDetails() {
        article.setUrl("https://www.hs.fi/");
        Article other = new Article("Title", "https://www.hs.fi/");
        assertTrue(article.equals(other));
    }
    
    @Test
    public void articlesHaveSameHashcodeWhenSameDetails() {
        article.setUrl("https://www.hs.fi/");
        Article other = new Article("Title", "https://www.hs.fi/");
        assertEquals(article.hashCode(), other.hashCode());
    }
    
    @Test
    public void articlesAreNotEqualWhenDifferentDetails() {
        Article other = new Article("Title", "https://www.hs.fi/");
        Article another = new Article("Other");
        assertFalse(article.equals(other));
        assertFalse(article.equals(another));
    }
    
    @Test
    public void articleDoesntEqualToBook() {
        Book book = new Book("Title");
        assertFalse(article.equals(book));
    }
    
}
