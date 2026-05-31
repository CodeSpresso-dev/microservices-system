Feature: Create Card

  Scenario: Successfully create a card
    Given a customer with mobile number "09123456789"
    And a valid card creation request
    When I request to create a card
    Then the card should be created successfully
    And response should contain cardId