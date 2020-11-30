
package SeaTurtle;

import SeaTurtle.model.*;
import SeaTurtle.ui.ConsoleColors;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class Stepdefs {
    List<Book> testbooks;
    List<Article> testarticles;
    
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
        assertEquals("Kirjan nimi: " + title + ".", testbooks.get(0).toString());
    }

    @Then("book's title {string} and author {string} is returned")
    public void returnBookTitleAndAuthorAsString(String title, String author) {
        assertEquals("Kirjan nimi: " + title + ". Kirjoittaja: " + author + ".", testbooks.get(0).toString());
    }

    @Then("book's title {string} and page count of {string} is returned")
    public void returnBookTitleAndPageCountAsString(String title, String pageCount) {
        assertEquals("Kirjan nimi: " + title + ". " + pageCount + " sivua.", testbooks.get(0).toString());
    }
    
    @Then("book's title {string}, page count of {string} and bookmark for page {string} is returned")
    public void returnBookTitlePageCountAndBookmarkAsString(String title, String pageCount, String bookmark) {
        assertEquals("Kirjan nimi: " + title + ". " + pageCount + " sivua. Kirjanmerkki sivulla " + bookmark + ". Kirjasta luettu " + ConsoleColors.YELLOW + (int) Math.round(Integer.parseInt(bookmark) * 100.0/Integer.parseInt(pageCount)) + " %." + ConsoleColors.RESET, testbooks.get(0).toString());
    }

    @Then("book's title {string}, author {string} and page count of {string} is returned")
    public void returnBookAuthorTitleAndPageCountAsString(String title, String author, String pageCount) {
        assertEquals("Kirjan nimi: " + title + ". Kirjoittaja: " + author + ". " + pageCount + " sivua.", testbooks.get(0).toString());
    }
    
    @Then("book's title {string}, author {string}, page count of {string} and bookmark for page {string} is returned")
    public void returnBookAllDetailsAsString(String title, String author, String pageCount, String bookmark) {
        assertEquals("Kirjan nimi: " + title + ". Kirjoittaja: " + author + ". " + pageCount + " sivua. Kirjanmerkki sivulla " + bookmark + ". Kirjasta luettu " + ConsoleColors.YELLOW + (int) Math.round(Integer.parseInt(bookmark) * 100.0/Integer.parseInt(pageCount)) + " %." + ConsoleColors.RESET, testbooks.get(0).toString());
    }

    @Then("only article's title {string} is returned")
    public void returnArticleTitleAsString(String title) {
        assertEquals("Artikkeli: " + title + ".", testarticles.get(0).toString());
    }

    @Then("article's title {string} and URL {string} is returned")
    public void returnArticleAllDetailsAsString(String title, String url) {
        assertEquals("Artikkeli: " + title + ". <" + url + ">", testarticles.get(0).toString());
    }
    

}