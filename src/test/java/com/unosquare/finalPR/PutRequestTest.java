package com.unosquare.finalPR;

import com.unosquare.ApiCore;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.with;

public class PutRequestTest {

    private ApiCore apiCore;

    @DataProvider(name = "data-provider")
    public Object[][] dpMethod() {
        String basePath = "src/test/java/resources/";
        return new Object[][]{{basePath + "update.json", "1"}};
    }

    @BeforeSuite
    public void beforeSuite() {
        this.apiCore = new ApiCore();
    }

    @Test(groups = {"group1"}, dataProvider = "data-provider")
    public void PutUSer(String filePath, String userId) throws IOException, ParseException {
        Response currentUser = this.apiCore.getUserById(userId);
        String old_first_name = currentUser.jsonPath().get("data.first_name");
        Response response = this.apiCore.PutUser(filePath, "/Users/" + userId);
        System.out.println(response.body().asString());
        Assert.assertNotEquals(response.jsonPath().get("data.first_name"), old_first_name);
        Assert.assertEquals(response.statusCode(), 200);
    }
}
