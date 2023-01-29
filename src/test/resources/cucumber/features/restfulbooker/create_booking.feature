Feature: Creates a new booking in the API

  Background:
    Given set base uri "https://restful-booker.herokuapp.com/booking" for create feature


  Scenario: creating booking. Json request body
  Given make a body from "restfullbooker/createRq.json" file
  When do POST with body
  Then response contains info from request body