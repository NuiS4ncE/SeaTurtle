package SeaTurtle;

import SeaTurtle.model.Article;
import SeaTurtle.model.Book;
import SeaTurtle.ui.ConsoleColors;
import java.util.ArrayList;
import java.util.Collections;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BookTest {
    
    Book book;
    
    @Before
    public void setUp() {
        book = new Book("Title");
    }
    
    @Test
    public void bookHasCorrectTitle() {
        assertEquals("Title", book.getTitle());
    }
    
    @Test
    public void bookHasNoAuthor() {
        assertNull(book.getAuthor());
    }
    
    @Test
    public void bookHasNoPageCount() {
        assertNull(book.getPageCount());
    }
    
    @Test
    public void bookHasNoBookmark() {
        assertNull(book.getBookmark());
    }
    
    @Test
    public void bookHasNoId() {
        assertNull(book.getId());
    }
    
    @Test
    public void bookHasCorrectAuthor() {
        book.setAuthor("Author");
        assertEquals("Author", book.getAuthor());
    }
    
    @Test
    public void bookHasCorrectPageCount() {
        book.setPageCount("200");
        assertEquals("200", book.getPageCount());
    }
    
    @Test
    public void bookHasCorrectBookmarkIfPageCount() {
        book.setPageCount("200");
        book.setBookmark("20");
        assertEquals("20", book.getBookmark());
    }
    
    @Test
    public void cannotAddBookmarkIfNoPageCount() {
        book.setBookmark("20");
        assertNull(book.getBookmark());
    }
    
    @Test
    public void bookHasCorrectStringWithJustTitle() {
        assertEquals("Kirjan nimi: Title.", book.toString());
    }
    
    @Test
    public void bookHasCorrectStringWithTitleAndAuthor() {
        book.setAuthor("Author");
        assertEquals("Kirjan nimi: Title. Kirjoittaja: Author.", book.toString());
    }
    
    @Test
    public void bookHasCorrectStringWithTitleAndPageCount() {
        book.setPageCount("200");
        assertEquals("Kirjan nimi: Title. 200 sivua. Kirjasta luettu " + ConsoleColors.RED + "0 %" + ConsoleColors.RESET + ".", book.toString());
    }
    
    @Test
    public void bookHasCorrectStringWithTitlePageCountAndBookmark() {
        book.setPageCount("200");
        book.setBookmark("20");
        assertEquals("Kirjan nimi: Title. 200 sivua. Kirjanmerkki sivulla 20. Kirjasta luettu " + ConsoleColors.YELLOW + "10 %" + ConsoleColors.RESET + ".", book.toString());
    }
    
    @Test
    public void bookHasCorrectStringWithAllDetails() {
        book.setAuthor("Author");
        book.setPageCount("200");
        book.setBookmark("200");
        assertEquals("Kirjan nimi: Title. Kirjoittaja: Author. 200 sivua. Kirjanmerkki sivulla 200. Kirjasta luettu " + ConsoleColors.GREEN + "100 %" + ConsoleColors.RESET + ".", book.toString());
    }
    
    @Test
    public void bookComparedCorrectly() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(book);
        books.add(new Book("Other"));
        Collections.sort(books);
        assertEquals("Kirjan nimi: Other.", books.get(0).toString());
    }
    
    @Test
    public void booksAreEqualWhenSameDetails() {
        book.setAuthor("Author");
        book.setPageCount("100");
        Book other = new Book("Title");
        other.setAuthor("Author");
        other.setPageCount("100");
        assertTrue(book.equals(other));
    }
    
    @Test
    public void booksHaveSameHashcodeWhenSameDetails() {
        book.setAuthor("Author");
        book.setPageCount("100");
        Book other = new Book("Title");
        other.setAuthor("Author");
        other.setPageCount("100");
        assertEquals(book.hashCode(), other.hashCode());
    }
    
    @Test
    public void booksAreNotEqualWhenDifferentDetails() {
        Book other = new Book("Title");
        other.setAuthor("Author");
        Book another = new Book("Other");
        assertFalse(book.equals(other));
        assertFalse(book.equals(another));
    }
    
    @Test
    public void bookDoesntEqualToArticle() {
        Article article = new Article("Title");
        assertFalse(book.equals(article));
    }
    
}
