package com.onetrading.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class InstrumentsTest {
    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "https://api.onetrading.com/public/v1"; // Replace with the actual base URI
    }

    @Test
    public void testGetInstrumentsStructure() {
        Response response = given()
                .header("Accept", "application/json")
                .when()
                .get("/instruments")
                .then()
                .statusCode(200)
                .extract()
                .response();

        response.then()
                .body("[0]", hasKey("state"))
                .body("[0].base", hasKey("code"))
                .body("[0].base", hasKey("precision"))
                .body("[0].quote", hasKey("code"))
                .body("[0].quote", hasKey("precision"))
                .body("[0]", hasKey("amount_precision"))
                .body("[0]", hasKey("market_precision"))
                .body("[0]", hasKey("min_size"));
    }
}
