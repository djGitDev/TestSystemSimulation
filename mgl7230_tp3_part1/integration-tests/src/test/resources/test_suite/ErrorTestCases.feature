Feature: Test Suite for error adding passenger to flight

  @ignore
  Scenario: Wrong flight number is selected
    Given passenger selects flight "NON-EXISTANT"
    And flight catalog doesn't have this flight
    And service is initialized
    When agent pass all information to the system and continue "no"
    And system is called
    Then no data is added to file
    And agent is requested to retry
