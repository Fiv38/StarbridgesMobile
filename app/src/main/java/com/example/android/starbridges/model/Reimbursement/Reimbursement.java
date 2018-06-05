
package com.example.android.starbridges.model.Reimbursement;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.example.android.starbridges.model.Reimbursement.CustomField;
import com.example.android.starbridges.model.Reimbursement.ReturnValue;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reimbursement implements Serializable, Parcelable
{

    @SerializedName("CustomField")
    @Expose
    private com.example.android.starbridges.model.Reimbursement.CustomField customField;
    @SerializedName("ReturnValue")
    @Expose
    private List<ReturnValue> returnValue = null;
    @SerializedName("isSucceed")
    @Expose
    private boolean isSucceed;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Creator<Reimbursement> CREATOR = new Creator<Reimbursement>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Reimbursement createFromParcel(Parcel in) {
            return new Reimbursement(in);
        }

        public Reimbursement[] newArray(int size) {
            return (new Reimbursement[size]);
        }

    }
    ;
    private final static long serialVersionUID = 2870342627210960311L;

    protected Reimbursement(Parcel in) {
        this.customField = ((com.example.android.starbridges.model.Reimbursement.CustomField) in.readValue((com.example.android.starbridges.model.Reimbursement.CustomField.class.getClassLoader())));
        in.readList(this.returnValue, (ReturnValue.class.getClassLoader()));
        this.isSucceed = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Reimbursement() {
    }

    /**
     * 
     * @param message
     * @param isSucceed
     * @param customField
     * @param returnValue
     */
    public Reimbursement(com.example.android.starbridges.model.Reimbursement.CustomField customField, List<ReturnValue> returnValue, boolean isSucceed, String message) {
        super();
        this.customField = customField;
        this.returnValue = returnValue;
        this.isSucceed = isSucceed;
        this.message = message;
    }

    public com.example.android.starbridges.model.Reimbursement.CustomField getCustomField() {
        return customField;
    }

    public void setCustomField(com.example.android.starbridges.model.Reimbursement.CustomField customField) {
        this.customField = customField;
    }

    public Reimbursement withCustomField(CustomField customField) {
        this.customField = customField;
        return this;
    }

    public List<ReturnValue> getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(List<ReturnValue> returnValue) {
        this.returnValue = returnValue;
    }

    public Reimbursement withReturnValue(List<ReturnValue> returnValue) {
        this.returnValue = returnValue;
        return this;
    }

    public boolean isIsSucceed() {
        return isSucceed;
    }

    public void setIsSucceed(boolean isSucceed) {
        this.isSucceed = isSucceed;
    }

    public Reimbursement withIsSucceed(boolean isSucceed) {
        this.isSucceed = isSucceed;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Reimbursement withMessage(String message) {
        this.message = message;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(customField);
        dest.writeList(returnValue);
        dest.writeValue(isSucceed);
        dest.writeValue(message);
    }

    public int describeContents() {
        return  0;
    }

}
