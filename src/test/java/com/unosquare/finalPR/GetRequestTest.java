package com.unosquare.finalPR;

import com.unosquare.ApiCore;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetRequestTest {

    private ApiCore apiCore;

    @DataProvider(name = "data-provider")
    public Object[][] dpMethod() {
        return new Object[][]{{"1"}, {"2"}, {"3"}, {"4"}};
    }

    @BeforeSuite
    public void beforeSuite() {
        this.apiCore = new ApiCore();
    }

    @Test(groups = {"group1"}, dataProvider = "data-provider")
    public void GetUSerById(String userId) {
        Response test = this.apiCore.getUserById(userId);
        System.out.println(test.body().asString());
        Assert.assertEquals(test.statusCode(), 200);
    }
}
