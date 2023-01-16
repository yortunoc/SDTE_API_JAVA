package com.unosquare;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

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
        Reporter.log("Success 200 validation");
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

    @Test
    public void f_Gherkin1() {

        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON)
                .assertThat().body("data.first_name", equalTo("Janet"));

        Reporter.log("Sucess 200 validation");
    }

    @Test
    void postRequest(){
        JSONObject requestParams = new JSONObject();
        requestParams.put("email","qa@gmail.com");
        requestParams.put("password","qaApi");

        try (FileWriter file = new FileWriter("test.json")) {
            file.write(requestParams.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        RestAssured.baseURI = "https://reqres.in/api";
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.headers("Content-Type", "application/json");
        httpRequest.body(requestParams.toString());
        Response response = httpRequest.post("/users");
    }



}
