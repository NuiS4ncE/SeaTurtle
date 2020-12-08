Feature: User input is validated

  Scenario: Book with negative page number is not added
    Given Book list is initialized
    When a book with title "Test Book" is created
    Then adding page count "-1" throws exception

  Scenario: Article with invalid URL not added
    Given Article list is initialized
    When an article with title "Test Article" is created
    Then adding invalid URL throws exception