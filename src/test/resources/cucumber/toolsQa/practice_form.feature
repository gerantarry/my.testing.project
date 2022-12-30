Feature: Student Registration Form
  Student may enter his own data for registration.

  Scenario: Correct input to the form
    Given Browser opens the page with reg. form
    When Student inputs his data into the form
    Then Submit goes correct
