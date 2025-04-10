Feature:  Test suite to validate all class type possibilities. (every scenario is a Test Case "TC")



  Scenario: type:suc:01 => Verify the possibility to add a first class passenger to the flight with first class seat available
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

  Scenario: book:suc:02 => Verify the possibility to add a business class passenger to the flight with business class seat available
    Given the following information
      """
        [
          {
            "flightNumber": "UQAM001",
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
    | UQAM001,pass1,Name1,10,BUSINESS_CLASS,8146 |

  Scenario: book:suc:03 => Verify the possibility to add a economy class passenger to the flight with economy class seat available
    Given the following information
      """
        [
          {
            "flightNumber": "UQAM001",
            "passengerPassport": "pass1",
            "passengerName": "Name1",
            "passengerAge": 10,
            "passengerType": "economy"
          }
        ]
      """
    When call to save passengers is launched
    Then response code is equal to 201
    And following data is in the saved file
    | UQAM001,pass1,Name1,10,ECONOMY_CLASS,8146 |

  Scenario: book:err:04 => Verify to not add a  passenger to the flight with unknown selected class
    Given the following information
      """
        [
          {
            "flightNumber": "UQAM001",
            "passengerPassport": "pass1",
            "passengerName": "Name1",
            "passengerAge": 10,
            "passengerType": "default"
          }
        ]
      """
    When call to save passengers is launched
    Then response code is equal to 400
    And no saved file created


