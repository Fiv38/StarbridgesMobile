
package com.example.android.starbridges.model.requestconfirmation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestConfirmation {

    @SerializedName("CustomField")
    @Expose
    private CustomField customField;
    @SerializedName("ReturnValue")
    @Expose
    private ReturnValue returnValue;
    @SerializedName("isSucceed")
    @Expose
    private Boolean isSucceed;
    @SerializedName("message")
    @Expose
    private String message;

    public CustomField getCustomField() {
        return customField;
    }

    public void setCustomField(CustomField customField) {
        this.customField = customField;
    }

    public ReturnValue getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(ReturnValue returnValue) {
        this.returnValue = returnValue;
    }

    public Boolean getIsSucceed() {
        return isSucceed;
    }

    public void setIsSucceed(Boolean isSucceed) {
        this.isSucceed = isSucceed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
