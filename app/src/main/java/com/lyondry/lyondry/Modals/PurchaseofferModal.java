package com.lyondry.lyondry.Modals;

public class PurchaseofferModal {
    private String offer;
    private String discount;
    private String upto;

    public PurchaseofferModal(String offer, String discount, String upto) {
        this.offer = offer;
        this.discount = discount;
        this.upto = upto;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getUpto() {
        return upto;
    }

    public void setUpto(String upto) {
        this.upto = upto;
    }
}
