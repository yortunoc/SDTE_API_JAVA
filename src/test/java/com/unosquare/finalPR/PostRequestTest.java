package com.unosquare.finalPR;

import com.unosquare.ApiCore;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class PostRequestTest {
    private ApiCore apiCore;

    @DataProvider(name = "data-provider")
    public Object[][] dpMethod() {
        String basePath = "src/test/java/resources/createUser/";
        return new Object[][]{{basePath + "register1.json"}, {basePath + "register2.json"}};
    }

    @BeforeSuite
    public void beforeSuite() {
        this.apiCore = new ApiCore();
    }

    @Test(groups = {"group1"}, dataProvider = "data-provider")
    public void PostLogin(String filePath) throws IOException, ParseException {
        Response test = this.apiCore.PostRequest(filePath, "/login");
        Assert.assertEquals(test.statusCode(), 200);
    }

    @Test(groups = {"group1"}, dataProvider = "data-provider")
    public void PostUSer(String filePath) throws IOException, ParseException {
        Response test = this.apiCore.PostRequest(filePath, "/Users");
        System.out.println(test.body().asString());
        Assert.assertEquals(test.statusCode(), 201);
    }
}
