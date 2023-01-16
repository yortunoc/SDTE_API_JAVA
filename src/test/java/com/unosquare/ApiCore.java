package com.unosquare;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class ApiCore {

    public RequestSpecification httpRequest;

    public ApiCore() {
        RestAssured.baseURI = "https://reqres.in/api";
        this.httpRequest = given();
    }


    public Response PostRequest(String json_path, String url) throws IOException, ParseException {
        Object json_obj;
        JSONParser json = new JSONParser();
        FileReader reader = new FileReader(json_path);
        json_obj = json.parse(reader);

        this.httpRequest.headers("Content-Type", "application/json");
        this.httpRequest.body(json_obj.toString());
        return this.httpRequest.post(url);
    }

    public Response getUserById(String userId) {
        return this.httpRequest.get("/users/" + userId);
    }

    public Response PutUser(String json_path, String url) throws IOException, ParseException {
        Object json_obj;
        JSONParser json = new JSONParser();
        FileReader reader = new FileReader(json_path);
        json_obj = json.parse(reader);

        this.httpRequest.headers("Content-Type", "application/json");
        this.httpRequest.body(json_obj.toString());
        return this.httpRequest.put(url);
    }
}
