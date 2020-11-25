Feature: As a user I want to be able to add a book to my list

  Scenario: Add one book
    Given Book list is initialized
    When a book with title "Test Book" is created
    Then the number of books should be 1

  Scenario: Add four books
    Given Book list is initialized
    When a book with title "Test Book" is created
    And a book with title "Another Test Book" is created
    And a book with title "The Real Test Book" is created
    And a book with title "The Only Test Book" is created
    Then the number of books should be 4

  Scenario: Add book with only title
    Given Book list is initialized
    When a book with title "Test Book" is created
    Then only book's title "Test Book" is returned

  Scenario: Add book with title and author
    Given Book list is initialized
    When a book with title "Test Book" and author "Author Writer" is created
    Then book's title "Test Book" and author "Author Writer" is returned

  Scenario: Add book with title and page count
    Given Book list is initialized
    When a book with title "Test Book" and "200" pages is created
    Then book's title "Test Book" and page count of "200" is returned

  Scenario: Add book with all details
    Given Book list is initialized
    When a book with title "Test Book", author "Author Writer" and "200" pages is created
    Then book's title "Test Book", author "Author Writer" and page count of "200" is returned