Feature: Returns the ids of all the bookings that exist within the API. Can take optional query strings to search and return a subset of booking ids.

  Background:
    Given set base url "https://restful-booker.herokuapp.com"

  Scenario: Get all ids
    When do GET with path "/booking"
    Then response contains array of objects with booking ids

  Scenario: Get all filtered by name
    Given name and surname for filtering
      | firstname | Josh |
      | lastname  | Allen |
    When do GET with query firstname and lastname with path "/booking"
    Then response contains this person booking

    ## Format must be CCYY-MM-DD
  Scenario: Get all filtered by date
    Given dates for filtering
      | checkin  | 2014-03-13 |
      | checkout | 2014-05-21 |
    When do GET with date filters and path "/booking"
    Then response contains these dates booking