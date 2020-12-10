@DatabaseNeeded
Feature: As a user I want to be able to search from books or articles only

  Scenario: Search lists only books where name has specified word
    Given Book list is initialized
    And Article list is initialized
    When a book with title "Test Book" is created
    And an article with title "Test Article" is created
    And an article with title "Another Test Article" is created
    Then searching books with title "Test" returns 1 results

  Scenario: Search lists only  articles where name has specified word
    Given Book list is initialized
    And Article list is initialized
    When a book with title "Test Book" is created
    And an article with title "Test Article" is created
    And an article with title "Another Test Article" is created
    Then searching articles with title "Test" returns 2 results
