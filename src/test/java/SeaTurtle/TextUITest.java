package SeaTurtle;

import java.util.Scanner;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import SeaTurtle.dao.DBBookDao;
import SeaTurtle.database.DBService;
import SeaTurtle.ui.*;

public class TextUITest {

    TextUI textUI;
    Scanner s;

    @Before
    public void setUp() {
        s = new Scanner(System.in);
        DBBookDao bookDao = new DBBookDao();
        textUI = new TextUI(new DBService(bookDao));
    }


    @Test
    public void textUICreateAndExit() {

    


    }


}
