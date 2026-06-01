Feature: Update Card Status

  Scenario: Update card status to BLOCKED
    Given I request to create a card with status "ACTIVE"

    When I update card status to "BLOCKED"

    Then card status should be "BLOCKED"

  Scenario: Update non existing card
    Given card with id 99999 does not exist

    When I update card status to BLOCKED

    Then "ResourceNotFoundException" should be thrown

  Scenario: Update to a invalid card status
    Given a card exists

    When I update card status to "UNKNOWN"

    Then "InvalidCardStatusException" should be thrown