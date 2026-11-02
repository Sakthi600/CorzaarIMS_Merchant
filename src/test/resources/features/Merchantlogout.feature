
Feature: Logout functionality

 @merchant @logout
  Scenario: Verify user logout successfully
    When user clicks Logout
    Then user should be redirected to the login page
