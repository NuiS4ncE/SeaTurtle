Feature: As a user I want to be able to add a book to my list

  Scenario: Add one book
    Given Book list is initialized
    When a book title "Test Book" is created
    Then the number of books should be 1

  Scenario: Add four books
    Given Book list is initialized
    When a book title "Test Book" is created
    And a book title "Another Test Book" is created
    And a book title "The Real Test Book" is created
    And a book title "The Only Test Book" is created
    Then the number of books should be 4
