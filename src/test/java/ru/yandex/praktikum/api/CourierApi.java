package ru.yandex.praktikum.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.praktikum.pojo.CourierCreateRequest;
import ru.yandex.praktikum.pojo.CourierLoginRequest;

import static io.restassured.RestAssured.given;

public class CourierApi {
    private final static String CREATE_COURIER_URL = "/api/v1/courier";
    private final static String LOGIN_COURIER_URL = "/api/v1/courier/login";
    private final static String DELETE_COURIER_URL = "/api/v1/courier/";

    @Step("Send POST request to /api/v1/courier")
    public static Response createCourier(String courierName, String password, String firstname) {
        CourierCreateRequest request = new CourierCreateRequest(courierName, password, firstname);
        return given()
                .header("Content-type", "application/json")
                .body(request)
                .post(CREATE_COURIER_URL);
    }

    @Step("Send POST request to /api/v1/courier/login")
    public static Response loginCourier(String login, String password) {
        CourierLoginRequest request = new CourierLoginRequest(login, password);
        return given()
                .header("Content-type", "application/json")
                .body(request)
                .post(LOGIN_COURIER_URL);
    }

    @Step("Send DELETE request to /api/v1/courier/")
    public static Response deleteCourier(int id) {
        return given()
                .header("Content-type", "application/json")
                .delete(DELETE_COURIER_URL + id);
    }
}
