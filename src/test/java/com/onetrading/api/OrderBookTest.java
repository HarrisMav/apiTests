package com.onetrading.api;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OrderBookTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "https://api.onetrading.com"; // Replace with the actual base URI
    }

    @Test
    public void testGetOrderBookLevel3() {
        Response response = given()
                .header("Accept", "application/json")
                .queryParam("level", 3) // Level 3 is a full order book
                .pathParam("instrument_code", "BTC_EUR")
                .when()
                .get("/public/v1/order-book/{instrument_code}")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Validate the response structure and content for a level 3 order book
        response.then()
                .body("bids", hasSize(greaterThan(0))) // Ensure there are bids in the response
                .body("asks", hasSize(greaterThan(0))) // Ensure there are asks in the response
                .body("bids[0]", hasKey("price")) // Check if the first bid has a "price" field
                .body("asks[0]", hasKey("price")); // Check if the first ask has a "price" field
    }

    @Test
    public void testGetOrderBookDefaultLevel() {
        // Perform a GET request to the /order-book/{instrument_code} endpoint without specifying level (default is level 3)
        Response response = given()
                .header("Accept", "application/json")
                .pathParam("instrument_code", "BTC_EUR")
                .when()
                .get("/public/v1/order-book/{instrument_code}")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Validate the response structure and content for the default level (level 3)
        response.then()
                .body("bids", hasSize(greaterThan(0)))
                .body("asks", hasSize(greaterThan(0)))
                .body("bids[0]", hasKey("price"))
                .body("asks[0]", hasKey("price"));
    }

    @Test
    public void testGetOrderBookWithDepthLimit() {
        Response response = given()
                .header("Accept", "application/json")
                .queryParam("depth", 5) // Limit the depth to 5
                .pathParam("instrument_code", "BTC_EUR")
                .when()
                .get("/public/v1/order-book/{instrument_code}")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Validate the response structure and content with a depth limit
        response.then()
                .body("bids", hasSize(5)) // Ensure there are 5 bids in the response
                .body("asks", hasSize(5)); // Ensure there are 5 asks in the response
    }
}
