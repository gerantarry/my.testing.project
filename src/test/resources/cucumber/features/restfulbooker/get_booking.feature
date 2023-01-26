Feature: Returns a specific booking based upon the booking id provided

  Background:
    Given set base uri "https://restful-booker.herokuapp.com/booking/"

  ## json by default
  ## xml
  ## id 172 , 1250

  Scenario Outline: Get booking by id
    When  do GET with id to "<id>"
    Then response contains info

    Examples:
      | id   |
      | 172  |
      | 1250 |


