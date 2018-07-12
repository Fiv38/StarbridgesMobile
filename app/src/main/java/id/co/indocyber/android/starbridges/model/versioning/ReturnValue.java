
package id.co.indocyber.android.starbridges.model.versioning;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReturnValue implements Serializable, Parcelable
{

    @SerializedName("VersionCode")
    @Expose
    private String versionCode;
    @SerializedName("VersionName")
    @Expose
    private String versionName;
    public final static Parcelable.Creator<ReturnValue> CREATOR = new Creator<ReturnValue>() {


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
    private final static long serialVersionUID = 566140376695196302L;

    protected ReturnValue(Parcel in) {
        this.versionCode = ((String) in.readValue((String.class.getClassLoader())));
        this.versionName = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ReturnValue() {
    }

    /**
     * 
     * @param versionCode
     * @param versionName
     */
    public ReturnValue(String versionCode, String versionName) {
        super();
        this.versionCode = versionCode;
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public ReturnValue withVersionCode(String versionCode) {
        this.versionCode = versionCode;
        return this;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public ReturnValue withVersionName(String versionName) {
        this.versionName = versionName;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(versionCode);
        dest.writeValue(versionName);
    }

    public int describeContents() {
        return  0;
    }

}
