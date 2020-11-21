package SeaTurtle;

import SeaTurtle.ui.TextUI;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello SeaTurtle!\n");
        
        TextUI UI = new TextUI();
        Scanner s = new Scanner(System.in);
        UI.run(s);
        
    }
}
