package ru.yandex.praktikum;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.pojo.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class ListOrdersTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void createOrderPositive() {

        CourierLoginRequest loginRequest = new CourierLoginRequest("Neo", "111");
        CourierLoginResponse loginResponse = given()
                .header("Content-type", "application/json")
                .body(loginRequest)
                .post("/api/v1/courier/login").body().as(CourierLoginResponse.class);


        GettingListOfOrdersResponse response = given()
                .header("Content-type", "application/json")
                .get("/v1/orders?courierId="+loginResponse.getId()).body().as(GettingListOfOrdersResponse.class);


         MatcherAssert.assertThat(response, notNullValue());
    }



}
