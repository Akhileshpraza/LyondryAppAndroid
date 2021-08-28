package com.lyondry.lyondry.Modals;

public class servicesModal {
    private int product_Img;
    private String product_name;

    public servicesModal(int product_Img, String product_name) {
        this.product_Img = product_Img;
        this.product_name = product_name;
    }

    public int getProduct_Img() {
        return product_Img;
    }

    public void setProduct_Img(int product_Img) {
        this.product_Img = product_Img;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
}
