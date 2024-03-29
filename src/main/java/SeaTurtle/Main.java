package SeaTurtle;

import java.util.Scanner;

import SeaTurtle.dao.*;
import SeaTurtle.ui.TextUI;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello SeaTurtle!\n");
         
        BookDao bookDao = new DBBookDao();
        ArticleDao articleDao = new DBArticleDao();
        TagDao tagDao = new DBTagDao();
        Scanner s  = new Scanner(System.in);

        TextUI UI = new TextUI(s, bookDao, articleDao, tagDao);
        UI.run();
        
        s.close();
        System.out.println("Goodbye SeaTurtle!");
    }
}
