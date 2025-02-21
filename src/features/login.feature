Feature: Login user

  Scenario: Valid Login
    Given the user is on the login page
    When the user enters valid credentials "test_123@gmail.com", "test@123"
    And the user clicks on the login button
    Then the user should be redirected to the My Account page