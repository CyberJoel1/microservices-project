package com.microservices.userservice.stepdefinitions;

import com.intuit.karate.junit5.Karate;

class ClientApiTest {

    @Karate.Test
    Karate testClients() {
        return Karate.run("classpath:client-api.feature").relativeTo(getClass());
    }
}
