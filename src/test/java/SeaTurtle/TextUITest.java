package SeaTurtle;

import java.lang.System;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import SeaTurtle.dao.*;
import SeaTurtle.ui.*;

public class TextUITest {

    TextUI textUI;
    Scanner s;
    ByteArrayOutputStream out;

    @Mock
    BookDao mockDBBookDao = new DBBookDao();

    @Mock
    ArticleDao mockDBArticleDao = null;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        s = new Scanner(System.in);
        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao);
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
    public void TextUIRunAndAddBook() {
        String data = "k\n\nTitle\nAuthor\n\nv\nq\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        s = new Scanner(System.in);
        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao);

        textUI.run();

        assertTrue(out.toString().contains("kirjan nimi"));
        s.close();
    }
/*
    @Test
    public void TextUIAddBookTitleOnly() {
        String data = "\nAuthor\n\n3\nv\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        s = new Scanner(System.in);

        textUI = new TextUI(s, mockDBBookDao);
        
        textUI.addBook(s);

        assertTrue(out.toString().contains("kirjan nimi"));
    }

*/


    @Test
    public void TextUIRunAndHelp() {
        String data = "h\nq\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        s = new Scanner(System.in);

        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao);

        textUI.run();

        assertTrue(out.toString().contains("Käytettävissä"));
    }

    @Test
    public void TextUIRunAndExit() {
        String data = "q\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        s = new Scanner(System.in);
        
        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao);

        textUI.run();

        assertTrue(out.toString().contains("Hei hei!"));        
    }

    @Test
    public void TextUIRunAndInvalidCommand() {
        String data = "aku\nq\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        s = new Scanner(System.in);

        textUI = new TextUI(s, mockDBBookDao, mockDBArticleDao);

        textUI.run();

        assertTrue(out.toString().contains("komentoa ei tunnistettu"));        
    }



}
