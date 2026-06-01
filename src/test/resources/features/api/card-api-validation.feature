Feature: Card API Validation

  Scenario: Invalid CVV
    Given a valid card creation request with CVV "12"

    When I send POST request to "/api/cards"

    Then api response should contain validation error code "VALIDATION_ERROR"
    And api response status should be 400