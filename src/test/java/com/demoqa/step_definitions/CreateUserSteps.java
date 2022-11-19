package com.demoqa.step_definitions;

import com.demoqa.utilities.ConfigurationReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;


import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CreateUserSteps {
    Response response;
    String requestUserName;
    String requestPassword;
    String responseUserID;
    File outFile;
    PrintWriter output;

    @When("User sends a POST request to create user end point")
    public void user_sends_a_POST_request_to_create_user_end_point() {
        // body part of the request
        requestUserName = ConfigurationReader.get("userName");
        requestPassword = ConfigurationReader.get("passWord");
        Map<String,String> requestBody = new LinkedHashMap<>();
        requestBody.put("userName",requestUserName);
        requestBody.put("password",requestPassword);
        // end point of our request
        baseURI = ConfigurationReader.get("baseUrl");
        basePath = ConfigurationReader.get("apiUser");
        // Below is our creating request to API
        response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(requestBody)
                .when().post();

    }

    @When("User captures status code userID username information")
    public void user_captures_status_code_userID_username_information() throws FileNotFoundException {
        // We are de-serializing response object to get UserID
        Map<Object,Object> responseMap = response.as(Map.class);
        responseUserID = (String) responseMap.get("userID");
        // writing userID to a regular resources file
        // defining a file: we need to provide absolute path of the file
        outFile = new File("C:\\Users\\Oscar\\IdeaProjects\\BookStoreApi\\src\\test\\resources\\userID.out");
        if(outFile.exists()){
            outFile.delete();
        }
        output = new PrintWriter(outFile);
        output.println(responseUserID);
        output.close();  // this step crucial to actually finalize writing function


    }

    @Then("Verifies status code username and userID is NOT null")
    public void verifies_status_code_username_and_userID_is_NOT_null() throws FileNotFoundException {
        // Read the file for userID
        outFile = new File("C:\\Users\\Oscar\\IdeaProjects\\BookStoreApi\\src\\test\\resources\\userID.out");
        Scanner scanner = new Scanner(outFile);
        String userID = scanner.next();
        System.out.println("userID = " + userID);

    }
}
