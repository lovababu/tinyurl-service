Feature: Tiny URL service Behavior testing

  Background:
    Given the service is up and running
    And is healty

  Scenario: as a user I wanted to shorten my long url
    Given the below long url and invoke the service
      |url|
    Then it succeeded with http status code 200
    And the shortened url is accessible

  Scenario: as a user I wanted to verify the system behavior with invalid url
    Given the below long url and invoke the service
      |url|
    Then it failed with http status code 400

  Scenario: as a user I wanted to verify the system behavior by requesting multiple times with same long url
    Given the below long url and invoke the service
      |url|
    Then it succeeded with http status code 200
    And the shortened url is accessible

    Then the below long url and invoke the service
      |url|
    And the shortened url is accessible

  Scenario: as a user I wanted to shorten my long url with my custom key
    Given the below long url and invoke the service with custom key "xyz"
      |url|
    Then it succeeded with http status code 200
    And the shortened url is accessible


  Scenario: as a user I wanted to verify system behavior by passing same custom key multiple times
    Given the below long url and invoke the service with custom key "xyz"
      |url|
    Then it succeeded with http status code 200
    And the shortened url is accessible

    When the below long url and invoke the service with custom key "xyz"
      |url|
    Then it failed with http status code 409
