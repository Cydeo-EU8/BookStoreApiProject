package com.demoqa.pages;
import com.demoqa.utilities.ConfigurationReader;
import io.cucumber.java.Before;

import static io.restassured.RestAssured.*;

public class CreateRequestBasePage {

    @Before
    public void setUpRequest(){
        baseURI = ConfigurationReader.get("baseUrl");
        basePath = ConfigurationReader.get("apiUser");
    }


}
