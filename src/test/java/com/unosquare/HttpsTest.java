package com.unosquare;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HttpsTest {
    @Test
    void validate_items(){

        RestAssured.baseURI = "https://reqres.in/api/";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get("/users/2");
        int expectedStatusCode =200;
        int statusCode = response.getStatusCode();
        Assert.assertEquals( expectedStatusCode, statusCode);
        response.then().body("data.id", equalTo(2));
        response.then().body("data.email", equalTo("janet.weaver@reqres.in"));
        response.then().body("data.first_name", equalTo("Janet"));
        response.then().body("data.last_name", equalTo("Weaver"));
    }

    @Test
    void validate_items_second_url(){

        RestAssured.baseURI = "https://reqres.in/api/";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get("/unknown/2");
        int expectedStatusCode =200;
        int statusCode = response.getStatusCode();
        Assert.assertEquals( expectedStatusCode, statusCode);
        response.then()
                .assertThat().body("data.id", equalTo(2))
                .assertThat().body("data.name", equalTo("fuchsia rose"));
    }

}
