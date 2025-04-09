Feature: Test Suite to validate everything about passengers (every scenario is a Test Case "TC")

  Scenario: pass:suc:type:01 => Verify the possibility to add a first class passenger to the flight with first class seat available
    Given the following information
    """
    [
      {
        "flightNumber": "UQAM001",
        "passengerPassport": "pass1",
        "passengerName": "Name1",
        "passengerAge": 10,
        "passengerType": "first"
      }
    ]
    """
    When call to save passengers is launched
    Then response code is equal to 201
    And following data is in the saved file
      | UQAM001,pass1,Name1,10,FIRST_CLASS,8146 |

  Scenario: pass:suc:type:02 => Verify the possibility to add multiple first class passengers to the flight with multiple first class seats available
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