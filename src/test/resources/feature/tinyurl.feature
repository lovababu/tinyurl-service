Feature: Tiny URL service Behavior testing

  Background:
    Given the service is up and running

  Scenario: as a user I wanted to shorten my long url
    Given the below long url and invoke the service
      |url                                                         |customKey|expiryTimeInMs|
      |https://www.zycus.com/solution/strategic-sourcing-suite.html|         |              |
    Then it succeeded with http status code 200
    And the shortened url is accessible

  Scenario: as a user I wanted to verify the system behavior with invalid url
    Given the below long url and invoke the service
      |url|customKey|expiryTimeInMs|
      |abc|         |              |
    Then it failed with http status code 400

  Scenario: as a user I wanted to verify the system behavior by requesting multiple times with same long url
    Given the below long url and invoke the service
      |url                                                                                                                                                                                                                           |customKey|expiryTimeInMs|
      |https://www.amazon.com/Kindle-Wireless-Reading-Display-Globally/dp/B003FSUDM4/ref=amb_link_353259562_2?pf_rd_m=ATVPDKIK X0DER&pf_rd_s=center-10&pf_rd_r=11EYKTN682A79T370AM3&pf_rd_t=201&pf_rd_p=1270985982&pf_rd_i=B002Y27P3M|         |              |
    Then it succeeded with http status code 200
    And the shortened url is accessible

    Then the below long url and invoke the service
      |url                                                                                                                                                                                                                           |customKey|expiryTimeInMs|
      |https://www.amazon.com/Kindle-Wireless-Reading-Display-Globally/dp/B003FSUDM4/ref=amb_link_353259562_2?pf_rd_m=ATVPDKIK X0DER&pf_rd_s=center-10&pf_rd_r=11EYKTN682A79T370AM3&pf_rd_t=201&pf_rd_p=1270985982&pf_rd_i=B002Y27P3M|         |              |
    And the shortened url is accessible

  Scenario: as a user I wanted to shorten my long url with my custom key
    Given the below long url and invoke the service
      |url                                                         |customKey|expiryTimeInMs|
      |https://www.zycus.com/solution/strategic-sourcing-suite.html|zycus1   |              |
    Then it succeeded with http status code 200
    And the shortened url is accessible


  Scenario: as a user I wanted to verify system behavior by passing same custom key multiple times
    Given the below long url and invoke the service
      |url                                                         |customKey|expiryTimeInMs|
      |https://www.zycus.com/solution/strategic-sourcing-suite.html|zycus2   |              |
    Then it succeeded with http status code 200
    And the shortened url is accessible

    When the below long url and invoke the service
      |url                                                         |customKey|expiryTimeInMs|
      |https://www.zycus.com/solution/strategic-sourcing-suite.html|zycus2   |              |
    Then it failed with http status code 409
