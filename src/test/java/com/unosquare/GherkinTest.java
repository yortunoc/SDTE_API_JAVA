package com.unosquare;

import io.restassured.http.ContentType;
import org.testng.Reporter;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GherkinTest {
    @Test
    public void f_Gherkin() {

        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON)
                .assertThat().body("data.first_name", equalTo("Janet"));

        Reporter.log("Sucess 200 validation");
    }
}
