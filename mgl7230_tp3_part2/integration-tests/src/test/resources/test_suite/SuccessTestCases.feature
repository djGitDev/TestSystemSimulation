@ignore
Feature: Test Suite for successfully adding passenger to flight

  Scenario: Add First Class Passengers
    Given passenger selects flight "UQAM005"
    And flight catalog is mocked
    And a passenger with passport number "passport"
    And name "passengerName"
    And age 10
    And looking to buy in "first" class
    When service receives a call
    Then passenger is added to fly "UQAM005"

