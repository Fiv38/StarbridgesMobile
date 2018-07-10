
package id.co.indocyber.android.starbridges.model.medicalrequestconfirmation;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MedicalRequestConfirmation implements Serializable, Parcelable
{

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
    public final static Creator<MedicalRequestConfirmation> CREATOR = new Creator<MedicalRequestConfirmation>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MedicalRequestConfirmation createFromParcel(Parcel in) {
            return new MedicalRequestConfirmation(in);
        }

        public MedicalRequestConfirmation[] newArray(int size) {
            return (new MedicalRequestConfirmation[size]);
        }

    }
    ;
    private final static long serialVersionUID = -16720659679272892L;

    protected MedicalRequestConfirmation(Parcel in) {
        this.customField = ((CustomField) in.readValue((CustomField.class.getClassLoader())));
        this.returnValue = ((ReturnValue) in.readValue((ReturnValue.class.getClassLoader())));
        this.isSucceed = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public MedicalRequestConfirmation() {
    }

    /**
     *
     * @param message
     * @param isSucceed
     * @param customField
     * @param returnValue
     */
    public MedicalRequestConfirmation(CustomField customField, ReturnValue returnValue, Boolean isSucceed, String message) {
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
