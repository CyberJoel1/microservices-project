package com.microservices.userservice.stepdefinitions;

import com.intuit.karate.junit5.Karate;

public class TransactionApiTest {
    @Karate.Test
    Karate testClients() {
        return Karate.run("classpath:transaction-api.feature").relativeTo(getClass());
    }
}
