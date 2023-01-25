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
    When do POST to "https://restful-booker.herokuapp.com/auth"
    Then status code is 200
    And response contains token


  Scenario Outline: Create token. Not valid data in the request body
    Given request body <username> and <password>
    When do POST with invalid data to "https://restful-booker.herokuapp.com/auth"
    Then response contains reason "Bad credentials"

    Examples:
      | username | password    |
      | admn     | password123 |
      | admin    | pass111     |