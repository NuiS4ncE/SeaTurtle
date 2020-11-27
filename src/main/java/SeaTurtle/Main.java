package SeaTurtle;

import java.util.Scanner;

import SeaTurtle.dao.DBBookDao;
import SeaTurtle.ui.TextUI;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello SeaTurtle!\n");
         
        DBBookDao bookDao = new DBBookDao();
        Scanner s  = new Scanner(System.in);

        TextUI UI = new TextUI(s, bookDao);
        UI.run();
        
        s.close();
    }
}
