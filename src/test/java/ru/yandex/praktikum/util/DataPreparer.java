package ru.yandex.praktikum.util;

import ru.yandex.praktikum.pojo.CreateOrderRequest;

import static ru.yandex.praktikum.util.TestConstants.*;

public final class DataPreparer {

    private DataPreparer() {
    }

    public static CreateOrderRequest preparationCreateOrderRequest(String[] colors) {
        return new CreateOrderRequest(TEST_ORDER_FIRST_NAME, TEST_ORDER_LAST_NAME, TEST_ORDER_ADDRESS, TEST_ORDER_METRO_STATION, TEST_ORDER_PHONE, TEST_ORDER_RENT_TIME, colors);
    }

    public static CreateOrderRequest preparationCreateOrderRequest() {
        return new CreateOrderRequest(TEST_ORDER_FIRST_NAME, TEST_ORDER_LAST_NAME, TEST_ORDER_ADDRESS, TEST_ORDER_METRO_STATION, TEST_ORDER_PHONE, TEST_ORDER_RENT_TIME, new String[]{BLACK_COLOUR});
    }

}
