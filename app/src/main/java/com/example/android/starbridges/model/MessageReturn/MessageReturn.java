
package com.example.android.starbridges.model.MessageReturn;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageReturn implements Serializable, Parcelable
{

    @SerializedName("CustomField")
    @Expose
    private CustomField customField;
    @SerializedName("ReturnValue")
    @Expose
    private Object returnValue;
    @SerializedName("isSucceed")
    @Expose
    private boolean isSucceed;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Creator<MessageReturn> CREATOR = new Creator<MessageReturn>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MessageReturn createFromParcel(Parcel in) {
            return new MessageReturn(in);
        }

        public MessageReturn[] newArray(int size) {
            return (new MessageReturn[size]);
        }

    }
    ;
    private final static long serialVersionUID = -1141720627055148297L;

    protected MessageReturn(Parcel in) {
        this.customField = ((CustomField) in.readValue((CustomField.class.getClassLoader())));
        this.returnValue = ((Object) in.readValue((Object.class.getClassLoader())));
        this.isSucceed = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public MessageReturn() {
    }

    /**
     * 
     * @param message
     * @param isSucceed
     * @param customField
     * @param returnValue
     */
    public MessageReturn(CustomField customField, Object returnValue, boolean isSucceed, String message) {
        super();
        this.customField = customField;
        this.returnValue = returnValue;
        this.isSucceed = isSucceed;
        this.message = message;
    }

    public CustomField getCustomField() {
        return customField;
    }

    public void setCustomField(CustomField customField) {
        this.customField = customField;
    }

    public MessageReturn withCustomField(CustomField customField) {
        this.customField = customField;
        return this;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }

    public MessageReturn withReturnValue(Object returnValue) {
        this.returnValue = returnValue;
        return this;
    }

    public boolean isIsSucceed() {
        return isSucceed;
    }

    public void setIsSucceed(boolean isSucceed) {
        this.isSucceed = isSucceed;
    }

    public MessageReturn withIsSucceed(boolean isSucceed) {
        this.isSucceed = isSucceed;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageReturn withMessage(String message) {
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
