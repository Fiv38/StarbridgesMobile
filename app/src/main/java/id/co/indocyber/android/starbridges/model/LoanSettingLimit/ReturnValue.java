
package id.co.indocyber.android.starbridges.model.LoanSettingLimit;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReturnValue implements Serializable, Parcelable
{

    @SerializedName("NameLoanSetting")
    @Expose
    private String nameLoanSetting;
    @SerializedName("Limit")
    @Expose
    private String limit;
    public final static Creator<ReturnValue> CREATOR = new Creator<ReturnValue>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ReturnValue createFromParcel(Parcel in) {
            return new ReturnValue(in);
        }

        public ReturnValue[] newArray(int size) {
            return (new ReturnValue[size]);
        }

    }
    ;
    private final static long serialVersionUID = 2178125734310890756L;

    protected ReturnValue(Parcel in) {
        this.nameLoanSetting = ((String) in.readValue((String.class.getClassLoader())));
        this.limit = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ReturnValue() {
    }

    /**
     * 
     * @param limit
     * @param nameLoanSetting
     */
    public ReturnValue(String nameLoanSetting, String limit) {
        super();
        this.nameLoanSetting = nameLoanSetting;
        this.limit = limit;
    }

    public String getNameLoanSetting() {
        return nameLoanSetting;
    }

    public void setNameLoanSetting(String nameLoanSetting) {
        this.nameLoanSetting = nameLoanSetting;
    }

    public ReturnValue withNameLoanSetting(String nameLoanSetting) {
        this.nameLoanSetting = nameLoanSetting;
        return this;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public ReturnValue withLimit(String limit) {
        this.limit = limit;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(nameLoanSetting);
        dest.writeValue(limit);
    }

    public int describeContents() {
        return  0;
    }

}
