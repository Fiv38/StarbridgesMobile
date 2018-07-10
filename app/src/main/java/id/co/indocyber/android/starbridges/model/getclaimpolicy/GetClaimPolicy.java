
package id.co.indocyber.android.starbridges.model.getclaimpolicy;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetClaimPolicy implements Serializable, Parcelable
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
    public final static Creator<GetClaimPolicy> CREATOR = new Creator<GetClaimPolicy>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GetClaimPolicy createFromParcel(Parcel in) {
            return new GetClaimPolicy(in);
        }

        public GetClaimPolicy[] newArray(int size) {
            return (new GetClaimPolicy[size]);
        }

    }
    ;
    private final static long serialVersionUID = 2244699327209150165L;

    protected GetClaimPolicy(Parcel in) {
        this.customField = ((CustomField) in.readValue((CustomField.class.getClassLoader())));
        //in.readList(this.returnValue, (com.example.android.starbridges.activity.getclaimpolicy.ReturnValue.class.getClassLoader()));
        in.readList(this.returnValue, (ReturnValue.class.getClassLoader()));
        this.isSucceed = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public GetClaimPolicy() {
    }

    /**
     *
     * @param message
     * @param isSucceed
     * @param customField
     * @param returnValue
     */
    public GetClaimPolicy(CustomField customField, List<ReturnValue> returnValue, Boolean isSucceed, String message) {
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
