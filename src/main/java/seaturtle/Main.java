package seaturtle;

import seaturtle.ui.TekstiUI;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello SeaTurtle!");
        
        TekstiUI UI = new TekstiUI();
        Scanner s = new Scanner(System.in);        
        UI.run(s);
        
        
    }
}
