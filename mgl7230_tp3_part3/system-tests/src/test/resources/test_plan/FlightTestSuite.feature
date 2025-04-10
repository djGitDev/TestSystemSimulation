Feature: Test Suite to validate everything about flight attributes and restrictions  (every scenario is a Test Case "TC")


  Scenario: flight:suc:flightNumber:01 => Verify the possibility to add  passenger to an existing flight
    Given the following information
    """
    [
      {
        "flightNumber": "UQAM003",
        "passengerPassport": "335436457",
        "passengerName": "Omer Simpson",
        "passengerAge": 43,
        "passengerType": "first"
      }
    ]
    """
    When call to save passengers is launched
    Then response code is equal to 201
    And following data is in the saved file
      | UQAM003,335436457,Omer Simpson,43,FIRST_CLASS,5503 |


  Scenario: flight:err:02 => Verify  to not add  passenger to an inexisting flight
    Given the following information
    """
    [
      {
        "flightNumber": "UQAM009",
        "passengerPassport": "335436457",
        "passengerName": "Omer Simpson",
        "passengerAge": 43,
        "passengerType": "first"
      }
    ]
    """
    When call to save passengers is launched
    Then response code is equal to 400
    And no saved file created

  Scenario: flight:err:03 => Verify to not add the same passenger twice to the same flight
    Given the following information
    """
    [
      {
        "flightNumber": "UQAM003",
        "passengerPassport": "335436457",
        "passengerName": "Omer Simpson",
        "passengerAge": 43,
        "passengerType": "first"
      },
      {
        "flightNumber": "UQAM003",
        "passengerPassport": "335436457",
        "passengerName": "Omer Simpson",
        "passengerAge": 43,
        "passengerType": "business"
      }
    ]
    """
    When call to save passengers is launched
    Then response code is equal to 400
    And following data is in the saved file
      | UQAM003,335436457,Omer Simpson,43,FIRST_CLASS,5503 |
    And following data is not in the saved file
      | UQAM003,335436457,Omer Simpson,43,BUSINESS_CLASS,5503 |
