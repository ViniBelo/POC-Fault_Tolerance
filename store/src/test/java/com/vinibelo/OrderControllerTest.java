package com.vinibelo;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class OrderControllerTest {
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/service-b")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

}