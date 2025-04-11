Feature: Test Suite to validate everything about passenger attributes (every scenario is a Test Case "TC")

  Scenario: pass:suc:01 => Verify the possibility to add  passenger to the flight with a valid name, passport, age and class type
    Given the following information
    """
    [
      {
        "flightNumber": "UQAM001",
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
      | UQAM001,335436457,Omer Simpson,43,FIRST_CLASS,8146 |

  Scenario: pass:err:name:02 => Verify not add passenger with not valid name
    Given the following information
    """
    [
      {
        "flightNumber": "UQAM001",
        "passengerPassport": "335436457",
        "passengerName": "Weirdjvjf::;:!bn;n!n",
        "passengerAge": 43,
        "passengerType": "first"
      }
    ]
    """
    When call to save passengers is launched
    Then response code is equal to 400
    And no saved file created

  Scenario: pass:err:name:03 => Verify not add passenger if name is not defined
    Given the following information
    """
    [
      {
        "flightNumber": "UQAM001",
        "passengerPassport": "335436457",
        "passengerName": "",
        "passengerAge": 52,
        "passengerType": "first"
      }
    ]
    """
    When call to save passengers is launched
    Then response code is equal to 400
    And no saved file created

  Scenario: pass:err:passport:04 => Verify not add passenger with not valid passport number
    Given the following information
    """
    [
      {
        "flightNumber": "UQAM001",
        "passengerPassport": "33543zssdfA6457",
        "passengerName": "Omer Simpson",
        "passengerAge": 43,
        "passengerType": "first"
      }
    ]
    """
    When call to save passengers is launched
    Then response code is equal to 400
    And no saved file created

  Scenario: pass:err:passport:05 => Verify not add passenger if passport number not defined
    Given the following information
    """
    [
      {
        "flightNumber": "UQAM001",
        "passengerPassport": "",
        "passengerName": "Omer Simpson",
        "passengerAge": 43,
        "passengerType": "first"
      }
    ]
    """
    When call to save passengers is launched
    Then response code is equal to 400
    And no saved file created

  Scenario: pass:err:age:06 => Verify not add passenger with not valid age
    Given the following information
    """
    [
      {
        "flightNumber": "UQAM001",
        "passengerPassport": "335436457",
        "passengerName": "Omer Simpson",
        "passengerAge": -23,
        "passengerType": "first"
      }
    ]
    """
    When call to save passengers is launched
    Then response code is equal to 400
    And no saved file created

  Scenario: pass:err:age:07 => Verify not add passenger if age is not defined
    Given the following information
    """
    [
      {
        "flightNumber": "UQAM001",
        "passengerPassport": "335436457",
        "passengerName": "Omer Simpson",
        "passengerAge": "" ,
        "passengerType": "first"
      }
    ]
    """
    When call to save passengers is launched
    Then response code is equal to 400
    And no saved file created




