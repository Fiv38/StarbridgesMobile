
package com.example.android.starbridges.model.listmedicalclaim;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListMedicalClaim implements Serializable, Parcelable
{

    @SerializedName("CustomField")
    @Expose
    private CustomField customField;
    @SerializedName("ReturnValue")
    @Expose
    private List<ReturnValue> returnValue = null;
    @SerializedName("isSucceed")
    @Expose
    private Boolean isSucceed;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<ListMedicalClaim> CREATOR = new Creator<ListMedicalClaim>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ListMedicalClaim createFromParcel(Parcel in) {
            return new ListMedicalClaim(in);
        }

        public ListMedicalClaim[] newArray(int size) {
            return (new ListMedicalClaim[size]);
        }

    }
    ;
    private final static long serialVersionUID = 7899558373967826459L;

    protected ListMedicalClaim(Parcel in) {
        this.customField = ((CustomField) in.readValue((CustomField.class.getClassLoader())));
        in.readList(this.returnValue, (com.example.android.starbridges.model.listmedicalclaim.ReturnValue.class.getClassLoader()));
        this.isSucceed = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ListMedicalClaim() {
    }

    /**
     * 
     * @param message
     * @param isSucceed
     * @param customField
     * @param returnValue
     */
    public ListMedicalClaim(CustomField customField, List<ReturnValue> returnValue, Boolean isSucceed, String message) {
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
