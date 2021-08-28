package com.lyondry.lyondry.Modals;

public class InviceModal {

    String ServiceName;
    String Hsn_Sac;
    String Uom;
    String Quantity;
    String Price;
    String Taxable;

    public InviceModal(String serviceName, String hsn_Sac, String uom, String quantity, String price, String taxable) {
        ServiceName = serviceName;
        Hsn_Sac = hsn_Sac;
        Uom = uom;
        Quantity = quantity;
        Price = price;
        Taxable = taxable;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public String getHsn_Sac() {
        return Hsn_Sac;
    }

    public void setHsn_Sac(String hsn_Sac) {
        Hsn_Sac = hsn_Sac;
    }

    public String getUom() {
        return Uom;
    }

    public void setUom(String uom) {
        Uom = uom;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getTaxable() {
        return Taxable;
    }

    public void setTaxable(String taxable) {
        Taxable = taxable;
    }
}
