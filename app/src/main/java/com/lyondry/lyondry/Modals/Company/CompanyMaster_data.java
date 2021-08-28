package com.lyondry.lyondry.Modals.Company;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyMaster_data {

    @SerializedName("CompanyCode")
    @Expose
    private String companyCode;
    @SerializedName("CompanyName")
    @Expose
    private String companyName;
    @SerializedName("CompanyAddress")
    @Expose
    private String companyAddress;
    @SerializedName("CompanyEmailId")
    @Expose
    private String companyEmailId;
    @SerializedName("CompanyContactPerson")
    @Expose
    private String companyContactPerson;
    @SerializedName("CompanyContactPersonNo")
    @Expose
    private String companyContactPersonNo;
    @SerializedName("CompanyGstNo")
    @Expose
    private String companyGstNo;
    @SerializedName("CompanyLogo")
    @Expose
    private Object companyLogo;
    @SerializedName("CompanyLocation")
    @Expose
    private String companyLocation;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyEmailId() {
        return companyEmailId;
    }

    public void setCompanyEmailId(String companyEmailId) {
        this.companyEmailId = companyEmailId;
    }

    public String getCompanyContactPerson() {
        return companyContactPerson;
    }

    public void setCompanyContactPerson(String companyContactPerson) {
        this.companyContactPerson = companyContactPerson;
    }

    public String getCompanyContactPersonNo() {
        return companyContactPersonNo;
    }

    public void setCompanyContactPersonNo(String companyContactPersonNo) {
        this.companyContactPersonNo = companyContactPersonNo;
    }

    public String getCompanyGstNo() {
        return companyGstNo;
    }

    public void setCompanyGstNo(String companyGstNo) {
        this.companyGstNo = companyGstNo;
    }

    public Object getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(Object companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }

}
