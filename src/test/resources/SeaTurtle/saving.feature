Feature: As a user I want my books and articles to be saved permanently

  Scenario: Book is saved into database
    Given Book list is initialized
    When a book with title "Test Book" is created
    And a book with title "Another Test Book" is created
    Then all added books are in the database

  Scenario: Article is saved into database
    Given Article list is initialized
    When an article with title "Test Article" is created
    And an article with title "Another Test Article" is created
    Then all added articles are in the database