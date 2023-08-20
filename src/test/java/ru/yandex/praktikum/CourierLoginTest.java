package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.pojo.CourierLoginResponse;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static ru.yandex.praktikum.api.CourierApi.*;
import static ru.yandex.praktikum.util.TestConstants.*;

public class CourierLoginTest {


    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
        createCourier(TEST_COURIER_LOGIN, TEST_COURIER_PASSWORD, TEST_COURIER_NAME);
    }

    @Test
    @DisplayName("Test login courier ")
    public void loginCourierTest() {
        Response response = loginCourier(TEST_COURIER_LOGIN, TEST_COURIER_PASSWORD);
        response.then().assertThat().body("id", notNullValue())
                .and()
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Test login courier without login ")
    public void loginCourierWithoutLoginTest() {
        Response response = loginCourier(null, TEST_COURIER_PASSWORD);
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Test login courier without password")
    public void loginCourierWithoutPasswordTest() {
        Response response = loginCourier(TEST_COURIER_LOGIN, "");
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Test login courier with incorrect login")
    public void loginCourierWithIncorrectLoginTest() {
        Response response = loginCourier(TEST_COURIER_LOGIN + Integer.MAX_VALUE, TEST_COURIER_PASSWORD);
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Test login courier with incorrect password")
    public void loginCourierWithIncorrectPasswordTest() {
        Response response = loginCourier(TEST_COURIER_LOGIN, TEST_COURIER_PASSWORD + Integer.MAX_VALUE);
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(SC_NOT_FOUND);
    }

    @After
    public void setDown() {
        CourierLoginResponse loginResponse = loginCourier(TEST_COURIER_LOGIN, TEST_COURIER_PASSWORD)
                .body().as(CourierLoginResponse.class);
        deleteCourier(loginResponse.getId());
    }
}
