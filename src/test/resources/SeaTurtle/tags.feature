@DatabaseNeeded
Feature: As a user I want to add and remove tags to/from books and articles

  Scenario: After adding tag to it, a book is listed when searching that tag
    Given Book list is initialized
    When a book with title "Test Book" is created
    And a book with title "Another Test Book" is created
    And a tag "test" is added to the book with title "Another Test Book"
    Then searching tag "test" returns 1 results