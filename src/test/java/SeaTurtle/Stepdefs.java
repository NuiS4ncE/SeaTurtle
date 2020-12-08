
package SeaTurtle;

import SeaTurtle.dao.*;
import SeaTurtle.model.*;
import SeaTurtle.ui.ConsoleColors;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class Stepdefs {
    List<Book> testbooks;
    List<Article> testarticles;
    BookDao dbBookDao;
    ArticleDao dbArticleDao;
    
    private void databaseInitialized() throws SQLException {
        dbBookDao = new DBBookDao("jdbc:sqlite:seaturtletest_cucumber.db");
        dbArticleDao = new DBArticleDao("jdbc:sqlite:seaturtletest_cucumber.db");
        
        for (Book b : testbooks) {
            Book book = new Book(b.getTitle());
            if (b.getAuthor() != null) {
                book.setAuthor(b.getAuthor());
            }
            if (b.getPageCount() != null) {
                book.setPageCount(b.getPageCount());
            }
            dbBookDao.create(book);
        }
        
        for (Article a : testarticles) {
            Article article = new Article(a.getTitle());
            if (a.getUrl() != null) {
                article.setUrl(a.getUrl());
            }
            dbArticleDao.create(article);
        }
    }
    
    private void databaseDropped() throws SQLException {
        dbBookDao.dropTable();
        dbArticleDao.dropTable();
    }
    
    // GIVEN
    
    @Given("Book list is initialized")
    public void bookListIsInitialized() {
        testbooks = new ArrayList<>();
    }
    
    @Given("Article list is initialized")
    public void articleListIsInitialized() {
        testarticles = new ArrayList<>();
    }
    
    
    // WHEN

    @When("a book with title {string} is created")
    public void bookIsCreatedWithTitle(String title) {
        Book book = new Book(title);
        testbooks.add(book);
    }

    @When("a book with title {string} and author {string} is created")
    public void bookIsCreatedWithTitleAndAuthor(String title, String author) {
        Book book = new Book(title);
        book.setAuthor(author);
        testbooks.add(book);
    }

    @When("a book with title {string} and {string} pages is created")
    public void bookIsCreatedWithTitleAndPageCount(String title, String pageCount) {
        Book book = new Book(title);
        book.setPageCount(pageCount);
        testbooks.add(book);
    }
    
    @When("a book with title {string}, {string} pages and bookmark for page {string} is created")
    public void bookIsCreatedWithTitlePageCountAndBookmark(String title, String pageCount, String bookmark) {
        Book book = new Book(title);
        book.setPageCount(pageCount);
        book.setBookmark(bookmark);
        testbooks.add(book);
    }
    
    @When("a book with title {string}, author {string} and {string} pages is created")
    public void bookIsCreatedWithAuthorTitleAndPageCount(String title, String author, String pageCount) {
        Book book = new Book(title);
        book.setAuthor(author);
        book.setPageCount(pageCount);
        testbooks.add(book);
    }

    @When("a book with title {string}, author {string}, {string} pages and bookmark for page {string} is created")
    public void bookIsCreatedWithAllDetails(String title, String author, String pageCount, String bookmark) {
        Book book = new Book(title);
        book.setAuthor(author);
        book.setPageCount(pageCount);
        book.setBookmark(bookmark);
        testbooks.add(book);
    }

    @When("an article with title {string} is created")
    public void articleIsCreatedWithTitle(String title) {
        Article article = new Article(title);
        testarticles.add(article);
    }

    @When("an article with title {string} and URL {string} is created")
    public void articleIsCreatedWithAllDetails(String title, String url) {
        Article article = new Article(title);
        article.setUrl(url);
        testarticles.add(article);
    }
    
    
    // THEN

    @Then("the number of books should be {int}")
    public void numberOfBooksShouldBe(Integer val) {
        assertEquals(val.intValue(), testbooks.size());
    }

    @Then("the number of articles should be {int}")
    public void numberOfArticlesShouldBe(Integer val) {
        assertEquals(val.intValue(), testarticles.size());
    }

    @Then("only book's title {string} is returned")
    public void returnBookTitleAsString(String title) {
        assertEquals("Kirjan nimi: " + ConsoleColors.BLUE + title + ConsoleColors.RESET + ".", testbooks.get(0).toString());
    }

    @Then("book's title {string} and author {string} is returned")
    public void returnBookTitleAndAuthorAsString(String title, String author) {
        assertEquals("Kirjan nimi: " + ConsoleColors.BLUE + title + ConsoleColors.RESET + ". Kirjoittaja: " + author + ".", testbooks.get(0).toString());
    }

    @Then("book's title {string} and page count of {string} is returned")
    public void returnBookTitleAndPageCountAsString(String title, String pageCount) {
        assertEquals("Kirjan nimi: " + ConsoleColors.BLUE + title + ConsoleColors.RESET + ". " + pageCount + " sivua. Kirjasta luettu " + ConsoleColors.RED + "0 %" + ConsoleColors.RESET + ".", testbooks.get(0).toString());
    }
    
    @Then("book's title {string}, page count of {string} and bookmark for page {string} is returned")
    public void returnBookTitlePageCountAndBookmarkAsString(String title, String pageCount, String bookmark) {
        assertEquals("Kirjan nimi: " + ConsoleColors.BLUE + title + ConsoleColors.RESET + ". " + pageCount + " sivua. Kirjanmerkki sivulla " + bookmark + ". Kirjasta luettu " + ConsoleColors.YELLOW + (int) Math.round(Integer.parseInt(bookmark) * 100.0/Integer.parseInt(pageCount)) + " %" + ConsoleColors.RESET + ".", testbooks.get(0).toString());
    }

    @Then("book's title {string}, author {string} and page count of {string} is returned")
    public void returnBookAuthorTitleAndPageCountAsString(String title, String author, String pageCount) {
        assertEquals("Kirjan nimi: " + ConsoleColors.BLUE+ title + ConsoleColors.RESET + ". Kirjoittaja: " + author + ". " + pageCount + " sivua. Kirjasta luettu " + ConsoleColors.RED + "0 %" + ConsoleColors.RESET + ".", testbooks.get(0).toString());
    }
    
    @Then("book's title {string}, author {string}, page count of {string} and bookmark for page {string} is returned")
    public void returnBookAllDetailsAsString(String title, String author, String pageCount, String bookmark) {
        assertEquals("Kirjan nimi: " + ConsoleColors.BLUE + title + ConsoleColors.RESET + ". Kirjoittaja: " + author + ". " + pageCount + " sivua. Kirjanmerkki sivulla " + bookmark + ". Kirjasta luettu " + ConsoleColors.YELLOW + (int) Math.round(Integer.parseInt(bookmark) * 100.0/Integer.parseInt(pageCount)) + " %" + ConsoleColors.RESET + ".", testbooks.get(0).toString());
    }

    @Then("only article's title {string} is returned")
    public void returnArticleTitleAsString(String title) {
        assertEquals("Artikkeli: " + ConsoleColors.YELLOW + title + ConsoleColors.RESET + ".", testarticles.get(0).toString());
    }

    @Then("article's title {string} and URL {string} is returned")
    public void returnArticleAllDetailsAsString(String title, String url) {
        assertEquals("Artikkeli: " + ConsoleColors.YELLOW + title + ConsoleColors.RESET + ". <" + url + ">", testarticles.get(0).toString());
    }

    @Then("adding invalid URL throws exception")
    public void invalidURLThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Article failArticle = testarticles.get(0);
            failArticle.setUrl("www.fi");
        });
    }

    @Then("adding page count {string} throws exception")
    public void invalidPageCountThrowsException(String pageCount) {
        assertThrows(IllegalArgumentException.class, () -> {
            Book failBook = testbooks.get(0);
            failBook.setPageCount(pageCount);
        });
    }

    @Then("searching {string} returns {int} results")
    public void searchReturnsNumberOfResults(String search, int results) throws SQLException {
        databaseInitialized();
        
        ArrayList<Book> bookSearchResults = new ArrayList<>();
        bookSearchResults = dbBookDao.findAndList(search);
        ArrayList<Article> articleSearchResults = new ArrayList<>();
        articleSearchResults = dbArticleDao.findAndList(search);
        
        assertEquals(results, bookSearchResults.size() + articleSearchResults.size());
        
        databaseDropped();
    }

}