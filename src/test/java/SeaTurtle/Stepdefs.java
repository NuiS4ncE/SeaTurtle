
package SeaTurtle;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class Stepdefs {
    List<Book> testbooks;
    
    @Given("Book list is initialized")
    public void bookListIsInitialized() {
        testbooks = new ArrayList<>();
    }

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

    @When("a book with title {string} and {int} pages is created")
    public void bookIsCreatedWithTitleAndPageCount(String title, int pageCount) {
        Book book = new Book(title);
        book.setPageCount(pageCount);
        testbooks.add(book);
    }

    @When("a book with title {string}, author {string} and {int} pages is created")
    public void bookIsCreatedWithAllDetails(String title, String author, int pageCount) {
        Book book = new Book(title, author, pageCount);
        testbooks.add(book);
    }

    @Then("the number of books should be {int}")
    public void numberOfBooksShouldBe(Integer val) {
        assertEquals(val.intValue(), testbooks.size());
    }

    @Then("only book's title {string} is returned")
    public void returnTitleAsString(String title) {
        assertEquals("Kirjan nimi: " + title + ".", testbooks.get(0).toString());
    }

    @Then("book's title {string} and author {string} is returned")
    public void returnTitleAndAuthorAsString(String title, String author) {
        assertEquals("Kirjan nimi: " + title + ". Kirjoittaja: " + author + ".", testbooks.get(0).toString());
    }

    @Then("book's title {string} and page count of {int} is returned")
    public void returnTitleAndPageCountAsString(String title, int pageCount) {
        assertEquals("Kirjan nimi: " + title + ". " + pageCount + " sivua.", testbooks.get(0).toString());
    }

    @Then("book's title {string}, author {string} and page count of {int} is returned")
    public void returnAllDetailsAsString(String title, String author, int pageCount) {
        assertEquals("Kirjan nimi: " + title + ". Kirjoittaja: " + author + ". " + pageCount + " sivua.", testbooks.get(0).toString());
    }

}