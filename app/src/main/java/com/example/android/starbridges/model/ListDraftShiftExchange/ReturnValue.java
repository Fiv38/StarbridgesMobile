
package com.example.android.starbridges.model.ListDraftShiftExchange;

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
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("Shift")
    @Expose
    private String shift;
    @SerializedName("Notes")
    @Expose
    private String notes;
    @SerializedName("EmployeeName")
    @Expose
    private String employeeName;
    @SerializedName("EmployeeID")
    @Expose
    private Integer employeeID;
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
    private final static long serialVersionUID = -3297128146428372544L;

    protected ReturnValue(Parcel in) {
        this.iD = ((String) in.readValue((String.class.getClassLoader())));
        this.date = ((String) in.readValue((String.class.getClassLoader())));
        this.shift = ((String) in.readValue((String.class.getClassLoader())));
        this.notes = ((String) in.readValue((String.class.getClassLoader())));
        this.employeeName = ((String) in.readValue((String.class.getClassLoader())));
        this.employeeID = ((Integer) in.readValue((Integer.class.getClassLoader())));
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
     * @param employeeID
     * @param fullAccess
     * @param notes
     * @param date
     * @param iD
     * @param shift
     * @param exclusionFields
     * @param employeeName
     */
    public ReturnValue(String iD, String date, String shift, String notes, String employeeName, Integer employeeID, Boolean fullAccess, List<Object> exclusionFields, String accessibilityAttribute) {
        super();
        this.iD = iD;
        this.date = date;
        this.shift = shift;
        this.notes = notes;
        this.employeeName = employeeName;
        this.employeeID = employeeID;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
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
        dest.writeValue(date);
        dest.writeValue(shift);
        dest.writeValue(notes);
        dest.writeValue(employeeName);
        dest.writeValue(employeeID);
        dest.writeValue(fullAccess);
        dest.writeList(exclusionFields);
        dest.writeValue(accessibilityAttribute);
    }

    public int describeContents() {
        return  0;
    }

}
