Feature: Chair Search

  Scenario: Search for chairs and save top results
    Given user has launched the browser and navigated to "https://www.amazon.in/"
    When user searches "chairs"
    Then save the top 3 chair results in excel sheet
    And Close the browser