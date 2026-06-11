package com.anhtester.api.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest {
    @Test
    public void testApi01() {
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/");


    }

}
