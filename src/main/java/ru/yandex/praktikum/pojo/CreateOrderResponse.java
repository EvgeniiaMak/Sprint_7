package ru.yandex.praktikum.pojo;

public class CreateOrderResponse {

    private int track;

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    public CreateOrderResponse(int track) {
        this.track = track;
    }
}
