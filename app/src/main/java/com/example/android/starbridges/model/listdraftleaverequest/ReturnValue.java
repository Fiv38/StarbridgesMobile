
package com.example.android.starbridges.model.listdraftleaverequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReturnValue {

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("RequestDate")
    @Expose
    private String requestDate;
    @SerializedName("LeaveType")
    @Expose
    private String leaveType;
    @SerializedName("TotalUnit")
    @Expose
    private Integer totalUnit;
    @SerializedName("StartLeave")
    @Expose
    private String startLeave;
    @SerializedName("EndLeave")
    @Expose
    private String endLeave;
    @SerializedName("Notes")
    @Expose
    private String notes;
    @SerializedName("FullAccess")
    @Expose
    private Boolean fullAccess;
    @SerializedName("ExclusionFields")
    @Expose
    private List<Object> exclusionFields = null;
    @SerializedName("AccessibilityAttribute")
    @Expose
    private String accessibilityAttribute;

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

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public Integer getTotalUnit() {
        return totalUnit;
    }

    public void setTotalUnit(Integer totalUnit) {
        this.totalUnit = totalUnit;
    }

    public String getStartLeave() {
        return startLeave;
    }

    public void setStartLeave(String startLeave) {
        this.startLeave = startLeave;
    }

    public String getEndLeave() {
        return endLeave;
    }

    public void setEndLeave(String endLeave) {
        this.endLeave = endLeave;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

}
