Feature: Card Manager

  Scenario: Successfully create a card
    Given a valid card creation request with mobile number "09123456789"

    When I request to create a card

    Then the card should be created successfully
    And response should contain cardId

  Scenario: Prevent duplicate card creation
    Given a card with number "12054" already exists
    And a valid card creation request with card number "12054"

    When I request to create a card

    Then "CardAlreadyExistsException" should be thrown

  Scenario: Invalid card type
    Given a valid card creation request with card type "SAVINGS"

    When I request to create a card

    Then "InvalidCardTypeException" should be thrown

  Scenario: Invalid expiry date
    Given a valid card creation request with expiry date "2025"

    When I request to create a card

    Then "InvalidExpiryDateException" should be thrown