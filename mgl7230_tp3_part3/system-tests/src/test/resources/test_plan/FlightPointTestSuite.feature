Feature: Test Suite to validate the logic attributing points to passengers (every scenario is a Test Case "TC")

  Scenario: point:suc:01 => Verify the correct attributing points tt passenger according to distance flight
    Given the following information
    """
    [
      {
        "flightNumber": "UQAM005",
        "passengerPassport": "pass1",
        "passengerName": "Name1",
        "passengerAge": 10,
        "passengerType": "business"
      }
    ]
    """
    When call to save passengers is launched
    Then response code is equal to 201
    And following data is in the saved file
      | UQAM005,pass1,Name1,10,BUSINESS_CLASS,5739 |





  Scenario: point:suc:02 => Verify the correct cumulating points to passenger for multiple booking
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
        "flightNumber": "UQAM005",
        "passengerPassport": "pass1",
        "passengerName": "Name1",
        "passengerAge": 10,
        "passengerType": "business"
      }
    ]
    """
    When call to save passengers is launched
    Then response code is equal to 201
    And following data is in the saved file
      | UQAM001,pass1,Name1,10,FIRST_CLASS,8146 |
      | UQAM005,pass1,Name1,10,BUSINESS_CLASS,13885 |
