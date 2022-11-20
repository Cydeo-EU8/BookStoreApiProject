Feature: Adding Book

  Background: Token
    Given Generate Token request is sent to related end point

  @wip3
  Scenario: Add Book to User Account
    When A POST request is sent for adding book
    Then Status codes 201 and book added is verified