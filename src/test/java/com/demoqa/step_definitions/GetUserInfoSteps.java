package com.demoqa.step_definitions;

import com.demoqa.pages.ResponseApiPage;
import com.demoqa.utilities.ConfigurationReader;
import io.cucumber.java.Before;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GetUserInfoSteps {

    Response response;
    String token;
    @Before
    public void setUpRequest(){
        baseURI = ConfigurationReader.get("baseUrl");
        basePath = ConfigurationReader.get("apiGenerateToken");
    }

    @When("Generate Token request is sent to related end point")
    public void generateTokenRequestIsSentToRelatedEndPoint() {
           response = given().accept(ContentType.JSON)
           .and().contentType(ContentType.JSON)
           .and().body(new ResponseApiPage().getRequestBody()) // body comes from POM
           .when().post(); // end point comes from Before method
        JsonPath jsonPath = response.jsonPath();
        token = jsonPath.getString("token");
        System.out.println("token = " + token);
    }

}
