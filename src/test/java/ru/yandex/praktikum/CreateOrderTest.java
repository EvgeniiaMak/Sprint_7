package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.pojo.CreateOrderRequest;

import static org.hamcrest.Matchers.notNullValue;
import static ru.yandex.praktikum.api.OrderApi.createOrder;
import static ru.yandex.praktikum.util.DataPreparer.preparationCreateOrderRequest;
import static ru.yandex.praktikum.util.TestConstants.BLACK_COLOUR;
import static ru.yandex.praktikum.util.TestConstants.GRAY_COLOUR;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private final CreateOrderRequest request;

    public CreateOrderTest(CreateOrderRequest request) {
        this.request = request;
    }


    @Parameterized.Parameters
    public static Object[] getCreateOrderRequest() {
        return new Object[][]{
                {preparationCreateOrderRequest(new String[]{BLACK_COLOUR})},
                {preparationCreateOrderRequest(new String[]{GRAY_COLOUR})},
                {preparationCreateOrderRequest(new String[]{GRAY_COLOUR, BLACK_COLOUR})},
                {preparationCreateOrderRequest(new String[]{})},
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Test create order")
    public void createOrderTest() {
        Response response = createOrder(request);
        response.then().assertThat().body("track", notNullValue())
                .and()
                .statusCode(201);
    }

}
