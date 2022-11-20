package com.demoqa.pages;

import com.demoqa.utilities.ConfigurationReader;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseApiPage {

    public Map<String,String> getRequestBody(){
        String requestUserName = ConfigurationReader.get("userName");
        String requestPassword = ConfigurationReader.get("passWord");
        Map<String,String> requestBody = new LinkedHashMap<>();
        requestBody.put("userName",requestUserName);
        requestBody.put("password",requestPassword);
        return requestBody;
    }

}
