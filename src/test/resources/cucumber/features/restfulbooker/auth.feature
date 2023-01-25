Feature: Creates a new auth token to use for access to the PUT and DELETE /booking

  Scenario: Create token
    Given request body
    """body
    {
    "username" : "admin",
    "password" : "password123"
    }
    """
    And header content-type "application/json"
    When do POST request to "https://restful-booker.herokuapp.com/auth"
    Then status code is 200
    And response contains token