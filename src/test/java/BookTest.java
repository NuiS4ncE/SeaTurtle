import SeaTurtle.Book;
import static org.junit.Assert.assertEquals;
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
        assertEquals("-", book.getAuthor());
    }
    
    @Test
    public void bookHasNoPageCount() {
        assertEquals("-", book.getPageCount());
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
    public void bookHasCorrectString() {
        assertEquals("Kirjan nimi: Title, kirjoittaja: -, sivumäärä: -", book.toString());
    }
    
}
