Feature: Merchant Batch Creation

  @merchant @batch @positive
  Scenario Outline: Create Cohort Batch using Mandatory Fields
    When user opens Batches and Attendance
    And user clicks Create Cohort Batch
    And user fills mandatory batch details "<Coordinator>" "<StartDate>" "<EndDate>" "<SeatCapacity>"
    And user generates batch sessions
    Then the created batch should be displayed in the batch table

    Examples:
      | Coordinator | StartDate  | EndDate    | SeatCapacity |
      | Senior      | 02-11-2026 | 25-01-2027 | 30           |

  @merchant @batch @roster
  Scenario Outline: Verify batch creation and Roster Matrix navigation
    When user opens Batches and Attendance
    And user clicks Create Cohort Batch
    And user fills mandatory batch details "<Coordinator>" "<StartDate>" "<EndDate>" "<SeatCapacity>"
    Then the created batch should be displayed in the batch table
    When user clicks Roster Matrix for the created batch
    Then the Roster Matrix page should be displayed
    When user closes the Roster Matrix
    Then the Roster Matrix should be closed

  @merchant @batch @negative
  Scenario Outline: Verify negative batch validations
    When user opens Batches and Attendance
    And user clicks Create Cohort Batch
    And user tests batch validation for "<TestCase>" using  "<Coordinator>" "<StartDate>" "<EndDate>" "<SeatCapacity>"
    Then batch validation should be displayed for "<TestCase>"

    Examples:
      | TestCase             | Coordinator | StartDate  | EndDate    | SeatCapacity |
      | MissingCoordinator   |             | 02-11-2026 | 25-01-2027 | 30           |
      | InvalidStartDate     | Senior      | 25-01-2027 | 02-11-2026 | 30           |
      | InvalidEndDate       | Senior      | 02-11-2026 | 01-11-2026 | 30           |
      | ZeroSeatCapacity     | Senior      | 02-11-2026 | 25-01-2027 | 0            |
      | NegativeSeatCapacity | Senior      | 02-11-2026 | 25-01-2027 | -5           |
