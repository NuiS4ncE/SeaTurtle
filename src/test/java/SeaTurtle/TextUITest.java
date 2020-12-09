package SeaTurtle;

import java.lang.System;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import SeaTurtle.dao.*;
import SeaTurtle.model.Book;
import SeaTurtle.ui.*;
import java.sql.SQLException;

public class TextUITest {

    TextUI textUI;
    Scanner s;
    ByteArrayOutputStream out;

    @Mock
    BookDao mockDBBookDao = new DBBookDao();

    @Mock
    ArticleDao mockDBArticleDao = new DBArticleDao();

    @Mock
    TagDao mockDBTagDao = new DBTagDao();


    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        s = new Scanner(System.in);
        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao, mockDBTagDao);
    }

    @After
    public void tearDown() {
        System.setIn(System.in);
        System.setOut(System.out);
    }


    @Test
    public void textUIHelp() {
        textUI.help();

        assertTrue(out.toString().contains("Käytettävissä"));
    }

    @Test
    public void TextUIExit() {
        textUI.exit();

        assertTrue(out.toString().contains("Hei hei!"));
    }


    @Test
    public void TextUIAddBook() {
        String data = "k\n\nTitle\nAuthor\n666\n\n\nv\nq\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        s = new Scanner(System.in);
        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao, mockDBTagDao);

        textUI.run();

        assertTrue(out.toString().contains("kirjan nimi"));
        s.close();
    }

    @Test
    public void TextUIAddBookNoPages() {
        String data = "Title\nAuthor\n\n\nv\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        s = new Scanner(System.in);
        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao, mockDBTagDao);

        textUI.addBook(s);

        assertTrue(out.toString().contains("kirjan nimi"));
        s.close();
    }
    
    @Test
    public void TextUIAddBookNoBookMark() {
        String data = "Title\nAuthor\n666\nm\n1\n\nv\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        s = new Scanner(System.in);
        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao, mockDBTagDao);

        textUI.addBook(s);

        assertTrue(out.toString().contains("kirjan nimi"));
        s.close();
    }

    @Test
    public void TextUIAddBookWithATag() {
        String data = "Title\nAuthor\n666\nm\n1\nt\nwriting\n\nv\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        s = new Scanner(System.in);
        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao, mockDBTagDao);

        textUI.addBook(s);

        assertTrue(out.toString().contains("kirjan nimi"));
        s.close();
    }


    @Test
    public void TextUIAddArticle() {
        String data = "a\n\nTitle\nhttp://www.helsinki.fi/\n\nv\nq\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        s = new Scanner(System.in);
        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao, mockDBTagDao);

        textUI.run();

        assertTrue(out.toString().contains("artikkelin otsikko"));
        s.close();
    }
    
    @Test
    public void TextUIAddArticleWithInvalidURL() {
        String data = "a\n\nTitle\nhelsinki.fi/\n\n\nv\nq\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        s = new Scanner(System.in);
        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao, mockDBTagDao);

        textUI.run();

        assertTrue(out.toString().contains("URL-osoite oli virheellinen"));
        s.close();
    }

    @Test
    public void TextUIAddArticleWithTag() {
        String data = "a\n\nTitle\nhttp://www.helsinki.fi/\nt\nwriting\n\nv\nq\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        s = new Scanner(System.in);
        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao, mockDBTagDao);

        textUI.run();

        assertTrue(out.toString().contains("artikkelin otsikko"));
        s.close();
    }

    @Test
    public void TextUIRunAndHelp() {
        String data = "h\nq\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        s = new Scanner(System.in);

        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao, mockDBTagDao);

        textUI.run();

        assertTrue(out.toString().contains("Käytettävissä"));
    }

    @Test
    public void TextUIRunAndExit() {
        String data = "q\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        s = new Scanner(System.in);
        
        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao, mockDBTagDao);

        textUI.run();

        assertTrue(out.toString().contains("Hei hei!"));        
    }

    @Test
    public void TextUIInvalidCommand() {
        String data = "aku\nq\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        s = new Scanner(System.in);

        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao, mockDBTagDao);

        textUI.run();

        assertTrue(out.toString().contains("komentoa ei tunnistettu"));        
    }


    @Test
    public void TextUIFindBook() {
        String data = "e\nk\nakuankka\n\nv\nq\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        s = new Scanner(System.in);

        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao, mockDBTagDao);

        textUI.run();

        assertTrue(out.toString().contains("Löydetyt lukuvinkit"));  
    }

    @Test
    public void TextUIFindArticle() {
        String data = "e\na\nakuankka\n\nv\nq\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        s = new Scanner(System.in);

        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao, mockDBTagDao);

        textUI.run();

        assertTrue(out.toString().contains("Löydetyt lukuvinkit"));  
    }

    @Test
    public void TextUIFindArticleOrBook() {
        String data = "e\nak\nakuankka\n\nv\nq\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        s = new Scanner(System.in);

        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao, mockDBTagDao);

        textUI.run();

        assertTrue(out.toString().contains("Löydetyt lukuvinkit"));  
    }

    @Test
    public void TextUIFindTag() {
        String data = "e\nt\nakuankka\n\nv\nq\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        s = new Scanner(System.in);

        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao, mockDBTagDao);

        textUI.run();

        assertTrue(out.toString().contains("Löydetyt lukuvinkit"));  
    }

    @Test
    public void TextUIDeleteBook() {
        String data = "p\nk\nTitle\nAuthor\nv\nq\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        s = new Scanner(System.in);

        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao, mockDBTagDao);

        textUI.run();

        assertTrue(out.toString().contains("Lukuvinkki poistettu!"));  
    }

    @Test
    public void TextUIDeleteArticle() {
        String data = "p\na\nTitle\nv\nq\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        s = new Scanner(System.in);

        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao, mockDBTagDao);

        textUI.run();

        assertTrue(out.toString().contains("Lukuvinkki poistettu!"));  
    }

    @Test
    public void TextUIAddBookmark() {
        String data = "222\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        s = new Scanner(System.in);
        
        Book b = new Book("Title", "Author", "666", "",1);
        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao, mockDBTagDao);
        textUI.addBookmark(b);
        assertTrue(out.toString().contains("kirjanmerkin sivunumero"));  
    }

    @Test
    public void TextUIUpdateBookmark() {
        ArrayList<Book> testBooks = new ArrayList<>();
        Book testBook = new Book ("Title", "Author", "666", "222", 1); 
        testBooks.add(testBook);

        String data = "m\n1\n444\nq\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        s = new Scanner(System.in);
        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao, mockDBTagDao);
        textUI.setBooks(testBooks);

        textUI.run();

        assertTrue(out.toString().contains("kaikki kirjavinkit:"));
        s.close();

    }

    @Test
    public void TextUIListBooks() {
        String data = "l\nq\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        s = new Scanner(System.in);
        
        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao, mockDBTagDao);
        textUI.run();
    }    

    @Test
    public void TextUIAddTag() {
        String data = "l\nq\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        s = new Scanner(System.in);

        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao, mockDBTagDao);
        textUI.addTag(s);

        assertTrue(out.toString().contains("anna uusi tag"));
    }    
    
    
}
