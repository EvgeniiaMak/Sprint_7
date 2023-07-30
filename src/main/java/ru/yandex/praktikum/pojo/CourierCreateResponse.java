package ru.yandex.praktikum.pojo;

public class CourierCreateResponse {
    private boolean ok;

    public CourierCreateResponse(boolean ok) {
        this.ok = ok;
    }

    public CourierCreateResponse() {
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }


}
