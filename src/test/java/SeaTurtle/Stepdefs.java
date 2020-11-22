
package SeaTurtle;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

// Book ei ollut vielä käytössä, joten Book-oli toistaiseksi String.
// Korvataan Book'illa, kun käytössä.

public class Stepdefs {
    List<String> testbooks;
    
    @Given("Book list is initialized")
    public void bookListIsInitialized() {
        testbooks = new ArrayList<>();
    }

    @When("a book title {string} is created")
    public void bookIsCreatedWithTitle(String title) {
        String book = new String(title);
        testbooks.add(book);
    }

    @Then("the number of books should be {int}")
    public void numberOfBooksShouldBe(Integer val) {
        assertEquals(val.intValue(), testbooks.size());
    }    

}