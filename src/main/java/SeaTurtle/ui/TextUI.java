package SeaTurtle.ui;

import SeaTurtle.model.Article;
import SeaTurtle.model.Book;
import SeaTurtle.model.Tag;
import SeaTurtle.dao.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.validator.UrlValidator;

public class TextUI {

    private BookDao bookDao;
    private ArticleDao articleDao;
    private TagDao tagDao;
    private ArrayList<Book> books;
    private ArrayList<Article> articles;
    private Scanner s;

    public TextUI(Scanner s, BookDao bookDao, ArticleDao articleDao, TagDao tagDao) {
        this.bookDao = bookDao;
        this.articleDao = articleDao;
        this.tagDao = tagDao;
        this.s = s;

        updateBooks();
        updateArticles();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public void run() {

        while (true) {
            this.help();

            System.out.println("Anna komento:");
            String input = s.nextLine();

            switch (input) {
                case "k":
                    this.addBook(s);
                    break;
                case "a":
                    this.addArticle(s);
                    break;
                case "m":
                    this.updateBookmark(s);
                    break;
                case "l":
                    this.listBooks();
                    this.listArticles();
                    break;
                case "e":
                    this.find(s);
                    break;
                case "p":
                    this.deleteSubMenu(s);
                    break;
                case "h":
                    this.help();
                    break;
                case "q":
                    this.exit();
                    return;
                case "t":
                    this.editTags(s);
                    break;
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
        while (title.trim().isEmpty()) {
            System.out.println("anna kirjan nimi:");
            title = s.nextLine();
        }
        Book newBook = new Book(title);

        System.out.println("kirjan kirjoittaja: ");
        String author = s.nextLine();
        if (!author.trim().isEmpty()) {
            newBook.setAuthor(author);
        }

        while (true) {
            System.out.println("kirjan sivumäärä: ");
            String pageCount = s.nextLine();
            if (pageCount.isEmpty()) {
                break;
            } 
            else if (pageCount.matches("\\d+") && Integer.parseInt(pageCount) >= 0) {
                newBook.setPageCount(pageCount);
                System.out.println(
                        "paina [m] jos haluat lisätä kirjaan kirjanmerkin tai mitä tahansa muuta näppäintä tallentaaksesi kirjavinkin");
                String addBookmark = s.nextLine();
                if (addBookmark.equals("m")) {
                    addBookmark(newBook);
                }
                break;
            }
            System.out.println(ConsoleColors.RED + "anna sivumäärä positiivisena numerona tai paina enter, jos haluat jättää kentän tyhjäksi" + ConsoleColors.RESET);
        }

        String newBookId = "";
        try {
            newBookId = bookDao.create(newBook);
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        updateBooks();
        
        while (true) {
            System.out.println("paina [t] jos haluat lisätä tageja tai enter, jos et halua");
            String addTag = s.nextLine();
            if (addTag.isEmpty()) {
                break;
            }
            else if (addTag.equals("t")) {
                System.out.println("anna tagi:");
                String tag = s.nextLine();
                try {
                    tagDao.create(new Tag("BOOK", tag, newBookId));
                } catch (SQLException e) {
                    System.err.println(e);
                }
            }
        }

        System.out.println(ConsoleColors.GREEN + "kirjavinkki lisätty" + ConsoleColors.RESET);

        listBooks();

        while (true) {
            System.out.println("[k] lisää uusi kirjavinkki\n" + "[v] palaa valikkoon\n");
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

        if (books.isEmpty()) {
            System.out.println("ei kirjavinkkejä");
            System.out.println("");
            return false;
        } else {
            for (Book book : books) {
                System.out.println((books.indexOf(book) + 1) + ") " + book.toString());
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
                System.out.println(
                    "anna sen kirjan numero, jolle haluat asettaa kirjanmerkin, tai paina enter palataksesi valikkoon: ");
                String selectBook = s.nextLine();
                if (selectBook.isEmpty()) {
                    break;
                } else if (selectBook.matches("\\d+")) {
                    int bookIndex = Integer.parseInt(selectBook);
                    if (bookIndex <= books.size() + 1 && bookIndex > 0) {
                        Book book = books.get(bookIndex - 1);
                        if (book.getPageCount() != null) {
                            addBookmark(book);
                            try {
                                bookDao.updateBookmark(book);
                            } catch (SQLException e) {
                                System.out.print(e.getMessage());
                            }
                            updateBooks();
                            listBooks();
                            break;
                        } else {
                            System.out.println(ConsoleColors.RED + "kirjalla ei ole sivumäärää, joten kirjanmerkkiä ei voida lisätä" + ConsoleColors.RESET);
                            System.out.println("");
                        }
                    } else {
                        System.out.println(ConsoleColors.RED + "väärä kirjan numero" + ConsoleColors.RESET);
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
            if (bookmark.isEmpty()) {
                break;
            } else if (bookmark.matches("\\d+")
                    && Integer.parseInt(bookmark) <= Integer.parseInt(book.getPageCount())
                    && Integer.parseInt(bookmark) >= 0) {
                book.setBookmark(bookmark);
                break;
            }
            System.out.println(ConsoleColors.RED +
                    "sivunumero ei saa olla negatiivinen tai sivumäärää suurempi. paina enter, jos haluat jättää kentän tyhjäksi."
                    + ConsoleColors.RESET);
        }
    }

    public void addArticle(Scanner s) {

        System.out.println("artikkelin otsikko: ");
        String title = s.nextLine();
        while (title.trim().isEmpty()) {
            System.out.println("anna artikkelin otsikko: ");
            title = s.nextLine();
        }
        Article newArticle = new Article(title);

        while (true) {
            System.out.println("artikkelin URL-osoite (tai tyhjä): ");
            String url = s.nextLine();
            String[] schemes = {"http","https"};
            UrlValidator urlValidator = new UrlValidator(schemes);
            if (url.trim().isEmpty()) {
                break;
            } else if (urlValidator.isValid(url.trim())) {
                newArticle.setUrl(url);
                break;
            }
            System.out.println(ConsoleColors.RED + "URL-osoite oli virheellinen. anna oikeanmuotoinen URL-osoite (esim. https://www.hs.fi). paina enter, jos haluat jättää kentän tyhjäksi." + ConsoleColors.RESET);
        }

        try {
            articleDao.create(newArticle);
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        updateArticles();
        
        while (true) {
            System.out.println("paina [t] jos haluat lisätä tageja tai enter, jos et halua");
            String addTag = s.nextLine();
            if (addTag.isEmpty()) {
                break;
            }
            else if (addTag.equals("t")) {
                System.out.println("anna tagi:");
                String tag = s.nextLine();
                String articleId = Integer.toString(articles.size());
                try {
                    tagDao.create(new Tag("ARTICLE", tag, articleId));
                } catch (SQLException e) {
                    System.err.println(e);
                }
            }
        }

        System.out.println(ConsoleColors.GREEN + "artikkelivinkki lisätty" + ConsoleColors.RESET);

        listArticles();

        while (true) {
            System.out.println("[a] lisää uusi artikkelivinkki\n" + "[v] palaa valikkoon\n");
            String choice = s.nextLine();
            if (choice.equals("a")) {
                addArticle(s);
                break;
            }

            else if (choice.equals("v")) {
                return;
            }
        }
    }

    public boolean listArticles() {
        System.out.println("");
        System.out.println("kaikki artikkelivinkit:");
        Collections.sort(articles);

        if (articles.isEmpty()) {
            System.out.println("ei artikkelivinkkejä");
            System.out.println("");
            return false;
        } else {
            for (Article article : articles) {
                System.out.println((articles.indexOf(article) + 1) + ") " + article.toString());
            }
        }
        System.out.println("");
        return true;
    }

    public void updateArticles() {
        try {
            articles = articleDao.list();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void find(Scanner s) {
        ArrayList<Book> bookSearchResults = new ArrayList<>();
        ArrayList<Article> articleSearchResults = new ArrayList<>();
        ArrayList<Tag> tagSearchResults = new ArrayList<>();

        while (true) {
            System.out.println("\n[k] hae vain kirjavinkeistä\n" 
            + "[a] hae vain artikkelivinkeistä\n"
            + "[ak] hae kaikista vinkeistä\n"
            + "[t] hae tageista\n"
            + "[v] poistu valikkoon\n");
            String searchArea = s.nextLine();
            
            if (searchArea.equals("v")) {
                return;
            }

            while (true) {
                System.out.println("anna hakutermi (tyhjällä takaisin edelliseen näkymään):");
                String searchTerm = s.nextLine();

                if (searchTerm.equals(""))
                    break;

                if (searchArea.equals("t")) {
                    try {
                        tagSearchResults = tagDao.findAndList(searchTerm);
                        ArrayList<Integer> bookIds = tagDao.findBookIdsByTag(searchTerm);
                        System.out.println("Löydetyt lukuvinkit:");
                        int i = 0;
                        for (int bookId : bookIds) {
                            System.out.println("Tagi: " + tagSearchResults.get(i) + ". " + bookDao.findBookById(bookId));
                            i++;
                        }
                        tagSearchResults.clear();
                        bookIds.clear();
                    } catch (SQLException e) {
                        System.err.println(e);
                    }
                }

                if (searchArea.contains("k")) {
                    try {
                        bookSearchResults = bookDao.findAndList(searchTerm);
                        System.out.println("Löydetyt lukuvinkit:");
                        bookSearchResults.forEach(System.out::println);
                        bookSearchResults.clear();
                    } catch (SQLException e) {
                        System.err.println(e);
                    }
                }
                
                if (searchArea.contains("a")) {
                    try {
                        articleSearchResults = articleDao.findAndList(searchTerm);
                        System.out.println("Löydetyt lukuvinkit:");
                        articleSearchResults.forEach(System.out::println);
                        articleSearchResults.clear();
                    } catch (SQLException e) {
                        System.err.println(e);
                    }
                }
                
                System.out.println();

            }
        }
    }

    public void editTags(Scanner s) {

        ArrayList<Book> books;
        try{
            books = bookDao.list();

            while(true) {
                books.forEach(b -> { System.out.println("ID: " + b.getId() +", " + b); });
                System.out.println("\nAnna kirjan ID, jonka tietoja haluat muokata (tyhjällä pois)");
                String id = s.nextLine();
    
                if (id.isEmpty()) {
                    return;
                } else {
                    int bookId = Integer.parseInt(id);

                    ArrayList<Tag> tags = tagDao.findTagsByBookId(bookId);
                    System.out.println("\nKirjan tagit:");
                    tags.forEach(System.out::println);

                    System.out.println("[l] lisää tag, [p] poista tag (tyhjällä pois)");
                    String input = s.nextLine();
                    
                    if (input.equals("l")) {
                        appendTag(s, "BOOK", bookId);
                    } else if (input.equals("p")) {
                        removeTag(s, bookId);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("TextUI:editTags():" + e);
        }
    }

    public void removeTag(Scanner s, int id) {
        ArrayList<Tag> tags = null;
        while(true) {
            System.out.println("anna poistettavan tagin id (tyhjällä pois)");
            String input = s.nextLine();

            if (input.isEmpty()) {
                return;
            }

            try {
                tagDao.deleteById(Integer.parseInt(input));
                tags = tagDao.findTagsByBookId(id);
            } catch (SQLException e) {
                System.out.println(ConsoleColors.RED + "Huono ID" + ConsoleColors.RESET);
            }

            if(!tags.isEmpty()) {
                System.out.println();
                tags.forEach(System.out::println);
            } else {
                System.out.println("Kirjalla ei ole enää tageja");
                return;
            }
        }
    }

    public void appendTag(Scanner s, String type, int id) {
        while(true) {
            System.out.println("anna lisättävä tag (tyhjällä pois)");
            String input = s.nextLine();

            if(input.isEmpty()) {
                return;
            }

            try {
                tagDao.create(new Tag(type, input, String.valueOf(id)));
            } catch (SQLException e) {
                System.out.println(e);
            }
            
            ArrayList<Tag> tags = null;
            try {
                System.out.println();
                System.out.println(ConsoleColors.GREEN + "tagi lisätty" + ConsoleColors.RESET);
                tags = tagDao.findTagsByBookId(id);
                System.out.println();
                System.out.println("Kirjan tagit:");
                tags.forEach(System.out::println);
            } catch (SQLException e) {
                System.out.println(e);
            }
   
        }
    }




    public void deleteSubMenu(Scanner s) {

        while(true){
            this.updateArticles();
            this.updateBooks();
            this.listBooks();
            this.listArticles();

            System.out.println("\n[k] poista kirjavinkki\n"
                    + "[a] poista artikkelivinkki\n"
                    + "[v] poistu valikkoon\n");
            String searchArea = s.nextLine();

            if (searchArea.equals("v")) {
                return;
            }

            while (true) {

                if (searchArea.contains("k")) {
                    System.out.println("anna poistettavan kirjan nimi (tyhjällä takaisin edelliseen näkymään):");
                    String deleteTitle = s.nextLine();
                    if (deleteTitle.equals("")) break;

                    System.out.println("ja kirjoittajan nimi (tyhjällä takaisin edelliseen näkymään)");
                    String deleteAuthor = s.nextLine();
                    if (deleteAuthor.equals("")) break;

                    try {
                        bookDao.delete(deleteTitle, deleteAuthor);
                    } catch (SQLException e) {
                        System.err.println(e);
                    }

                }

                if (searchArea.contains("a")) {
                    System.out.println("anna poistettavan artikkelin nimi (tyhjällä takaisin edelliseen näkymään):");
                    String deleteArticle = s.nextLine();
                    if (deleteArticle.equals("")) break;

                    try {
                        articleDao.delete(deleteArticle);
                    } catch (SQLException e) {
                        System.err.println(e);
                    }
                }
                System.out.println("Lukuvinkki poistettu!");
                break;
            }

        }
    }


    public void help() {
        System.out.println("\n" + "Käytettävissä olevat komennot:\n" 
        + "[k] lisää uusi kirjavinkki\n"
        + "[a] lisää uusi artikkelivinkki\n" 
        + "[m] lisää tai päivitä kirjanmerkki\n"
        + "[l] listaa kaikki lukuvinkit\n" 
        + "[e] etsi lukuvinkki\n"
        + "[p] poista lukuvinkki\n"
        + "[t] muokkaa tageja\n"
        + "---\n" 
        + "[h] listaa komennot\n"
        + "[q] poistu ohjelmasta\n");
    }

    public void exit() {
        System.out.println("\nHei hei!\n");
        return;
    }

}
