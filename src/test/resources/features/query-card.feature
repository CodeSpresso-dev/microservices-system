Feature: Get Cards

  Scenario: Retrieve cards by mobile number

    Given 2 cards exist for mobile number "09123456789"

    When I request to fetch cards for mobile number "09123456789"

    Then 2 cards should be returned