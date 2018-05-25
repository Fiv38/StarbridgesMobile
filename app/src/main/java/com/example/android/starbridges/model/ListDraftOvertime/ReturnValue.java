
package com.example.android.starbridges.model.ListDraftOvertime;

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
    @SerializedName("RequestDate")
    @Expose
    private String requestDate;
    @SerializedName("OvertimeDate")
    @Expose
    private String overtimeDate;
    @SerializedName("Start")
    @Expose
    private String start;
    @SerializedName("End")
    @Expose
    private String end;
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
    private final static long serialVersionUID = -4242046725834446682L;

    protected ReturnValue(Parcel in) {
        this.iD = ((String) in.readValue((String.class.getClassLoader())));
        this.requestDate = ((String) in.readValue((String.class.getClassLoader())));
        this.overtimeDate = ((String) in.readValue((String.class.getClassLoader())));
        this.start = ((String) in.readValue((String.class.getClassLoader())));
        this.end = ((String) in.readValue((String.class.getClassLoader())));
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
     * @param accessibilityAttribute
     * @param start
     * @param fullAccess
     * @param requestDate
     * @param iD
     * @param end
     * @param exclusionFields
     * @param overtimeDate
     */
    public ReturnValue(String iD, String requestDate, String overtimeDate, String start, String end, Boolean fullAccess, List<Object> exclusionFields, String accessibilityAttribute) {
        super();
        this.iD = iD;
        this.requestDate = requestDate;
        this.overtimeDate = overtimeDate;
        this.start = start;
        this.end = end;
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

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getOvertimeDate() {
        return overtimeDate;
    }

    public void setOvertimeDate(String overtimeDate) {
        this.overtimeDate = overtimeDate;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
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
        dest.writeValue(requestDate);
        dest.writeValue(overtimeDate);
        dest.writeValue(start);
        dest.writeValue(end);
        dest.writeValue(fullAccess);
        dest.writeList(exclusionFields);
        dest.writeValue(accessibilityAttribute);
    }

    public int describeContents() {
        return  0;
    }

}
