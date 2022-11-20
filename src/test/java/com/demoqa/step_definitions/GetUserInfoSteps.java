package com.demoqa.step_definitions;

import com.demoqa.pages.ResponseApiPage;
import com.demoqa.utilities.BookStoreApiUtils;
import com.demoqa.utilities.ConfigurationReader;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class GetUserInfoSteps {

    Response response;
    String requestUserName;
    String token;
    String responseUserID;
    String responseUserName;
    int responseStatusCode;
    List<Map<String,Object>> books;

    @When("Generate Token request is sent to related end point")
    public void generateTokenRequestIsSentToRelatedEndPoint() {
        requestUserName = ConfigurationReader.get("userName");

        baseURI = ConfigurationReader.get("baseUrl");
        basePath = ConfigurationReader.get("apiGenerateToken");
       Response responseToken = given().accept(ContentType.JSON)
           .and().contentType(ContentType.JSON)
           .and().body(new ResponseApiPage().getRequestBody()) // body comes from POM
           .when().post(); // end point comes from Before method
        JsonPath jsonPath = responseToken.jsonPath();
        token = "Bearer " + jsonPath.getString("token");
        System.out.println("token = " + token);
    }

    @When("User sends GET request to receive user information")
    public void userSendsGETRequestToReceiveUserInformation() {
        baseURI = ConfigurationReader.get("baseUrl");
        basePath = ConfigurationReader.get("apiUser");
        String userID = BookStoreApiUtils.readFromFile();
        response = given().accept(ContentType.JSON)
                .and().header("Authorization",token)
                .and().get("/"+userID);
        response.prettyPrint();
    }

    @And("User captures status code, userID, username and books information for GET")
    public void userCapturesStatusCodeUserIDUsernameAndBooksInformationForGET() {
        Map<Object,Object> responseMap = response.as(Map.class);
        responseUserID = (String) responseMap.get("userID");
        responseStatusCode = response.statusCode();
        responseUserName = (String) responseMap.get("username");
        books = (List<Map<String,Object>>) responseMap.get("books");
    }

    @Then("Verifies status code username and books information")
    public void verifiesStatusCodeUsernameAndBooksInformation() {
        assertEquals(200,responseStatusCode);
        assertEquals(requestUserName,responseUserName);
        System.out.println("books = " + books.size());
    }
}
