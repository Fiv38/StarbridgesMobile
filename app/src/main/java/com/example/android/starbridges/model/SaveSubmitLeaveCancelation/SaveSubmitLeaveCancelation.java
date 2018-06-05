
package com.example.android.starbridges.model.SaveSubmitLeaveCancelation;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.example.android.starbridges.model.CustomField;
import com.example.android.starbridges.model.ReturnValue;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveSubmitLeaveCancelation implements Serializable, Parcelable
{

    @SerializedName("CustomField")
    @Expose
    private com.example.android.starbridges.model.CustomField customField;
    @SerializedName("ReturnValue")
    @Expose
    private com.example.android.starbridges.model.ReturnValue returnValue;
    @SerializedName("isSucceed")
    @Expose
    private boolean isSucceed;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Creator<SaveSubmitLeaveCancelation> CREATOR = new Creator<SaveSubmitLeaveCancelation>() {


        @SuppressWarnings({
            "unchecked"
        })
        public SaveSubmitLeaveCancelation createFromParcel(Parcel in) {
            return new SaveSubmitLeaveCancelation(in);
        }

        public SaveSubmitLeaveCancelation[] newArray(int size) {
            return (new SaveSubmitLeaveCancelation[size]);
        }

    }
    ;
    private final static long serialVersionUID = -1722072426326542793L;

    protected SaveSubmitLeaveCancelation(Parcel in) {
        this.customField = ((com.example.android.starbridges.model.CustomField) in.readValue((com.example.android.starbridges.model.CustomField.class.getClassLoader())));
        this.returnValue = ((com.example.android.starbridges.model.ReturnValue) in.readValue((com.example.android.starbridges.model.ReturnValue.class.getClassLoader())));
        this.isSucceed = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public SaveSubmitLeaveCancelation() {
    }

    /**
     * 
     * @param message
     * @param isSucceed
     * @param customField
     * @param returnValue
     */
    public SaveSubmitLeaveCancelation(com.example.android.starbridges.model.CustomField customField, com.example.android.starbridges.model.ReturnValue returnValue, boolean isSucceed, String message) {
        super();
        this.customField = customField;
        this.returnValue = returnValue;
        this.isSucceed = isSucceed;
        this.message = message;
    }

    public com.example.android.starbridges.model.CustomField getCustomField() {
        return customField;
    }

    public void setCustomField(com.example.android.starbridges.model.CustomField customField) {
        this.customField = customField;
    }

    public SaveSubmitLeaveCancelation withCustomField(CustomField customField) {
        this.customField = customField;
        return this;
    }

    public com.example.android.starbridges.model.ReturnValue getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(com.example.android.starbridges.model.ReturnValue returnValue) {
        this.returnValue = returnValue;
    }

    public SaveSubmitLeaveCancelation withReturnValue(ReturnValue returnValue) {
        this.returnValue = returnValue;
        return this;
    }

    public boolean isIsSucceed() {
        return isSucceed;
    }

    public void setIsSucceed(boolean isSucceed) {
        this.isSucceed = isSucceed;
    }

    public SaveSubmitLeaveCancelation withIsSucceed(boolean isSucceed) {
        this.isSucceed = isSucceed;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SaveSubmitLeaveCancelation withMessage(String message) {
        this.message = message;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(customField);
        dest.writeValue(returnValue);
        dest.writeValue(isSucceed);
        dest.writeValue(message);
    }

    public int describeContents() {
        return  0;
    }

}
