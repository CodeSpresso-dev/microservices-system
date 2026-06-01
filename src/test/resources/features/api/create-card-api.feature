Feature: Card API

  Scenario: Create card through API
    Given a valid card creation request

    When I send POST request to "/api/cards"

    Then api response status should be 201

    And api response should contain cardId

  Scenario: Create duplicate card through API

    Given a card with card number "1234567892548962" already exists

    And a valid card creation request with existence card number

    When I send POST request to "/api/cards"

    Then api response status should be 409