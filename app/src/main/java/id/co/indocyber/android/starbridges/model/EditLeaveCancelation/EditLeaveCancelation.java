
package id.co.indocyber.android.starbridges.model.EditLeaveCancelation;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EditLeaveCancelation implements Serializable, Parcelable
{

    @SerializedName("CustomField")
    @Expose
    private CustomField customField;
    @SerializedName("ReturnValue")
    @Expose
    private ReturnValue returnValue;
    @SerializedName("isSucceed")
    @Expose
    private boolean isSucceed;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Creator<EditLeaveCancelation> CREATOR = new Creator<EditLeaveCancelation>() {


        @SuppressWarnings({
            "unchecked"
        })
        public EditLeaveCancelation createFromParcel(Parcel in) {
            return new EditLeaveCancelation(in);
        }

        public EditLeaveCancelation[] newArray(int size) {
            return (new EditLeaveCancelation[size]);
        }

    }
    ;
    private final static long serialVersionUID = -3303974593981915008L;

    protected EditLeaveCancelation(Parcel in) {
        this.customField = ((CustomField) in.readValue((CustomField.class.getClassLoader())));
        this.returnValue = ((ReturnValue) in.readValue((ReturnValue.class.getClassLoader())));
        this.isSucceed = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public EditLeaveCancelation() {
    }

    /**
     * 
     * @param message
     * @param isSucceed
     * @param customField
     * @param returnValue
     */
    public EditLeaveCancelation(CustomField customField, ReturnValue returnValue, boolean isSucceed, String message) {
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

    public EditLeaveCancelation withCustomField(CustomField customField) {
        this.customField = customField;
        return this;
    }

    public ReturnValue getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(ReturnValue returnValue) {
        this.returnValue = returnValue;
    }

    public EditLeaveCancelation withReturnValue(ReturnValue returnValue) {
        this.returnValue = returnValue;
        return this;
    }

    public boolean isIsSucceed() {
        return isSucceed;
    }

    public void setIsSucceed(boolean isSucceed) {
        this.isSucceed = isSucceed;
    }

    public EditLeaveCancelation withIsSucceed(boolean isSucceed) {
        this.isSucceed = isSucceed;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EditLeaveCancelation withMessage(String message) {
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
