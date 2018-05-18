
package com.example.android.starbridges.model.ListAttendaceCorrection;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListAttendanceCorrection implements Serializable, Parcelable
{

    @SerializedName("CustomField")
    @Expose
    private CustomField customField;
    @SerializedName("ReturnValue")
    @Expose
    private List<ReturnValue> returnValue = null;
    @SerializedName("isSucceed")
    @Expose
    private boolean isSucceed;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<ListAttendanceCorrection> CREATOR = new Creator<ListAttendanceCorrection>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ListAttendanceCorrection createFromParcel(Parcel in) {
            return new ListAttendanceCorrection(in);
        }

        public ListAttendanceCorrection[] newArray(int size) {
            return (new ListAttendanceCorrection[size]);
        }

    }
    ;
    private final static long serialVersionUID = -4841643344840622019L;

    protected ListAttendanceCorrection(Parcel in) {
        this.customField = ((CustomField) in.readValue((CustomField.class.getClassLoader())));
        in.readList(this.returnValue, (com.example.android.starbridges.model.ListAttendaceCorrection.ReturnValue.class.getClassLoader()));
        this.isSucceed = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ListAttendanceCorrection() {
    }

    /**
     * 
     * @param message
     * @param isSucceed
     * @param customField
     * @param returnValue
     */
    public ListAttendanceCorrection(CustomField customField, List<ReturnValue> returnValue, boolean isSucceed, String message) {
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

    public ListAttendanceCorrection withCustomField(CustomField customField) {
        this.customField = customField;
        return this;
    }

    public List<ReturnValue> getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(List<ReturnValue> returnValue) {
        this.returnValue = returnValue;
    }

    public ListAttendanceCorrection withReturnValue(List<ReturnValue> returnValue) {
        this.returnValue = returnValue;
        return this;
    }

    public boolean isIsSucceed() {
        return isSucceed;
    }

    public void setIsSucceed(boolean isSucceed) {
        this.isSucceed = isSucceed;
    }

    public ListAttendanceCorrection withIsSucceed(boolean isSucceed) {
        this.isSucceed = isSucceed;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ListAttendanceCorrection withMessage(String message) {
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
