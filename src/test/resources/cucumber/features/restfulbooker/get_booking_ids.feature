Feature: Returns the ids of all the bookings that exist within the API. Can take optional query strings to search and return a subset of booking ids.

  Background:
    Given set base url "https://restful-booker.herokuapp.com"

  Scenario: Get all ids
    When do GET with path "/booking"
    Then response contains array of objects with booking ids