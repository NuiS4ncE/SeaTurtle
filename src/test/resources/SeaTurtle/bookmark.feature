Feature: As a user I want to add bookmarks to my books

  Scenario: If book is 100/200 read, 50 per cent is left
    Given Book list is initialized
    When a book with title "Test Book" and "200" pages is created
    And a bookmark for "Test Book" on page "100" is added
    Then 50 per cent of the book is read

  Scenario: If book is all read, reading percentage is green
    Given Book list is initialized
    When a book with title "Test Book" and "200" pages is created
    And a bookmark for "Test Book" on page "200" is added
    Then read percentage is colored green

  Scenario: If book is partly read, reading percentage is yellow
    Given Book list is initialized
    When a book with title "Test Book" and "200" pages is created
    And a bookmark for "Test Book" on page "100" is added
    Then read percentage is colored yellow

  Scenario: If book is not started to read, reading percentage is red
    Given Book list is initialized
    When a book with title "Test Book" and "200" pages is created
    Then read percentage is colored red