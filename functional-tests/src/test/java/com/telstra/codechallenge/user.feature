# See
# https://github.com/intuit/karate#syntax-guide
# for how to write feature scenarios
Feature: As an api user I want to retrieve some spring boot quotes

  Scenario: Get a userList with given limit
    Given url microserviceUrl
    And path '/users/2'
    When method GET
    Then status 200
    And match header Content-Type contains 'application/json'
    # see https://github.com/intuit/karate#schema-validation
    And match response == 
    """
    
     [ 
      {
        id : '#number',
        login : '#string',
        html_url : '#string'
      },
      {
        id : '#number',
        login : '#string',
        html_url : '#string'
      }
      ]
    
    """
    
    

  