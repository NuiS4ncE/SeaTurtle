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
                case "m":
                    this.updateBookmark(s);
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
                System.out.println("paina [m] jos haluat lisätä kirjaan kirjanmerkin tai mitä tahansa muuta näppäintä tallentaaksesi kirjavinkin");
                String addBookmark = s.nextLine();
                if (addBookmark.equals("m")) {
                    addBookmark(newBook);
                }
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
        //dbService.createBook(newBook); 

        System.out.println(ConsoleColors.GREEN +  "kirjavinkki lisätty" + ConsoleColors.RESET);
        
        listBooks();
        
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
    
    public boolean listBooks() {
        System.out.println("");
        System.out.println("kaikki kirjavinkit:");
        Collections.sort(books);
        if(books.isEmpty()) {
            System.out.println("ei kirjavinkkejä");
            return false;
        } else {
            for(Book book : books) {
                System.out.println(books.indexOf(book)+1 + ") " + book.toString());
            }
        }
        System.out.println("");
        return true;
    }

    public void updateBooks() {
        try {
            books = bookDao.list();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void updateBookmark(Scanner s) {
        if (listBooks()) {
            while (true) {
            System.out.println("anna sen kirjan numero, jolle haluat asettaa kirjanmerkin, tai paina enter palataksesi valikkoon: ");
            String selectBook = s.nextLine();
                if(selectBook.isEmpty()) {
                    break;
                } else if(selectBook.matches("\\d+")) {
                    int bookIndex = Integer.parseInt(selectBook);
                    if(bookIndex <= books.size()+1 && bookIndex > 0) {
                        Book book = books.get(bookIndex-1);
                        if(book.getPageCount() != null) {
                            addBookmark(book);
                            try {
                                bookDao.update(book);
                            } catch (SQLException e) {
                                System.out.print(e.getMessage());
                            }
                            updateBooks();
                            listBooks();
                            break;
                        } else {
                            System.out.println("kirjalla ei ole sivumäärää, joten kirjanmerkkiä ei voida lisätä");
                            System.out.println("");
                        }
                    } else {
                        System.out.println("väärä kirjan numero");
                        System.out.println("");
                    }
                }
            }
        }
    }
    
    public void addBookmark(Book book) {
        while (true) {
            System.out.println("kirjanmerkin sivunumero: ");
            String bookmark = s.nextLine();
            if(bookmark.isEmpty()) {
                 break;
            } else if(bookmark.matches("\\d+") && Integer.parseInt(bookmark) <= Integer.parseInt(book.getPageCount())) {
                book.setBookmark(bookmark);
                break;
            }
            System.out.println("sivunumero ei saa olla sivumäärää suurempi. paina enter, jos haluat jättää kentän tyhjäksi.");
        }
    }

    public void help() {
        System.out.println("\n"
        + "Käytettävissä olevat komennot:\n" 
        + "[k] lisää uusi kirjavinkki\n"
        + "[m] lisää tai päivitä kirjanmerkki\n"
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
