@DatabaseNeeded
Feature: As a user I want to add and remove tags to/from books

  Scenario: After adding tag to it, a book is listed when searching that tag
    Given Book list is initialized
    When a book with title "Test Book" is created
    And a book with title "Another Test Book" is created
    And a tag "test" is added to the book with title "Another Test Book"
    Then searching tag "test" returns 1 results

  Scenario: After adding and removing same tag, no results are returned
    Given Book list is initialized
    When a book with title "Another Test Book" is created
    And a tag "test" is added to the book with title "Another Test Book"
    And the tag "test" is deleted
    Then searching tag "test" returns 0 results

  Scenario: After adding multiple tags to book, all tags are returned in search
    Given Book list is initialized
    When a book with title "Test Book" is created
    And a tag "first" is added to the book with title "Test Book"
    And a tag "second" is added to the book with title "Test Book"
    And a tag "third" is added to the book with title "Test Book"
    Then searching tags for book "Test Book" returns 3 results