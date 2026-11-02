Feature: Merchant Certificates and Approvals

  @merchant @certificate @review
  Scenario: Verify Certificates and Approvals page
    When user opens Certificates and Approvals
    Then Certificates and Approvals page should be displayed
    And Pending Approvals should be displayed
    And Templates Studio should be displayed
    When user clicks Preview for a certificate
    When user clicks Reject for a certificate
    When user clicks Sign and Approve for a certificate
    When user closes the Review tab

  @merchant @certificate @positive
  Scenario Outline: Create certificate template with valid details
    When user opens Certificates and Approvals
    And user opens Templates Studio
    And user clicks New Template
    And user enters certificate details "<TemplateName>" "<Signatory>"
    And user saves the certificate template
    Then certificate template "<TemplateName>" should be created successfully

    Examples:
      | TemplateName            | Signatory        |
      | Selenium Certificate    | Admin            |
      | Java Course Certificate | Training Manager |

  @merchant @certificate @negative
  Scenario Outline: Verify negative certificate validations
    When user opens Certificates and Approvals
    And user opens Templates Studio
    And user clicks New Template
    And user enters certificate details "<TemplateName>" "<Signatory>"
    Then certificate template should not be created for "<TestCase>"

    Examples:
      | TestCase                    | TemplateName     | Signatory |
      | BlankCertificateTemplate    |                  | Admin     |
      | BlankCertificateSignatory   | Test Certificate |           |
      | InvalidCertificateTemplate  | @@@**&           | Admin     |
      | InvalidCertificateSignatory | Test Certificate | 12345     |
