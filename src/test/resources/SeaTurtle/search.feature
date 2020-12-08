Feature: As a user I want to be able to search books and articles

  Scenario: Search lists all books and articles where name has specified word
    Given Book list is initialized
    And Article list is initialized
    When a book with title "Test Book" is created
    When an article with title "Test Article" is created
    Then searching "Test" returns 2 results