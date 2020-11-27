package SeaTurtle;

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
        assertEquals("Kirjan nimi: Title. 200 sivua.", book.toString());
    }
    
    @Test
    public void bookHasCorrectStringWithAllDetails() {
        book.setAuthor("Author");
        book.setPageCount("200");
        assertEquals("Kirjan nimi: Title. Kirjoittaja: Author. 200 sivua.", book.toString());
    }
    
    @Test
    public void bookComparedCorrectly() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(book);
        books.add(new Book("Other"));
        Collections.sort(books);
        assertEquals("Kirjan nimi: Other.", books.get(0).toString());
    }
}
