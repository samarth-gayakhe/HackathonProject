Feature: Gift Card Purchase Flow

  Scenario: Verify invalid email message on gift card purchase
    Given user has launched the browser and navigated to "https://www.amazon.in/"
    When user searches "amazon gift card"
    And Clicks on the first result
    And selects happy birthday
    And clicks on send as email
    And enter invalid email "test@invalid" and sender name "John Doe"
    Then User should see an error message
    And Close the browser