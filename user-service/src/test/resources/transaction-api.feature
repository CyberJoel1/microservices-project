Feature: Client Service API Testing

  Background:
    # Configura la URL base de la API
    * url 'http://localhost:8080'
    * def clientId = 32
    * def accountNumber = "123456789"

  Scenario: Create a new account
  Given path '/cuentas'
  And request { "account": { "accountNumber": accountNumber, "type": "S", "accountBalance": 20.00, "client": { "clientId": clientId } } }
  When method post
  Then status 200
  And match response.account.accountBalance == 20.00

  Scenario: Make a credit note
  Given path '/banking/credit/rest'
  And request { "amount": 50.00, "accountNumber": accountNumber}
  When method post
  Then status 200
  And match response.movement.amount == 10.00

  Scenario: Make a debit note
  Given path '/banking/debit/rest'
  And request { "amount": 10.00, "accountNumber": accountNumber}
  When method post
  Then status 200
  And match response.movement.amount == 10.00