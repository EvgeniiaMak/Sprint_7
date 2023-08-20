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
import static ru.yandex.praktikum.api.CourierApi.*;
import static ru.yandex.praktikum.util.TestConstants.*;

public class CreateCourierTest {


    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Test creating courier")
    public void createCourierTest() {
        Response response = createCourier(TEST_COURIER_LOGIN, TEST_COURIER_PASSWORD, TEST_COURIER_NAME);
        response.then().assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(SC_CREATED);
    }

    @Test
    @DisplayName("Test double creating courier")
    public void createDoubleCourierTest() {
        createCourier(TEST_COURIER_LOGIN, TEST_COURIER_PASSWORD, TEST_COURIER_NAME);
        Response response = createCourier(TEST_COURIER_LOGIN, TEST_COURIER_PASSWORD, TEST_COURIER_NAME);
        response.then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(SC_CONFLICT);
    }

    @Test
    @DisplayName("Test create courier without login")
    public void createCourierWithoutLoginTest() {
        Response response = createCourier(null, TEST_COURIER_PASSWORD, TEST_COURIER_NAME);
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Test create courier without password")
    public void createCourierWithoutPasswordTest() {
        Response response = createCourier(TEST_COURIER_LOGIN, null, TEST_COURIER_NAME);
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Test create courier without first name")
    public void createCourierWithOutFirstName() {
        Response response = createCourier(TEST_COURIER_LOGIN, TEST_COURIER_PASSWORD, null);
        response.then().assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(SC_CREATED);
    }

    @After
    public void setDown() {
        CourierLoginResponse loginResponse = loginCourier(TEST_COURIER_LOGIN, TEST_COURIER_PASSWORD)
                .body().as(CourierLoginResponse.class);
        deleteCourier(loginResponse.getId());
    }
}
