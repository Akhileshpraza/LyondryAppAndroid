package com.lyondry.lyondry.Modals;

public class LocalResponse {
    int uid;
    int orderId;
    String image;

//
    public LocalResponse(int uid, int orderId ,String image) {
        this.image = image;
        this.uid = uid;
        this.orderId =orderId;
    }
//
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalResponse(String image, int uid) {
        this.image = image;
        this.uid = uid;

    }

}
