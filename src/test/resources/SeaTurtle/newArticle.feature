Feature: As a user I want to be able to add an article to my list

  Scenario: Add one article
    Given Article list is initialized
    When an article with title "Test Article" is created
    Then the number of articles should be 1

  Scenario: Add four articles
    Given Article list is initialized
    When an article with title "Test Article" is created
    And an article with title "Another Test Article" is created
    And an article with title "The Real Test Article" is created
    And an article with title "The Only Test Article" is created
    Then the number of articles should be 4

  Scenario: Add article with only title
    Given Article list is initialized
    When an article with title "Test Article" is created
    Then only article's title "Test Article" is returned

  Scenario: Add article with all details
    Given Article list is initialized
    When an article with title "Test Article" and URL "https://www.hs.fi/" is created
    Then article's title "Test Article" and URL "https://www.hs.fi/" is returned