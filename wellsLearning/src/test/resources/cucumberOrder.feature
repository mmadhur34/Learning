Feature: restrict order smaller than 1 million
  Scenario: limit small order
  Given order with qty 900000
    Then Fail


    Scenario: large orders are fine
      Given order with qty 1000000
      Then Pass