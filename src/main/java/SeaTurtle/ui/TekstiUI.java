package seaturtle.ui;

import java.util.Scanner;   

public class TekstiUI {

    public TekstiUI() {
        
    }
    
    
    
    public void run(Scanner s) {
        this.help();
        while(true) {

            String input = s.nextLine();
            if (input.equals("exit"))
                System.exit(0);

        }
    }

    public void help() {
        System.out.println("Käytettävissä olevat komennot:\n" 
        + "[L]isää uusi lukuvinkki\n"
        + "---\n"
        + "[help] listaa käytettävät komennot\n"
        + "[exit] poistu ohjelmasta\n"
        + "---\n"
        );


    }


}
