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
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import SeaTurtle.dao.*;
import SeaTurtle.model.Book;
import SeaTurtle.ui.*;

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
        String data = "k\n\nTitle\nAuthor\n666\n\nv\nq\n";
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
        String data = "e\nakuankka\n\nq\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        s = new Scanner(System.in);

        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao, mockDBTagDao);

        textUI.run();

        assertTrue(out.toString().contains("Löydetyt lukuvinkit"));  
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
    public void TextUIListBooks() {
        String data = "l\nq\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        s = new Scanner(System.in);
        
        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao, mockDBTagDao);
        textUI.run();
    }    
    

    @Test
    public void TextUIUpdateBookmark() {
        String data = "\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        s = new Scanner(System.in);

        textUI = Mockito.spy(new TextUI(s, mockDBBookDao, mockDBArticleDao, mockDBTagDao));
        Mockito.doReturn(true).when(textUI).listBooks();

        textUI.updateBookmark(s);
    }

    


}
