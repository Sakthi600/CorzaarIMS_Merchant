Feature: Merchant Batch Creation

  @merchant @batch @positive
  Scenario Outline: Create Cohort Batch using Mandatory Fields
    When user opens Batches and Attendance
    And user clicks Create Cohort Batch
    And user fills mandatory batch details "<Coordinator>" "<WeeklySchedule>" "<StartDate>" "<EndDate>" "<SeatCapacity>"
    And user generates batch sessions
    Then the created batch should be displayed in the batch table

    Examples:
      | Coordinator | WeeklySchedule | StartDate  | EndDate    | SeatCapacity |
      | Senior      | Mon, Wed       | 02-11-2026 | 25-01-2027 | 30           |

  @merchant @batch @roster
  Scenario Outline: Verify batch creation and Roster Matrix navigation
    When user opens Batches and Attendance
    And user clicks Create Cohort Batch
    And user fills mandatory batch details "<Coordinator>" "<WeeklySchedule>" "<StartDate>" "<EndDate>" "<SeatCapacity>"
    Then the created batch should be displayed in the batch table
    When user clicks Roster Matrix for the created batch
    Then the Roster Matrix page should be displayed
    When user closes the Roster Matrix
    Then the Roster Matrix should be closed

    Examples:
      | Coordinator | WeeklySchedule | StartDate  | EndDate    | SeatCapacity |
      | Senior      | Mon, Wed       | 02-11-2026 | 25-01-2027 | 30           |

  @merchant @batch @negative
  Scenario Outline: Verify negative batch validations
    When user opens Batches and Attendance
    And user clicks Create Cohort Batch
    And user tests batch validation for "<TestCase>" using  "<Coordinator>" "<WeeklySchedule>" "<StartDate>" "<EndDate>" "<SeatCapacity>"
    Then batch validation should be displayed for "<TestCase>"

    Examples:
      | TestCase              | Coordinator | WeeklySchedule | StartDate  | EndDate    | SeatCapacity |
      | MissingCoordinator    |             | Mon, Wed, Fri  | 03-11-2026 | 01-02-2027 | 30           |
      | MissingWeeklySchedule | Senior      |                | 03-11-2026 | 01-02-2027 | 30           |
      | PastStartDate         | Senior      | Mon, Wed, Fri  | 03-10-2026 | 01-02-2027 | 30           |
      | OldYearStartDate      | Senior      | Mon, Wed, Fri  | 03-11-2025 | 01-02-2027 | 30           |
      | PastEndDate           | Senior      | Mon, Wed, Fri  | 03-11-2026 | 03-10-2026 | 30           |
      | InvalidEndDate        | Senior      | Mon, Wed, Fri  | 03-11-2026 | 01-11-2026 | 30           |
      | ZeroSeatCapacity      | Senior      | Mon, Wed, Fri  | 03-11-2026 | 01-02-2027 | 0            |
      | BelowMinimumCapacity  | Senior      | Mon, Wed, Fri  | 03-11-2026 | 01-02-2027 | 1            |
      | NegativeSeatCapacity  | Senior      | Mon, Wed, Fri  | 03-11-2026 | 01-02-2027 | -5           |
