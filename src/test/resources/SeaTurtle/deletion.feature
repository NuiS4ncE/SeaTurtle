@DatabaseNeeded
Feature: As a user I want to be able to delete a book or an article

  Scenario: Book can be deleted
    Given Book list is initialized
    When a book with title "Test Book" and author "Test Writer" is created
    And a book with title "Another Test Book" and author "Another Writer" is created
    And a book with title "Test Book" and author "Test Writer" is deleted
    Then the number of books should be 1

  Scenario: Article can be deleted
    Given Article list is initialized
    When an article with title "Test Article" is created
    And an article with title "Another Test Article" is created
    And an article with title "Test Article" is deleted
    Then the number of articles should be 1