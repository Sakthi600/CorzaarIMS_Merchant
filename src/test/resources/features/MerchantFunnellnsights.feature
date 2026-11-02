Feature: Funnel Insights

  @merchant @funnelinsights @positive
  Scenario: Verify Learner Retention and Funnel Insights
    When user opens Funnel Insights
    Then Funnel Insights page should be displayed
    And Top Performing Programs should be displayed
    And Rank 1 should be displayed
    And Rank 2 should be displayed
    And Curriculum Completion and Drop-Off Matrix should be displayed
    And no drop-off data message should be displayed
