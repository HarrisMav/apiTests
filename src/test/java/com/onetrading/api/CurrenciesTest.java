package com.onetrading.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CurrenciesTest {
    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "https://api.onetrading.com/public/v1";
    }

    @Test
    public void testGetRequest() {
        Response response = RestAssured.get("/currencies");
        response.then().statusCode(200);

        response.then()
                .body("code", hasItems("BTC", "ETH", "USD", "EUR", "GBP"));
    }

    @Test
    public void testGetCurrenciesEndpointCurrencyPrecision() {
        Response response = get("/currencies");

        response.then().body("find { it.code == 'BTC' }.precision", equalTo(8));
    }

    @Test
    public void testGetCurrenciesEndpointCurrencyNotFound() {
        Response response = get("/currencies");
        //checking for currencies that shouldn't be there
        response.then().body("code", not(hasItem("HARD")));
    }

    //You can have a test to see after renaming a endpoint, all the environments have it updated. For example /cryptocurrency
    //shouldn't work anymore
    @Test
    public void testInvalidParameters() {
        Response response = get("cryptocurrency");
                response.then().statusCode(404);
    }
}
