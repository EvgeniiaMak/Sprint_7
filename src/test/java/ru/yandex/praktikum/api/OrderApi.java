package ru.yandex.praktikum.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.praktikum.pojo.CancelOrderRequest;
import ru.yandex.praktikum.pojo.CreateOrderRequest;

import static io.restassured.RestAssured.given;

public class OrderApi {
    private final static String CREATE_ORDERS_URL = "/api/v1/orders";
    private final static String FINISH_ORDERS_URL = "/api/v1/orders/finish/";
    private final static String CANCEL_ORDERS_URL = "/api/v1/orders/cancel";
    private final static String ACCEPT_ORDERS_URL = "/api/v1/orders/accept/";
    private final static String GET_ORDERS_URL = "/api/v1/orders";
    private final static String TRACK_ORDERS_URL = "/api/v1/orders/track";


    @Step("Send POST request to /api/v1/orders")
    public static Response createOrder(CreateOrderRequest request) {

        return given()
                .header("Content-type", "application/json")
                .body(request)
                .post(CREATE_ORDERS_URL);
    }

    @Step("Send PUT request to /api/v1/orders/finish/")
    public static Response finishOrder(int id) {
        return given()
                .header("Content-type", "application/json")
                .put(FINISH_ORDERS_URL + id);
    }

    @Step("Send PUT request to /api/v1/orders/cancel")
    public static Response cancelOrder(String track) {
        CancelOrderRequest cancelOrderRequest = new CancelOrderRequest(track);
        return given()
                .header("Content-type", "application/json")
                .body(cancelOrderRequest)
                .put(CANCEL_ORDERS_URL);
    }

    @Step("Send PUT request to /api/v1/orders/accept/")
    public static Response acceptOrder(int orderId, int courierId) {
        return given()
                .header("Content-type", "application/json").queryParam("courierId", courierId)
                .put(ACCEPT_ORDERS_URL + orderId);
    }

    @Step("Send GET request to /api/v1/orders")
    public static Response getOrders(int courierId) {
        return given()
                .header("Content-type", "application/json").queryParam("courierId", courierId)
                .get(GET_ORDERS_URL);
    }

    @Step("Send GET request to /api/v1/orders/track")
    public static Response trackOrder(int orderTrack) {
        return given()
                .header("Content-type", "application/json").queryParam("t", orderTrack)
                .get(TRACK_ORDERS_URL);
    }
}
