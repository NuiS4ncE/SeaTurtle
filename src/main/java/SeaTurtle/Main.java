package SeaTurtle;

import SeaTurtle.dao.DBBookDao;
import SeaTurtle.database.DBService;
import SeaTurtle.ui.TextUI;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello SeaTurtle!\n");
        
        DBBookDao bookDao = new DBBookDao();
        DBService dbService = new DBService(bookDao);
        
        TextUI UI = new TextUI(dbService);
        UI.run();
        
    }
}
