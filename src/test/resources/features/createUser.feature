Feature: Bookstore API Create User Functionality
  @wip1
  Scenario: Create a user
    When User sends a POST request to create user end point
    And User captures status code, userID, username and books information
    Then Verifies status code username and userID is NOT null