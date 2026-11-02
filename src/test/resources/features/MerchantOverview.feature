Feature: Merchant Studio Overview Dashboard Validation

  @merchant @overview @positive
  Scenario: Verify Institute Overview details
    Given user is logged into Merchant Studio Overview
    Then My Institute status should be displayed
    And Accreditation status should be displayed
    And logged-in institute should match expected institute
    And Create Course button should be displayed
    And Add Batch button should be displayed

  @merchant @overview @positive
  Scenario: Verify Add button
    And Create Course button should navigate to Course Studio page
    When user returns to Overview page
    And Add Batch button should navigate to Batches Attendance page
    When user returns to Overview page

  @merchant @overview @positive
  Scenario: Verify  card navigation
    When user clicks Course Studio card
    Then Course Studio page should be displayed
    When user returns to Overview page
    When user clicks Batches Attendance card
    Then Batches Attendance page should be displayed
    When user returns to Overview page

  @merchant @overview @positive
  Scenario: Verify Learner Drop-off card navigation
    When user clicks Learner Drop-off card
    Then Learner Drop-off page should be displayed
    When user returns to Overview page

  @merchant @overview @positive
  Scenario: Verify Course and Batch counts
    Then Overview dashboard should be displayed
    And Total Revenue value should be displayed
    And Confirmed Paid Learners count should be displayed
    Then course and batch table counts should match overview
