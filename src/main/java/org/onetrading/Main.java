package org.onetrading;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Main {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://api.onetrading.com/public/v1";

        // Make a GET request
        Response response = RestAssured.get("/currencies");

        // Print the response
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Status Code: " + response.getStatusCode());

    }
}