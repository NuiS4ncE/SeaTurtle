package SeaTurtle.ui;

import SeaTurtle.Book;
import SeaTurtle.dao.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;   

public class TextUI {

    private DBBookDao bookDao;
    private ArrayList<Book> books;
    private Scanner s;

    public TextUI(Scanner s, DBBookDao bookDao) {
        this.bookDao = bookDao;
        updateBooks();
        this.s = s;
    }
    
    public void run() {

        while(true) {
            this.help();

            System.out.print("> ");
            String input = s.nextLine();

            switch (input) {
                case "k":
                    this.addBook(s);
                    break;
                case "h":
                    this.help();
                    break;
                case "q":
                    this.exit();
                    return;
                default:
                    System.out.println(ConsoleColors.RED + "komentoa ei tunnistettu." + ConsoleColors.RESET); 
                    this.help();
                    break;
            }
        }
    }

    public void addBook(Scanner s) {
        
        System.out.println("kirjan nimi: ");
        String title = s.nextLine();
        while(title.isEmpty()) {
            System.out.println("anna kirjan nimi:");
            title = s.nextLine();
        }
        Book newBook = new Book(title);       

        System.out.println("kirjan kirjoittaja: ");
        String author = s.nextLine();
        if (!author.isEmpty()) {
            newBook.setAuthor(author);
        }
        
        while (true) {
            System.out.println("kirjan sivumäärä: ");
            String pageCount = s.nextLine();
            if(pageCount.isEmpty()) {
                break;
            } else if(pageCount.matches("\\d+")) {
                newBook.setPageCount(pageCount);
                break;
            }
            System.out.println("anna sivumäärä numerona tai paina enter, jos haluat jättää kentän tyhjäksi");
        }

        try {
            bookDao.create(newBook);
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        updateBooks();
//        dbService.createBook(newBook); 

        System.out.println(ConsoleColors.GREEN +  "kirjavinkki lisätty" + ConsoleColors.RESET);
        
        System.out.println("");
        System.out.println("Kaikki kirjavinkit:");
        Collections.sort(books);
        for(Book book : books) {
            System.out.println(book.toString());
        }
        System.out.println("");
        
        while(true) {
            System.out.println("[k] lisää uusi kirjavinkki\n"
            + "[v] palaa valikkoon\n");
            String choice = s.nextLine();
            if (choice.equals("k")) {
                addBook(s);
                break;
            } 
            
            else if (choice.equals("v")) {
                return;
            }   
        }        
    }

    public void updateBooks() {
        try {
            books = bookDao.list();
        } catch (SQLException e) {
            System.out.println(e);
        }
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
        return;
    }

}
