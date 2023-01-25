Feature: Student Registration Form
  Student may enter his own data for registration.

  #Обозначение общего шага для всех сценариев в фиче
  Background:
    Given Prepare urls "reg_form_url"

  @UI
  Scenario: Correct and full input to the form
    Given Student is on the reg. form page
    And His information is
      | firstName | secondName | gender | phoneNumber |
      | Steve     | Jenkins    | Male   | 8847210572  |
    When Student inputs his data into the form
    Then Submit goes correct

  @UI
  Scenario Outline: hobbies selection
    Given Hobbies didn't select
    When Student picks a <hobbie>
    Then The <hobbie> checkbox becomes selected

    Examples:
      | hobbie  |
      | Sports  |
      | Reading |
      | Music   |

    @UI
    Scenario Template: Gender selection. Need to check the group of radio buttons.
      Given Gender didn't selected
      When Student picks a gender <gender>
      Then The <gender> radio becomes selected

      Examples:
        | gender  |
        | Male    |
        | Female  |
        | Dolphin |
