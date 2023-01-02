Feature: Student Registration Form
  Student may enter his own data for registration.

  #TODO сделать секцию Scenario Outline: с таблицей для параметризованного теста

  #Обозначение общего шага для всех сценариев в фиче
  Background:
  Given prepare urls "reg_form_url"

  Scenario: Correct and full input to the form
    Given Student is on the reg. form page
    When Student inputs his data into the form
    Then Submit goes correct

    Scenario Outline: hobbies selection
      Given Hobbies didn't select
      When Student picks a <hobbie>
      Then The <hobbie> checkbox becomes selected

      Examples:
      | hobbie |
      | Sports |
      | Reading |
      | Music   |
