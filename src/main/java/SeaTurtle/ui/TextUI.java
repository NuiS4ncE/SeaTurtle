package SeaTurtle.ui;

import java.util.Scanner;   

public class TextUI {

    public TextUI() {
        
    }

    
    public void run(Scanner s) {
        this.help();
        while(true) {

            System.out.print("> ");
            String input = s.nextLine();

            switch (input) {
                case "exit":
                    System.exit(0);
                    break;
                case "help":
                    this.help();
                    break;
                    

                default:
                    System.out.println(ConsoleColors.RED + "komentoa ei tunnistettu." + ConsoleColors.RESET); 
                    this.help();
                    break;
            }



        }
    }

    public void help() {
        System.out.println("\n"
        + "Käytettävissä olevat komennot:\n" 
        + "[L]isää uusi lukuvinkki\n"
        + "---\n"
        + "[help] listaa käytettävät komennot\n"
        + "[exit] poistu ohjelmasta\n"
        );
    }


}
