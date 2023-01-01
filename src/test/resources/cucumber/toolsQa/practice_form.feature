Feature: Student Registration Form
  Student may enter his own data for registration.

  #TODO сделать секцию Scenario Outline: с таблицей для параметризованного теста

  #Обозначение общего шага для всех сценариев в фиче
  Background:
  Given prepare urls

  Scenario: Correct and full input to the form
    Given Student is on the reg. form page
    When Student inputs his data into the form
    Then Submit goes correct
