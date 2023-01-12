package com.unosquare;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class FirstTest {

    @Test
    void f(){
        RestAssured.baseURI = "https://reqres.in/api/";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get("/users/2");
        httpRequest.get("/lotto/1").then().body("data.id", equalTo(1));
        httpRequest.get("/lotto/1").then().body("data.name", equalTo("cerulean"));
        int statusCode = response.getStatusCode();

        // Assert that correct status code is returned.
        Assert.assertEquals(statusCode,200);
        Reporter.log("Sucess 200 validation");
        Reporter.log(response.body().asString());
    }

    @Test
    public void f_Gherkin() {
        given()
        .when()
        .get("https://reqres.in/api/users/2")
        .then().assertThat().statusCode(200).assertThat().contentType(ContentType.JSON);
        Reporter.log("Sucess 200 validation");
    }

}
