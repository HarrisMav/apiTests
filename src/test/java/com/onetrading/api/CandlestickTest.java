package com.onetrading.api;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CandlestickTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "https://api.onetrading.com/public/v1/candlesticks";
    }

    @Test
    public void testValidCandlestickRequest() {
        // Valid request with parameters
        given()
                .queryParam("unit", "HOURS")
                .queryParam("period", "1")
                .queryParam("from", "2019-10-03T04:59:59.999Z")
                .queryParam("to", "2019-10-03T07:59:59.999Z")
                .header("Accept", "application/json")
                .when()
                .get("/BTC_EUR")
                .then()
                .statusCode(200) // Assuming 200 is the success status code
                .body("data", hasSize(greaterThan(0))); // Validate that data is present
    }

    @Test
    public void testInvalidTimeRange() {
        // Should 2099 be a valid time request for candlesticks? It returns 200
        given()
                .queryParam("unit", "HOURS")
                .queryParam("period", "1")
                .queryParam("from", "2099-10-03T04:59:59.999Z")
                .queryParam("to", "2099-10-03T07:59:59.999Z")
                .header("Accept", "application/json")
                .when()
                .get("/BTC_EUR")
                .then()
                .statusCode(400);
    }

    @Test
    public void testMissingHeaders() {
        // Missing headers in the request
        given()
                .queryParam("period", "1")
                .queryParam("from", "2019-10-03T04:59:59.999Z")
                .queryParam("to", "2019-10-03T07:59:59.999Z")
                .when()
                .get("/BTC_EUR")
                .then()
                .statusCode(400);
    }
}
