Feature: Merchant Course Authoring

  @merchant @course @positive
  Scenario Outline: Create a course and verify its actual status
    When user opens Course Studio
    And user clicks Create New Course Program
    And user fills mandatory course details "<CourseTitle>" "<TuitionFee>" "<Duration>" "<Overview>""<ModuleTitle>"
    And user clicks Add Module
    And user fills mandatory module details "<ModuleTitle1>"
    Then an additional module section should be displayed
    When user submits the course
    Then the created course status should be displayed
    And the existing course status should be displayed

    Examples:
      | CourseTitle                 | TuitionFee | Duration | Overview                          | ModuleTitle     | ModuleTitle1 |
      | Online Data Science Courses | 15000      | 2 Months | Learn Selenium automation testing | Selenium Basics | Corporate    |

  @merchant @course @negative
  Scenario Outline: Verify negative course validations
    When user opens Course Studio
    And user clicks Create New Course Program
    And user fills mandatory course details "<CourseTitle>" "<TuitionFee>" "<Duration>" "<Overview>""<ModuleTitle>"
    And user tests course validation for "<TestCase>"
    Then validation should be displayed for "<TestCase>"
    And user clicks Add Module
    And user fills mandatory module details "<ModuleTitle>"
    When user clicks the close button on the course program modal
    Then the course program modal should be closed

    Examples:
      | TestCase           | CourseTitle | TuitionFee | Duration | Overview        | ModuleTitle |
      | MissingTitle       |             | 15000      | 3 Months | Course Overview | Module 1    |
      | MissingTuitionFee  | Test Course |            | 3 Months | Course Overview | Module 1    |
      | MissingDuration    | Test Course | 15000      |          | Course Overview | Module 1    |
      | MissingOverview    | Test Course | 15000      | 3 Months |                 | Module 1    |
      | MissingModuleTitle | Test Course | 15000      | 3 Months | Course Overview |             |

  @merchant @course @update
  Scenario Outline: Update existing course details
    When user opens Course Studio
    And user clicks Edit for the course "<CourseTitle>"
    And user updates course details "<UpdatedTitle>" "<TuitionFee>" "<Duration>" "<Overview>"
    And user saves the course changes
    Then the updated course "<UpdatedTitle>" should be displayed in the course table

    Examples:
      | CourseTitle                  | UpdatedTitle               | TuitionFee | Duration | Overview                            |
      | Flutter and Dart Full Course | Advanced Technical Support | 18000      | 4 Months | Advanced technical support training |

  @merchant @course @updatenegative
  Scenario Outline: Validate negative course update
    When user opens Course Studio
    And user clicks Edit for the course "<CourseTitle>"
    And user updates course details "<UpdatedTitle>" "<TuitionFee>" "<Duration>" "<Overview>"
    Then the system should reject the invalid course details and prevent the update

    Examples:
      | CourseTitle                  | UpdatedTitle | TuitionFee | Duration | Overview         |
      | Flutter and Dart Full Course |              | 18000      | 3 Months | Advanced Support |
      | Flutter and Dart Full Course | UpdatedTitle | 0          | 3 Months | Advanced Support |
      | Flutter and Dart Full Course | UpdatedTitle | -1000      | 3 Months | Advanced Support |
      | Flutter and Dart Full Course | UpdatedTitle | 18000      | -1       | Advanced Support |

  @merchant @course @delete
  Scenario Outline: Delete existing course
    When user opens Course Studio
    And user deletes the configured course "<CourseTitle>"
    Then the deleted course "<CourseTitle>" should not be displayed in the course table

    Examples:
      | CourseTitle                  |
      | Flutter and Dart Full Course |
