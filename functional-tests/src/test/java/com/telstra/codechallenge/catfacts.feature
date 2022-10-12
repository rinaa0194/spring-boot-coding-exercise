# See
# https://github.com/intuit/karate#syntax-guide
# for how to write feature scenarios
Feature: As an api user I want to retrieve some cat facts

  Scenario: Get all the cat facts
    Given url microserviceUrl
    And path '/catFacts'
    When method GET
    Then status 200
    And match header Content-Type contains 'application/json'
    # see https://github.com/intuit/karate#schema-validation
    # Define the required schema
    * def catFactSchema = { fact : '#string', 'length' : '#number' }
    # The response should have an array of cat fact objects
    And match response == '#[] catFactSchema'
