Feature: Hamburger Menu Categories

  Scenario: Navigate through hamburger menu and list categories
    Given user has launched the browser and navigated to "https://www.amazon.in"
    And user clicks on All button on the navbar
    And scrolls down to shop by category and clicks on see all
    Then click on Home, Kitchen, Pets category
    And store the result of Home&Kitchen in excel and close the menu
    And Close the browser