Feature: Client Service API Testing

  Background:
    # Configura la URL base de la API
    * url 'http://localhost:8080'
    * def identification = "1754897566"

  Scenario: Create a new client
    Given path '/clientes'
      And request {  "client": {  "name": "Katherine Tapia",  "gender": "Female",  "age": 27,  "identification": #(identification),  "address": "Av. Tsafiqui",  "phoneNumber": "+593981223926",  "password": "password123"  }  }
    When method post
    Then status 200
    And match response.client.name == 'Katherine Tapia'
    And match response.client.identification == identification
    * print response.client.clientId

    * def clientId = response.client.clientId

  Scenario: Get client by identification
    Given path '/clientes'
    And param identification = identification
    When method get
    Then status 200
    And match response.client.identification == identification