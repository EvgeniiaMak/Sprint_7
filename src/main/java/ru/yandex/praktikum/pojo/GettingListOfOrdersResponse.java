package ru.yandex.praktikum.pojo;

public class GettingListOfOrdersResponse {
    private Order[] orders;
    private PageInfo pageInfo;
    private Station[] availableStations;

    public GettingListOfOrdersResponse(Order[] orders, PageInfo pageInfo, Station[] availableStations) {
        this.orders = orders;
        this.pageInfo = pageInfo;
        this.availableStations = availableStations;
    }

    public GettingListOfOrdersResponse() {
    }

    public Order[] getOrders() {
        return orders;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public Station[] getAvailableStations() {
        return availableStations;
    }

    public void setAvailableStations(Station[] availableStations) {
        this.availableStations = availableStations;
    }
}
