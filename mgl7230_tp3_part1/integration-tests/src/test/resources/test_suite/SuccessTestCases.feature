Feature: Test Suite for successfully adding passenger to flight

  @ignore
  Scenario Outline: Add First Class Passengers
    Given passenger selects flight "<flightNumber>"
    And flight catalog is mocked
    And plane catalog is mocked where number of first class seats is 5, number of business class seats is 12 and number of economy seats is 30
    And service is initialized
    And a passenger with passport number "<passengerPassport>"
    And name "<passengerName>"
    And age <passengerAge>
    And looking to buy in "<passengerClass>" class
    When agent pass all information to the system and continue "no"
    And system is called
    Then passenger is added to fly "<flightNumber>"
    Examples:
      | passengerPassport | passengerName | passengerAge | passengerClass | flightNumber |
      | PASSPORT_CAD001   | Passenger_1   | 10           | first          | UQAM005      |
      | PASSPORT_CAD002   | Passenger_2   | 20           | first          | UQAM005      |
      | PASSPORT_CAD003   | Passenger_3   | 30           | first          | UQAM005      |
      | PASSPORT_CAD004   | Passenger_4   | 40           | first          | UQAM005      |
      | PASSPORT_CAD005   | Passenger_5   | 50           | first          | UQAM005      |

  @ignore
  Scenario: Don't overbook the flight
    Given passenger selects flight "UQAM005"
    And flight catalog is mocked
    And plane catalog is mocked where number of first class seats is 0, number of business class seats is 0 and number of economy seats is 2
    And service is initialized
    And a passenger with passport number:
      | PASSPORT_CAD001 |
      | PASSPORT_CAD002 |
      | PASSPORT_CAD003 |
    And name:
      | Passenger_1 |
      | Passenger_2 |
      | Passenger_3 |
    And age:
      | 10 |
      | 20 |
      | 30 |
    And looking to buy in class:
      | first |
      | first |
      | first |
    When agent pass all information to the system and continue "yes"
    And system is called
    Then passenger "Passenger_1" is added to fly "UQAM005"
    Then passenger "Passenger_2" is added to fly "UQAM005"
    Then passenger "Passenger_3" is not added to fly "UQAM005"