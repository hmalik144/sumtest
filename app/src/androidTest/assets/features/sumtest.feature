Feature: Sum test feature to test the app for addition operations

  @calculator_test
  Scenario Outline: Run successful functions on the calculator
    Given I start the application
    When I run calculator sum for values "<First value>" and "<Second value>"
    And I assert the operation has run successfully with result "<Sum>"
    Examples:
      | First value | Second value | Sum   |
      | 5           | 5            | 10    |
      | -231        | -5           | -236  |
      | 999         | 1            | 1000  |
      | 1000        | 1000         | 2000  |
      | -1000       | -1000        | -2000 |

  @calculator_test
  Scenario Outline: Run unsuccessful functions on the calculator
    Given I start the application
    When I run calculator sum for values "<First value>" and "<Second value>"
    And I assert the operation has failed with error message <Error Type>
    Examples:
      | First value | Second value | Error Type   |
      | 2200        | 500          | Overflow     |
      | t44         | -5           | InvalidInput |
      |             | 10           | EmptyInput   |
      | 1!          | 44           | InvalidInput |
      | 1.2         | -5           | InvalidInput |
      | -2200       | -150         | Overflow     |
      | 1,200       | 120          | InvalidInput |

  @calculator_test
  Scenario Outline: Run functions for integer limitation on the calculator
    Given I start the application
    When I run calculator sum for values "<First value>" and "<Second value>"
    And I assert the operation has failed with error message <Error Type>
    Examples:
      | First value | Second value | Error Type |
      | 2147483647  | 10           | Overflow   |
      | 2147483637  | 10           | Overflow   |
      | -2147483648 | -10          | Overflow   |
      | -2147483638 | 10           | Overflow   |