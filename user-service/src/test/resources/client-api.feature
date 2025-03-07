Feature: Client Service API Testing

  Background:
    # Configura la URL base de la API
    * url 'http://localhost:8080'

  Scenario: Create a new client
    Given path '/clientes'
    And request {  "client": {  "name": "Joel Velasco",  "gender": "Male",  "age": 27,  "identification": "17264944026",  "address": "Av. Tsafiqui",  "phoneNumber": "+593981223926",  "password": "password123"  }  }
    When method post
    Then status 200
    And match response.client.name == 'Joel Velasco'
    And match response.client.identification == '17264944026'
    * print response.client.clientId

  Scenario: Get client by identification
    Given path '/clientes'
    And param identification = '17264944026'
    When method get
    Then status 200
    And match response.client.identification == '17264944026'