Feature: Healthcheck
  A simple health check endpoint to confirm whether the API is up and running.

  Scenario: Do ping
    When do GET request to "https://restful-booker.herokuapp.com/ping"
    Then response status code is 201