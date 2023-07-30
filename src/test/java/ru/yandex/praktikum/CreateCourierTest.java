package ru.yandex.praktikum;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.pojo.CourierCreateRequest;
import ru.yandex.praktikum.pojo.CourierCreateResponse;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateCourierTest {

    Random random = new Random();

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void createCourier() {
        String courierName = "Neo"+random.nextInt();
        CourierCreateRequest request = new CourierCreateRequest(courierName, "111", "Tomas");
        Response response = given()
                .header("Content-type", "application/json")
                .body(request)
                .post("/api/v1/courier");
        response.then().assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);

    }
}
