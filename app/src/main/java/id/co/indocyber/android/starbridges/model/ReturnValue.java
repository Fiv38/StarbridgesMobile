
package id.co.indocyber.android.starbridges.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReturnValue implements Serializable, Parcelable
{

    @SerializedName("LogDate")
    @Expose
    private String logDate;
    @SerializedName("LogTime")
    @Expose
    private String logTime;
    @SerializedName("LocationName")
    @Expose
    private String locationName;
    @SerializedName("LocationAddress")
    @Expose
    private String locationAddress;
    @SerializedName("LogType")
    @Expose
    private String logType;
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
    private final static long serialVersionUID = -6817603274625480850L;

    protected ReturnValue(Parcel in) {
        this.logDate = ((String) in.readValue((String.class.getClassLoader())));
        this.logTime = ((String) in.readValue((String.class.getClassLoader())));
        this.locationName = ((String) in.readValue((String.class.getClassLoader())));
        this.locationAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.logType = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ReturnValue() {
    }

    /**
     * 
     * @param logTime
     * @param logDate
     * @param locationName
     * @param logType
     * @param locationAddress
     */
    public ReturnValue(String logDate, String logTime, String locationName, String locationAddress, String logType) {
        super();
        this.logDate = logDate;
        this.logTime = logTime;
        this.locationName = locationName;
        this.locationAddress = locationAddress;
        this.logType = logType;
    }

    public String getLogDate() {
        return logDate;
    }

    public void setLogDate(String logDate) {
        this.logDate = logDate;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(logDate);
        dest.writeValue(logTime);
        dest.writeValue(locationName);
        dest.writeValue(locationAddress);
        dest.writeValue(logType);
    }

    public int describeContents() {
        return  0;
    }

}
