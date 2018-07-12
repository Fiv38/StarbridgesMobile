
package id.co.indocyber.android.starbridges.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Authentication implements Serializable, Parcelable
{
    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("token_type")
    @Expose
    private String tokenType;
    @SerializedName("expires_in")
    @Expose
    private int expiresIn;
    @SerializedName("as:client_id")
    @Expose
    private String asClientId;
    @SerializedName("loginName")
    @Expose
    private String loginName;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("nik")
    @Expose
    private String nik;
    @SerializedName("employeeID")
    @Expose
    private String employeeID;
    @SerializedName("LocationID")
    @Expose
    private String locationID;
    @SerializedName("Location")
    @Expose
    private String location;
    @SerializedName(".issued")
    @Expose
    private String issued;
    @SerializedName(".expires")
    @Expose
    private String expires;
    @SerializedName("AttendancePrivilege")
    @Expose
    private String attendancePrivilege;
    public final static Creator<Authentication> CREATOR = new Creator<Authentication>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Authentication createFromParcel(Parcel in) {
            return new Authentication(in);
        }

        public Authentication[] newArray(int size) {
            return (new Authentication[size]);
        }

    }
            ;
    private final static long serialVersionUID = 8113504589424797755L;

    protected Authentication(Parcel in) {
        this.accessToken = ((String) in.readValue((String.class.getClassLoader())));
        this.tokenType = ((String) in.readValue((String.class.getClassLoader())));
        this.expiresIn = ((int) in.readValue((int.class.getClassLoader())));
        this.asClientId = ((String) in.readValue((String.class.getClassLoader())));
        this.loginName = ((String) in.readValue((String.class.getClassLoader())));
        this.fullName = ((String) in.readValue((String.class.getClassLoader())));
        this.nik = ((String) in.readValue((String.class.getClassLoader())));
        this.employeeID = ((String) in.readValue((String.class.getClassLoader())));
        this.locationID = ((String) in.readValue((String.class.getClassLoader())));
        this.location = ((String) in.readValue((String.class.getClassLoader())));
        this.issued = ((String) in.readValue((String.class.getClassLoader())));
        this.expires = ((String) in.readValue((String.class.getClassLoader())));
        this.attendancePrivilege = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Authentication() {
    }

    /**
     *
     * @param employeeID
     * @param expires
     * @param tokenType
     * @param accessToken
     * @param location
     * @param expiresIn
     * @param issued
     * @param locationID
     * @param fullName
     * @param nik
     * @param loginName
     * @param asClientId
     */
    public Authentication(String accessToken, String tokenType, int expiresIn, String asClientId, String loginName, String fullName, String nik, String employeeID, String locationID, String location, String issued, String expires, String attendancePrivilege) {
        super();
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.asClientId = asClientId;
        this.loginName = loginName;
        this.fullName = fullName;
        this.nik = nik;
        this.employeeID = employeeID;
        this.locationID = locationID;
        this.location = location;
        this.issued = issued;
        this.expires = expires;
        this.attendancePrivilege = attendancePrivilege;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Authentication withAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Authentication withTokenType(String tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Authentication withExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public String getAsClientId() {
        return asClientId;
    }

    public void setAsClientId(String asClientId) {
        this.asClientId = asClientId;
    }

    public Authentication withAsClientId(String asClientId) {
        this.asClientId = asClientId;
        return this;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Authentication withLoginName(String loginName) {
        this.loginName = loginName;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Authentication withFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public Authentication withNik(String nik) {
        this.nik = nik;
        return this;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public Authentication withEmployeeID(String employeeID) {
        this.employeeID = employeeID;
        return this;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public Authentication withLocationID(String locationID) {
        this.locationID = locationID;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Authentication withLocation(String location) {
        this.location = location;
        return this;
    }

    public String getIssued() {
        return issued;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public Authentication withIssued(String issued) {
        this.issued = issued;
        return this;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public Authentication withExpires(String expires) {
        this.expires = expires;
        return this;
    }

    public String getAttendancePrivilege() {
        return attendancePrivilege;
    }

    public void setAttendancePrivilege(String attendancePrivilege) {
        this.attendancePrivilege = attendancePrivilege;
    }

    public Authentication withAttendancePrivilege(String attendancePrivilege) {
        this.attendancePrivilege = attendancePrivilege;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(accessToken);
        dest.writeValue(tokenType);
        dest.writeValue(expiresIn);
        dest.writeValue(asClientId);
        dest.writeValue(loginName);
        dest.writeValue(fullName);
        dest.writeValue(nik);
        dest.writeValue(employeeID);
        dest.writeValue(locationID);
        dest.writeValue(location);
        dest.writeValue(issued);
        dest.writeValue(expires);
        dest.writeValue(attendancePrivilege);
    }

    public int describeContents() {
        return  0;
    }
}
