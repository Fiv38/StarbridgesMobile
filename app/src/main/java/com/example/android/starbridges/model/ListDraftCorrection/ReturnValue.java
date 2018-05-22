
package com.example.android.starbridges.model.ListDraftCorrection;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReturnValue implements Serializable, Parcelable
{

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("LogDate")
    @Expose
    private String logDate;
    @SerializedName("ActualLogIn")
    @Expose
    private String actualLogIn;
    @SerializedName("ActualLogOut")
    @Expose
    private String actualLogOut;
    @SerializedName("ActualBreakStart")
    @Expose
    private String actualBreakStart;
    @SerializedName("ActualBreakEnd")
    @Expose
    private String actualBreakEnd;
    @SerializedName("ActualOvertimeIn")
    @Expose
    private String actualOvertimeIn;
    @SerializedName("ActualOvertimeOut")
    @Expose
    private String actualOvertimeOut;
    @SerializedName("FullAccess")
    @Expose
    private boolean fullAccess;
    @SerializedName("ExclusionFields")
    @Expose
    private List<Object> exclusionFields = null;
    @SerializedName("AccessibilityAttribute")
    @Expose
    private String accessibilityAttribute;
    private boolean isSelected = false;

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected() {
        return isSelected;
    }

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
    private final static long serialVersionUID = 426874262096376276L;

    protected ReturnValue(Parcel in) {
        this.iD = ((String) in.readValue((String.class.getClassLoader())));
        this.logDate = ((String) in.readValue((String.class.getClassLoader())));
        this.actualLogIn = ((String) in.readValue((String.class.getClassLoader())));
        this.actualLogOut = ((String) in.readValue((String.class.getClassLoader())));
        this.actualBreakStart = ((String) in.readValue((String.class.getClassLoader())));
        this.actualBreakEnd = ((String) in.readValue((String.class.getClassLoader())));
        this.actualOvertimeIn = ((String) in.readValue((String.class.getClassLoader())));
        this.actualOvertimeOut = ((String) in.readValue((String.class.getClassLoader())));
        this.fullAccess = ((boolean) in.readValue((boolean.class.getClassLoader())));
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
     * @param actualOvertimeIn
     * @param fullAccess
     * @param actualBreakEnd
     * @param actualLogIn
     * @param actualOvertimeOut
     * @param actualLogOut
     * @param logDate
     * @param actualBreakStart
     * @param iD
     * @param exclusionFields
     */
    public ReturnValue(String iD, String logDate, String actualLogIn, String actualLogOut, String actualBreakStart, String actualBreakEnd, String actualOvertimeIn, String actualOvertimeOut, boolean fullAccess, List<Object> exclusionFields, String accessibilityAttribute) {
        super();
        this.iD = iD;
        this.logDate = logDate;
        this.actualLogIn = actualLogIn;
        this.actualLogOut = actualLogOut;
        this.actualBreakStart = actualBreakStart;
        this.actualBreakEnd = actualBreakEnd;
        this.actualOvertimeIn = actualOvertimeIn;
        this.actualOvertimeOut = actualOvertimeOut;
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

    public ReturnValue withID(String iD) {
        this.iD = iD;
        return this;
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

    public String getActualLogIn() {
        return actualLogIn;
    }

    public void setActualLogIn(String actualLogIn) {
        this.actualLogIn = actualLogIn;
    }

    public ReturnValue withActualLogIn(String actualLogIn) {
        this.actualLogIn = actualLogIn;
        return this;
    }

    public String getActualLogOut() {
        return actualLogOut;
    }

    public void setActualLogOut(String actualLogOut) {
        this.actualLogOut = actualLogOut;
    }

    public ReturnValue withActualLogOut(String actualLogOut) {
        this.actualLogOut = actualLogOut;
        return this;
    }

    public String getActualBreakStart() {
        return actualBreakStart;
    }

    public void setActualBreakStart(String actualBreakStart) {
        this.actualBreakStart = actualBreakStart;
    }

    public ReturnValue withActualBreakStart(String actualBreakStart) {
        this.actualBreakStart = actualBreakStart;
        return this;
    }

    public String getActualBreakEnd() {
        return actualBreakEnd;
    }

    public void setActualBreakEnd(String actualBreakEnd) {
        this.actualBreakEnd = actualBreakEnd;
    }

    public ReturnValue withActualBreakEnd(String actualBreakEnd) {
        this.actualBreakEnd = actualBreakEnd;
        return this;
    }

    public String getActualOvertimeIn() {
        return actualOvertimeIn;
    }

    public void setActualOvertimeIn(String actualOvertimeIn) {
        this.actualOvertimeIn = actualOvertimeIn;
    }

    public ReturnValue withActualOvertimeIn(String actualOvertimeIn) {
        this.actualOvertimeIn = actualOvertimeIn;
        return this;
    }

    public String getActualOvertimeOut() {
        return actualOvertimeOut;
    }

    public void setActualOvertimeOut(String actualOvertimeOut) {
        this.actualOvertimeOut = actualOvertimeOut;
    }

    public ReturnValue withActualOvertimeOut(String actualOvertimeOut) {
        this.actualOvertimeOut = actualOvertimeOut;
        return this;
    }

    public boolean isFullAccess() {
        return fullAccess;
    }

    public void setFullAccess(boolean fullAccess) {
        this.fullAccess = fullAccess;
    }

    public ReturnValue withFullAccess(boolean fullAccess) {
        this.fullAccess = fullAccess;
        return this;
    }

    public List<Object> getExclusionFields() {
        return exclusionFields;
    }

    public void setExclusionFields(List<Object> exclusionFields) {
        this.exclusionFields = exclusionFields;
    }

    public ReturnValue withExclusionFields(List<Object> exclusionFields) {
        this.exclusionFields = exclusionFields;
        return this;
    }

    public String getAccessibilityAttribute() {
        return accessibilityAttribute;
    }

    public void setAccessibilityAttribute(String accessibilityAttribute) {
        this.accessibilityAttribute = accessibilityAttribute;
    }

    public ReturnValue withAccessibilityAttribute(String accessibilityAttribute) {
        this.accessibilityAttribute = accessibilityAttribute;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(iD);
        dest.writeValue(logDate);
        dest.writeValue(actualLogIn);
        dest.writeValue(actualLogOut);
        dest.writeValue(actualBreakStart);
        dest.writeValue(actualBreakEnd);
        dest.writeValue(actualOvertimeIn);
        dest.writeValue(actualOvertimeOut);
        dest.writeValue(fullAccess);
        dest.writeList(exclusionFields);
        dest.writeValue(accessibilityAttribute);
    }

    public int describeContents() {
        return  0;
    }

}
