
package com.example.android.starbridges.model.ListShift;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ReturnValue implements Serializable, Parcelable
{

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("IsNightShift")
    @Expose
    private Boolean isNightShift;
    @SerializedName("LogIn")
    @Expose
    private String logIn;
    @SerializedName("LogOut")
    @Expose
    private String logOut;
    @SerializedName("ShiftLimit")
    @Expose
    private String shiftLimit;
    @SerializedName("OvertimeStart")
    @Expose
    private String overtimeStart;
    @SerializedName("OvertimeEnd")
    @Expose
    private String overtimeEnd;
    @SerializedName("BeginEff")
    @Expose
    private String beginEff;
    @SerializedName("LastEff")
    @Expose
    private Object lastEff;
    @SerializedName("FullAccess")
    @Expose
    private Boolean fullAccess;
    @SerializedName("ExclusionFields")
    @Expose
    private List<Object> exclusionFields = null;
    @SerializedName("AccessibilityAttribute")
    @Expose
    private String accessibilityAttribute;
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
    private final static long serialVersionUID = -7863930260534886864L;

    protected ReturnValue(Parcel in) {
        this.iD = ((String) in.readValue((String.class.getClassLoader())));
        this.code = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.isNightShift = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.logIn = ((String) in.readValue((String.class.getClassLoader())));
        this.logOut = ((String) in.readValue((String.class.getClassLoader())));
        this.shiftLimit = ((String) in.readValue((String.class.getClassLoader())));
        this.overtimeStart = ((String) in.readValue((String.class.getClassLoader())));
        this.overtimeEnd = ((String) in.readValue((String.class.getClassLoader())));
        this.beginEff = ((String) in.readValue((String.class.getClassLoader())));
        this.lastEff = ((Object) in.readValue((Object.class.getClassLoader())));
        this.fullAccess = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.exclusionFields, (Object.class.getClassLoader()));
        this.accessibilityAttribute = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ReturnValue() {
    }

    /**
     * 
     * @param overtimeStart
     * @param accessibilityAttribute
     * @param lastEff
     * @param fullAccess
     * @param shiftLimit
     * @param isNightShift
     * @param name
     * @param beginEff
     * @param overtimeEnd
     * @param logOut
     * @param code
     * @param logIn
     * @param iD
     * @param exclusionFields
     */
    public ReturnValue(String iD, String code, String name, Boolean isNightShift, String logIn, String logOut, String shiftLimit, String overtimeStart, String overtimeEnd, String beginEff, Object lastEff, Boolean fullAccess, List<Object> exclusionFields, String accessibilityAttribute) {
        super();
        this.iD = iD;
        this.code = code;
        this.name = name;
        this.isNightShift = isNightShift;
        this.logIn = logIn;
        this.logOut = logOut;
        this.shiftLimit = shiftLimit;
        this.overtimeStart = overtimeStart;
        this.overtimeEnd = overtimeEnd;
        this.beginEff = beginEff;
        this.lastEff = lastEff;
        this.fullAccess = fullAccess;
        this.exclusionFields = exclusionFields;
        this.accessibilityAttribute = accessibilityAttribute;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsNightShift() {
        return isNightShift;
    }

    public void setIsNightShift(Boolean isNightShift) {
        this.isNightShift = isNightShift;
    }

    public String getLogIn() {
        return logIn;
    }

    public void setLogIn(String logIn) {
        this.logIn = logIn;
    }

    public String getLogOut() {
        return logOut;
    }

    public void setLogOut(String logOut) {
        this.logOut = logOut;
    }

    public String getShiftLimit() {
        return shiftLimit;
    }

    public void setShiftLimit(String shiftLimit) {
        this.shiftLimit = shiftLimit;
    }

    public String getOvertimeStart() {
        return overtimeStart;
    }

    public void setOvertimeStart(String overtimeStart) {
        this.overtimeStart = overtimeStart;
    }

    public String getOvertimeEnd() {
        return overtimeEnd;
    }

    public void setOvertimeEnd(String overtimeEnd) {
        this.overtimeEnd = overtimeEnd;
    }

    public String getBeginEff() {
        return beginEff;
    }

    public void setBeginEff(String beginEff) {
        this.beginEff = beginEff;
    }

    public Object getLastEff() {
        return lastEff;
    }

    public void setLastEff(Object lastEff) {
        this.lastEff = lastEff;
    }

    public Boolean getFullAccess() {
        return fullAccess;
    }

    public void setFullAccess(Boolean fullAccess) {
        this.fullAccess = fullAccess;
    }

    public List<Object> getExclusionFields() {
        return exclusionFields;
    }

    public void setExclusionFields(List<Object> exclusionFields) {
        this.exclusionFields = exclusionFields;
    }

    public String getAccessibilityAttribute() {
        return accessibilityAttribute;
    }

    public void setAccessibilityAttribute(String accessibilityAttribute) {
        this.accessibilityAttribute = accessibilityAttribute;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(iD);
        dest.writeValue(code);
        dest.writeValue(name);
        dest.writeValue(isNightShift);
        dest.writeValue(logIn);
        dest.writeValue(logOut);
        dest.writeValue(shiftLimit);
        dest.writeValue(overtimeStart);
        dest.writeValue(overtimeEnd);
        dest.writeValue(beginEff);
        dest.writeValue(lastEff);
        dest.writeValue(fullAccess);
        dest.writeList(exclusionFields);
        dest.writeValue(accessibilityAttribute);
    }

    public int describeContents() {
        return  0;
    }

}
