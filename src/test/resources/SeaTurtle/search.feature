@DatabaseNeeded
Feature: As a user I want to be able to search books and articles

  Scenario: Search lists all books and articles where name has specified word
    Given Book list is initialized
    And Article list is initialized
    When a book with title "Test Book" is created
    And an article with title "Test Article" is created
    And an article with title "Another Test Article" is created
    Then searching "Test" returns 3 results

  Scenario: Search lists only books and articles where name has specified word
    Given Book list is initialized
    And Article list is initialized
    When a book with title "Real Book" is created
    And an article with title "Test Article" is created
    And an article with title "Real Article" is created
    Then searching "Test" returns 1 results