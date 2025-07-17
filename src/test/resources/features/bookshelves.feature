Feature: Bookshelves Search and Filter

  Scenario: Filter bookshelves by price and out of stock
    Given user has launched the browser and navigated to "https://www.amazon.in/"
    When user searches "open bookshelves"
    And filter the results with price less than "15000" and include out of stock
    Then save the top 3 results in excel sheet