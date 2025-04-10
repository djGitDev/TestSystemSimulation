Feature: Test suite to validate the entire booking logic, covering all possible scenarios (each scenario represents a distinct Test Case "TC").



  Scenario: book:suc:01 => Verify the possibility to add multiple first class passengers to the flight with multiple first class seats available
    Given the following information
    """
    [
      {
        "flightNumber": "UQAM001",
        "passengerPassport": "pass1",
        "passengerName": "Name1",
        "passengerAge": 10,
        "passengerType": "first"
      },
      {
        "flightNumber": "UQAM001",
        "passengerPassport": "pass2",
        "passengerName": "Name2",
        "passengerAge": 20,
        "passengerType": "first"
      }
    ]
    """
    When call to save passengers is launched
    Then response code is equal to 201
    And following data is in the saved file
      | UQAM001,pass1,Name1,10,FIRST_CLASS,8146 |
      | UQAM001,pass2,Name2,20,FIRST_CLASS,8146 |


  Scenario: book:suc:02 => Verify the possibility to add one of each class passenger to the same flight with all classes seat availabilities
    Given the following information
    """
    [
      {
        "flightNumber": "UQAM001",
        "passengerPassport": "pass1",
        "passengerName": "Name1",
        "passengerAge": 10,
        "passengerType": "first"
      },
      {
        "flightNumber": "UQAM001",
        "passengerPassport": "pass2",
        "passengerName": "Name2",
        "passengerAge": 12,
        "passengerType": "business"
      },
      {
        "flightNumber": "UQAM001",
        "passengerPassport": "pass3",
        "passengerName": "Name3",
        "passengerAge": 16,
        "passengerType": "economy"
      }
    ]
    """
    When call to save passengers is launched
    Then response code is equal to 201
    And following data is in the saved file
      | UQAM001,pass1,Name1,10,FIRST_CLASS,8146 |
      | UQAM001,pass2,Name2,12,BUSINESS_CLASS,8146 |
      | UQAM001,pass3,Name3,16,ECONOMY_CLASS,8146 |

  Scenario: book:suc:03 => Verify the possibility to downgrade classes when selected class is not available
    Given the following information
    """
    [
      {
        "flightNumber": "UQAM005",
        "passengerPassport": "pass1",
        "passengerName": "Name1",
        "passengerAge": 10,
        "passengerType": "first"
      },
      {
        "flightNumber": "UQAM005",
        "passengerPassport": "pass2",
        "passengerName": "Name2",
        "passengerAge": 12,
        "passengerType": "business"
      },
      {
        "flightNumber": "UQAM005",
        "passengerPassport": "pass3",
        "passengerName": "Name3",
        "passengerAge": 16,
        "passengerType": "economy"
      }
    ]
    """
    When call to save passengers is launched
    Then response code is equal to 201
    And following data is in the saved file
      | UQAM005,pass1,Name1,10,BUSINESS_CLASS,5739 |
      | UQAM005,pass2,Name2,12,ECONOMY_CLASS,5739 |
      | UQAM005,pass3,Name3,16,ECONOMY_CLASS,5739 |

  Scenario: book:err:04 => Verify to not add passenger when seats are not available
    Given the following information
    """
    [
      {
        "flightNumber": "UQAM005",
        "passengerPassport": "pass1",
        "passengerName": "Name1",
        "passengerAge": 10,
        "passengerType": "first"
      },
      {
        "flightNumber": "UQAM005",
        "passengerPassport": "pass2",
        "passengerName": "Name2",
        "passengerAge": 12,
        "passengerType": "business"
      },
      {
        "flightNumber": "UQAM005",
        "passengerPassport": "pass3",
        "passengerName": "Name3",
        "passengerAge": 16,
        "passengerType": "economy"
      },
      {
        "flightNumber": "UQAM005",
        "passengerPassport": "pass4",
        "passengerName": "Name4",
        "passengerAge": 20,
        "passengerType": "first"
      },
      {
        "flightNumber": "UQAM005",
        "passengerPassport": "pass5",
        "passengerName": "Name5",
        "passengerAge": 23,
        "passengerType": "business"
      },
      {
        "flightNumber": "UQAM005",
        "passengerPassport": "pass6",
        "passengerName": "Name6",
        "passengerAge": 43,
        "passengerType": "economy"
      }
    ]
    """
    When call to save passengers is launched

    Then response code is equal to 400

    And following data is in the saved file
      | UQAM005,pass1,Name1,10,BUSINESS_CLASS,5739 |
      | UQAM005,pass2,Name2,12,ECONOMY_CLASS,5739 |
      | UQAM005,pass3,Name3,16,ECONOMY_CLASS,5739 |
      | UQAM005,pass4,Name4,20,ECONOMY_CLASS,5739 |
      | UQAM005,pass5,Name5,23,ECONOMY_CLASS,5739 |
    And following data is not in the saved file
      | UQAM005,pass6,Name6,43,ECONOMY_CLASS,5739 |