package com.lyondry.lyondry.Modals.SelectItem;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelecteItem_data {
    @SerializedName("ItemId")
    @Expose
    private Integer itemId;
    @SerializedName("ItemCode")
    @Expose
    private String itemCode;
    @SerializedName("ItemName")
    @Expose
    private String itemName;
    @SerializedName("ItemGroupId")
    @Expose
    private Integer itemGroupId;
    @SerializedName("ItemGroupName")
    @Expose
    private String itemGroupName;
    @SerializedName("ItemUomId")
    @Expose
    private Integer itemUomId;
    @SerializedName("ItemUomName")
    @Expose
    private String itemUomName;
    @SerializedName("SessionID")
    @Expose
    private Object sessionID;
    @SerializedName("DateTime")
    @Expose
    private String dateTime;
    @SerializedName("TimeStamp")
    @Expose
    private String timeStamp;
    @SerializedName("EntryStatus")
    @Expose
    private Object entryStatus;
    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemGroupId() {
        return itemGroupId;
    }

    public void setItemGroupId(Integer itemGroupId) {
        this.itemGroupId = itemGroupId;
    }

    public String getItemGroupName() {
        return itemGroupName;
    }

    public void setItemGroupName(String itemGroupName) {
        this.itemGroupName = itemGroupName;
    }

    public Integer getItemUomId() {
        return itemUomId;
    }

    public void setItemUomId(Integer itemUomId) {
        this.itemUomId = itemUomId;
    }

    public String getItemUomName() {
        return itemUomName;
    }

    public void setItemUomName(String itemUomName) {
        this.itemUomName = itemUomName;
    }

    public Object getSessionID() {
        return sessionID;
    }

    public void setSessionID(Object sessionID) {
        this.sessionID = sessionID;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Object getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(Object entryStatus) {
        this.entryStatus = entryStatus;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
