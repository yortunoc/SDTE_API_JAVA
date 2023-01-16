package com.unosquare;

import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;


public class LoginTest {

    private ApiCore apiCore;

    @BeforeSuite
    public void beforeSuite(){
        this.apiCore = new ApiCore();
    }

    @Test
    public void PostLogin() throws IOException, ParseException {
        Response test = this.apiCore.PostRequest("src/test/java/resources/register.json", "/login");
        Assert.assertEquals(test.statusCode(), 200);
    }

    @Test
    public void GetUSerById() {
        Response test = this.apiCore.getUserById("2");
        System.out.println(test.body().asString());
        Assert.assertEquals(test.statusCode(), 200);
    }

}
