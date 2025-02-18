Feature: Client Service API Testing

  Background:
    # Configura la URL base de la API
    * url 'http://localhost:8080'

  Scenario: Get client by identification
    Given path '/clientes'
    And param identification = '12345'
    When method get
    Then status 200
    And match response == { identification: '12345' }

  Scenario: Create a new client
    Given path '/clientes'
    And request {  "client": {  "name": "John Doe",  "gender": "Male",  "age": 30,  "identification": "123456789",  "address": "123 Main St, City, Country",  "phoneNumber": "555-1234",  "password": "password123",  "status": "Active"  }  }
    When method post
    Then status 200
    And match response.name == 'John Doe'
    And match response.identification == '12345'


