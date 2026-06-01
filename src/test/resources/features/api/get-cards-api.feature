Feature: Get Cards API

  Scenario: Get cards by mobile number
    Given cards exist for mobile number "09123456789"

    When I send GET request to "/api/cards/09123456789"

    Then api response status should be 200
    And api response should contain 2 cards