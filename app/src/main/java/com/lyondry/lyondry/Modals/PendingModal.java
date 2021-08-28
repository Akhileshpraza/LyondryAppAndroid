package com.lyondry.lyondry.Modals;

public class PendingModal {
    private String invoiceNo;
    private String date;
    private String amount;

    public PendingModal(String invoiceNo, String date, String amount) {
        this.invoiceNo = invoiceNo;
        this.date = date;
        this.amount = amount;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
