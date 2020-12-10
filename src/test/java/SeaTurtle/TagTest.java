package SeaTurtle;

import SeaTurtle.model.Article;
import SeaTurtle.model.Tag;
import SeaTurtle.ui.ConsoleColors;
import java.util.ArrayList;
import java.util.Collections;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TagTest {
    
    Tag tag;
    Tag tag2;
    
    @Before
    public void setUp() {
        tag = new Tag("book", "testing");
        tag2 = new Tag("book", "testing", 4, "2");
    }
    
    @Test
    public void tagHasCorrectTag() {
        assertEquals("testing", tag.getTag());
    }

    @Test
    public void tagHasCorrectType() {
        assertEquals("book", tag.getType());
    }
    
    @Test
    public void tagHasNoId() {
        assertNull(tag.getId());
    }
    
    @Test
    public void tagHasNoBookId() {
        assertNull(tag.getBookId());
    }
    
    @Test
    public void tagHasCorrectBookId() {
        tag.setBookId("1");
        assertEquals("1", tag.getBookId());
    }

    @Test
    public void tagSetsCorrectTag() {
        tag.setTag("writing");
        assertEquals("writing", tag.getTag());
    }

    @Test
    public void tagSetsCorrectType() {
        tag.setType("book");
        assertEquals("book", tag.getType());
    }


    @Test
    public void tagWithAllParameters() {
        assertEquals("book", tag2.getType());
        assertEquals("testing", tag2.getTag());
        assertTrue(4 == tag2.getId());
        assertEquals("2", tag2.getBookId());
    }
    
    @Test
    public void tagHasCorrectStringWithBook() {
        assertEquals("testing (id  4)", tag2.toString());
    }

    @Test
    public void tagHasCorrectStringWithArticle() {
        tag2.setType("ARTICLE");
        assertEquals("testing (Artikkeli 2)", tag2.toString());    
    }
}