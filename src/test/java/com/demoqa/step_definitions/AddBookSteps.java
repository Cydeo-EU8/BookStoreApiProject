package com.demoqa.step_definitions;

import com.demoqa.pages.ResponseApiPage;
import com.demoqa.utilities.ConfigurationReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.*;
public class AddBookSteps {
    Response response;
    String isbn = "9781491950296";
    @When("A POST request is sent for adding book")
    public void aPOSTRequestIsSentForAddingBook() {
        baseURI = ConfigurationReader.get("baseUrl");
        basePath = ConfigurationReader.get("apiAddBook");
        String token = GetUserInfoSteps.token;
        System.out.println("token = " + token);
        response = given().header("Authorization",token).and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(new ResponseApiPage().addBookRequestBody(isbn))
                .when().post();
        response.prettyPrint();
    }


    @Then("Status codes {int} and book added is verified")
    public void statusCodesAndBookAddedIsVerified(int status) {
        Assert.assertEquals(status,response.statusCode());
        String actualIsbn = response.jsonPath().getString("books[0].isbn");
        Assert.assertEquals(isbn,actualIsbn);
    }
}
