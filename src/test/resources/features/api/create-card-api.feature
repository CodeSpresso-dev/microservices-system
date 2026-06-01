Feature: Card API

  Scenario: Create card through API
    Given a valid card creation request

    When I send POST request to "/api/cards"

    Then api response status should be 201

    And api response should contain cardId