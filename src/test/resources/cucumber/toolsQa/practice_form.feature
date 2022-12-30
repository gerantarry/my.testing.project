Feature: Student Registration Form
  Student may enter his own data for registration.

  Scenario: Correct and full input to the form
    Given Student is on the reg. form page
    When Student inputs his data into the form
    Then Submit goes correct
