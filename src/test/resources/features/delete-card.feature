Feature: Delete Card

  Scenario: Delete existing card
    Given a card exists

    When I delete the card

    Then the card should be removed from database

  Scenario: Delete non existing card
    Given card with id 99999 does not exist

    When I delete the card with id 99999

    Then "ResourceNotFoundException" should be thrown