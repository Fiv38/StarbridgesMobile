
package com.example.android.starbridges.model.OLocation;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.example.android.starbridges.model.*;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OLocation implements Serializable, Parcelable
{

    @SerializedName("CustomField")
    @Expose
    private com.example.android.starbridges.model.OLocation.CustomField customField;
    @SerializedName("ReturnValue")
    @Expose
    private List<ReturnValue> returnValue = null;
    @SerializedName("isSucceed")
    @Expose
    private Boolean isSucceed;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Creator<OLocation> CREATOR = new Creator<OLocation>() {


        @SuppressWarnings({
            "unchecked"
        })
        public OLocation createFromParcel(Parcel in) {
            return new OLocation(in);
        }

        public OLocation[] newArray(int size) {
            return (new OLocation[size]);
        }

    }
    ;
    private final static long serialVersionUID = 2633037291548333381L;

    protected OLocation(Parcel in) {
        this.customField = ((com.example.android.starbridges.model.OLocation.CustomField) in.readValue((com.example.android.starbridges.model.OLocation.CustomField.class.getClassLoader())));
        in.readList(this.returnValue, (ReturnValue.class.getClassLoader()));
        this.isSucceed = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public OLocation() {
    }

    /**
     * 
     * @param message
     * @param isSucceed
     * @param customField
     * @param returnValue
     */
    public OLocation(com.example.android.starbridges.model.OLocation.CustomField customField, List<ReturnValue> returnValue, Boolean isSucceed, String message) {
        super();
        this.customField = customField;
        this.returnValue = returnValue;
        this.isSucceed = isSucceed;
        this.message = message;
    }

    public com.example.android.starbridges.model.OLocation.CustomField getCustomField() {
        return customField;
    }

    public void setCustomField(com.example.android.starbridges.model.OLocation.CustomField customField) {
        this.customField = customField;
    }

    public List<ReturnValue> getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(List<ReturnValue> returnValue) {
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
