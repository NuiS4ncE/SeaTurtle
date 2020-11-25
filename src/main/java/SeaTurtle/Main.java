package SeaTurtle;

import SeaTurtle.dao.DBBookDao;
import SeaTurtle.ui.TextUI;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello SeaTurtle!\n");
        
        DBBookDao bookDao = new DBBookDao();
        
        TextUI UI = new TextUI(bookDao);
        UI.run();
        
    }
}
