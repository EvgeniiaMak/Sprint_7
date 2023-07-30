package ru.yandex.praktikum;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.pojo.CourierCreateRequest;
import ru.yandex.praktikum.pojo.CreateOrderRequest;

import java.util.Arrays;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateOrderTest {

    Random random = new Random();

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void createOrderPositive() {


        CreateOrderRequest request = new CreateOrderRequest("Морфиус","Зеонович","Студеный, 32","Медведково","78345623432",2, new String[]{"BLACK"});
        Response response = given()
                .header("Content-type", "application/json")
                .body(request)
                .post("/api/v1/orders");
        response.then().assertThat().body("track", notNullValue())
                .and()
                .statusCode(201);

    }

}
