Feature: Delete Card

  Scenario: Delete existing card
    Given a card exists

    When I delete the card

    Then the card should be removed from database