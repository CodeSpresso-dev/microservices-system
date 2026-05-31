Feature: Update Card Status

  Scenario: Update card status to BLOCKED
    Given I request to create a card with status "ACTIVE"

    When I update card status to "BLOCKED"

    Then card status should be "BLOCKED"