
package id.co.indocyber.android.starbridges.model.history;

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
    private Object locationName;
    @SerializedName("LocationAddress")
    @Expose
    private Object locationAddress;
    @SerializedName("LogType")
    @Expose
    private String logType;
    @SerializedName("DisplayDate")
    @Expose
    private String displayDate;
    @SerializedName("DisplayTime")
    @Expose
    private String displayTime;
    @SerializedName("Latitude")
    @Expose
    private String latitude;
    @SerializedName("Longitude")
    @Expose
    private String longitude;
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
    private final static long serialVersionUID = 2785902057728072596L;

    protected ReturnValue(Parcel in) {
        this.logDate = ((String) in.readValue((String.class.getClassLoader())));
        this.logTime = ((String) in.readValue((String.class.getClassLoader())));
        this.locationName = ((Object) in.readValue((Object.class.getClassLoader())));
        this.locationAddress = ((Object) in.readValue((Object.class.getClassLoader())));
        this.logType = ((String) in.readValue((String.class.getClassLoader())));
        this.displayDate = ((String) in.readValue((String.class.getClassLoader())));
        this.displayTime = ((String) in.readValue((String.class.getClassLoader())));
        this.latitude = ((String) in.readValue((String.class.getClassLoader())));
        this.longitude = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public ReturnValue() {
    }

    /**
     *
     * @param displayTime
     * @param displayDate
     * @param logTime
     * @param longitude
     * @param logDate
     * @param latitude
     * @param locationName
     * @param logType
     * @param locationAddress
     */
    public ReturnValue(String logDate, String logTime, Object locationName, Object locationAddress, String logType, String displayDate, String displayTime, String latitude, String longitude) {
        super();
        this.logDate = logDate;
        this.logTime = logTime;
        this.locationName = locationName;
        this.locationAddress = locationAddress;
        this.logType = logType;
        this.displayDate = displayDate;
        this.displayTime = displayTime;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLogDate() {
        return logDate;
    }

    public void setLogDate(String logDate) {
        this.logDate = logDate;
    }

    public ReturnValue withLogDate(String logDate) {
        this.logDate = logDate;
        return this;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public ReturnValue withLogTime(String logTime) {
        this.logTime = logTime;
        return this;
    }

    public Object getLocationName() {
        return locationName;
    }

    public void setLocationName(Object locationName) {
        this.locationName = locationName;
    }

    public ReturnValue withLocationName(Object locationName) {
        this.locationName = locationName;
        return this;
    }

    public Object getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(Object locationAddress) {
        this.locationAddress = locationAddress;
    }

    public ReturnValue withLocationAddress(Object locationAddress) {
        this.locationAddress = locationAddress;
        return this;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public ReturnValue withLogType(String logType) {
        this.logType = logType;
        return this;
    }

    public String getDisplayDate() {
        return displayDate;
    }

    public void setDisplayDate(String displayDate) {
        this.displayDate = displayDate;
    }

    public ReturnValue withDisplayDate(String displayDate) {
        this.displayDate = displayDate;
        return this;
    }

    public String getDisplayTime() {
        return displayTime;
    }

    public void setDisplayTime(String displayTime) {
        this.displayTime = displayTime;
    }

    public ReturnValue withDisplayTime(String displayTime) {
        this.displayTime = displayTime;
        return this;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public ReturnValue withLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public ReturnValue withLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(logDate);
        dest.writeValue(logTime);
        dest.writeValue(locationName);
        dest.writeValue(locationAddress);
        dest.writeValue(logType);
        dest.writeValue(displayDate);
        dest.writeValue(displayTime);
        dest.writeValue(latitude);
        dest.writeValue(longitude);
    }

    public int describeContents() {
        return  0;
    }

}
