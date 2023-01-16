package com.unosquare;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class PostUsingJsonFiles {

    @Test
    void create_post_using_json_files() {
        RestAssured.baseURI = "https://reqres.in/api/";
        RequestSpecification httpRequest = given();
        Object json_obj;
        try {
            JSONParser json = new JSONParser();
            FileReader reader = new FileReader("src/test/java/resources/test.json");
            json_obj = json.parse(reader);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        httpRequest.headers("Content-Type", "application/json");
        httpRequest.body(json_obj.toString());
        Response response = httpRequest.post("/users");
        System.out.println("Response Body");
        System.out.println(response.body().asString());
        System.out.println(response.statusCode());
        System.out.println("Json body sending");
        System.out.println(json_obj.toString());
        System.out.println("URL");
        System.out.println(RestAssured.baseURI);
    }
}
