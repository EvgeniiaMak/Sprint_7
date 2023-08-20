package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.pojo.*;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.greaterThan;
import static ru.yandex.praktikum.api.CourierApi.*;
import static ru.yandex.praktikum.api.OrderApi.*;
import static ru.yandex.praktikum.util.DataPreparer.preparationCreateOrderRequest;
import static ru.yandex.praktikum.util.TestConstants.*;

public class ListOrdersTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
        createCourier(TEST_COURIER_LOGIN, TEST_COURIER_PASSWORD, TEST_COURIER_NAME);
    }

    @Test
    @DisplayName("Test get list order")
    public void getListOrderTest() {
        CourierLoginResponse loginResponse = loginCourier(TEST_COURIER_LOGIN, TEST_COURIER_PASSWORD)
                .body().as(CourierLoginResponse.class);
        // создать заказ
        CreateOrderResponse orderResponse = createOrder(preparationCreateOrderRequest()).body().as(CreateOrderResponse.class);
        // получить идентификатор заказа по трэку
        TrackOrderResponse trackOrderResponse = trackOrder(orderResponse.getTrack()).body().as(TrackOrderResponse.class);
        // взять заказ курьером
        acceptOrder(trackOrderResponse.getOrder().getId(), loginResponse.getId());

        Response getOrdersResponse = getOrders(loginResponse.getId());
        getOrdersResponse.then().assertThat().statusCode(SC_OK);
        GettingListOfOrdersResponse response = getOrdersResponse.body().as(GettingListOfOrdersResponse.class);

        MatcherAssert.assertThat(response.getOrders().length, greaterThan(0));
    }

    @After
    public void setDown() {
        CourierLoginResponse loginResponse = loginCourier(TEST_COURIER_LOGIN, TEST_COURIER_PASSWORD)
                .body().as(CourierLoginResponse.class);
        deleteCourier(loginResponse.getId());
    }


}
