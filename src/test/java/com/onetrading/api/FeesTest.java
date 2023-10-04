package com.onetrading.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class FeesTest {
    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "https://api.onetrading.com/public/v1";
    }

    @Test
    public void testDefaultResponse() {
        Response response = given()
                .header("Accept", "application/json")
                .when()
                .get("/fees")
                .then()
                .statusCode(200)
                .extract()
                .response();

        response.then()
                .body(not(hasKey("error")));
    }

    @Test
    public void testFeeDiscountRateAndMinimumPriceValue() {
        Response response = given()
                .header("Accept", "application/json")
                .when()
                .get("/fees")
                .then()
                .statusCode(200) // Assuming 200 is the success status code
                .extract()
                .response();

        response.then()
                .body("$", hasSize(1))
                .body("[0].fee_group_id", equalTo("default"))
                .body("[0].display_text", equalTo("The standard fee plan."));

        String feeGroupId = response.jsonPath().getString("[0].fee_group_id");
        String displayText = response.jsonPath().getString("[0].display_text");

        Assert.assertEquals("default", feeGroupId);
        Assert.assertEquals("The standard fee plan.", displayText);
    }
}
