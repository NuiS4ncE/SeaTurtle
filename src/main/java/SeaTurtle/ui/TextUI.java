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
                case "k":
                    this.addBook();
                    break;
                case "h":
                    this.help();
                    break;
                    case "q":
                    this.exit();
                    break;
                default:
                    System.out.println(ConsoleColors.RED + "komentoa ei tunnistettu." + ConsoleColors.RESET); 
                    this.help();
                    break;
            }
        }
    }

    public void addBook() {
        System.out.println(ConsoleColors.GREEN +  "kirjavinkki lisätty" + ConsoleColors.RESET);
    }


    public void help() {
        System.out.println("\n"
        + "Käytettävissä olevat komennot:\n" 
        + "[k] lisää uusi kirjavinkki\n"
        + "---\n"
        + "[h] listaa komennot\n"
        + "[q] poistu ohjelmasta\n"
        );
    }

    public void exit() {
        System.out.println("\nHei hei!\n");
        System.exit(0);
    }

}
