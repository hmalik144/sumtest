Feature: Sample feature file

  @sample_test
  Scenario: Successful login
    Given I start the application

  @sample_test
  Scenario: Unsuccessful login
    Given I start the application and throw an exception
