package ru.yandex.praktikum.pojo;

public class TrackOrderResponse {

    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public TrackOrderResponse(Order order) {
        this.order = order;
    }
}
