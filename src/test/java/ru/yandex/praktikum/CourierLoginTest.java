package ru.yandex.praktikum;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.pojo.CourierCreateRequest;
import ru.yandex.praktikum.pojo.CourierLoginRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CourierLoginTest {


    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void loginPositive() {
        CourierLoginRequest request = new CourierLoginRequest("Neo", "111");
        Response response = given()
                .header("Content-type", "application/json")
                .body(request)
                .post("/api/v1/courier/login");
        response.then().assertThat().body("id", notNullValue())
                .and()
                .statusCode(200);

    }
}
