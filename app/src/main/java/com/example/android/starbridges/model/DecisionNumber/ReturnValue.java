
package com.example.android.starbridges.model.DecisionNumber;

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
    @SerializedName("DecisionNumber")
    @Expose
    private String decisionNumber;
    @SerializedName("RequestDate")
    @Expose
    private String requestDate;
    @SerializedName("LeaveRequestRuleID")
    @Expose
    private int leaveRequestRuleID;
    @SerializedName("RequestType")
    @Expose
    private String requestType;
    @SerializedName("BalanceDate")
    @Expose
    private Object balanceDate;
    @SerializedName("TotalUnit")
    @Expose
    private String totalUnit;
    @SerializedName("StartLeave")
    @Expose
    private String startLeave;
    @SerializedName("EndLeave")
    @Expose
    private String endLeave;
    @SerializedName("Notes")
    @Expose
    private String notes;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("ApproveDate")
    @Expose
    private Object approveDate;
    @SerializedName("FullAccess")
    @Expose
    private boolean fullAccess;
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
    private final static long serialVersionUID = -5267159012603917148L;

    protected ReturnValue(Parcel in) {
        this.iD = ((String) in.readValue((String.class.getClassLoader())));
        this.decisionNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.requestDate = ((String) in.readValue((String.class.getClassLoader())));
        this.leaveRequestRuleID = ((int) in.readValue((int.class.getClassLoader())));
        this.requestType = ((String) in.readValue((String.class.getClassLoader())));
        this.balanceDate = ((Object) in.readValue((Object.class.getClassLoader())));
        this.totalUnit = ((String) in.readValue((String.class.getClassLoader())));
        this.startLeave = ((String) in.readValue((String.class.getClassLoader())));
        this.endLeave = ((String) in.readValue((String.class.getClassLoader())));
        this.notes = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.approveDate = ((Object) in.readValue((Object.class.getClassLoader())));
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
     * @param endLeave
     * @param accessibilityAttribute
     * @param leaveRequestRuleID
     * @param status
     * @param fullAccess
     * @param requestType
     * @param balanceDate
     * @param iD
     * @param exclusionFields
     * @param totalUnit
     * @param decisionNumber
     * @param startLeave
     * @param approveDate
     * @param requestDate
     * @param notes
     */
    public ReturnValue(String iD, String decisionNumber, String requestDate, int leaveRequestRuleID, String requestType, Object balanceDate, String totalUnit, String startLeave, String endLeave, String notes, String status, Object approveDate, boolean fullAccess, List<Object> exclusionFields, String accessibilityAttribute) {
        super();
        this.iD = iD;
        this.decisionNumber = decisionNumber;
        this.requestDate = requestDate;
        this.leaveRequestRuleID = leaveRequestRuleID;
        this.requestType = requestType;
        this.balanceDate = balanceDate;
        this.totalUnit = totalUnit;
        this.startLeave = startLeave;
        this.endLeave = endLeave;
        this.notes = notes;
        this.status = status;
        this.approveDate = approveDate;
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

    public String getDecisionNumber() {
        return decisionNumber;
    }

    public void setDecisionNumber(String decisionNumber) {
        this.decisionNumber = decisionNumber;
    }

    public ReturnValue withDecisionNumber(String decisionNumber) {
        this.decisionNumber = decisionNumber;
        return this;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public ReturnValue withRequestDate(String requestDate) {
        this.requestDate = requestDate;
        return this;
    }

    public int getLeaveRequestRuleID() {
        return leaveRequestRuleID;
    }

    public void setLeaveRequestRuleID(int leaveRequestRuleID) {
        this.leaveRequestRuleID = leaveRequestRuleID;
    }

    public ReturnValue withLeaveRequestRuleID(int leaveRequestRuleID) {
        this.leaveRequestRuleID = leaveRequestRuleID;
        return this;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public ReturnValue withRequestType(String requestType) {
        this.requestType = requestType;
        return this;
    }

    public Object getBalanceDate() {
        return balanceDate;
    }

    public void setBalanceDate(Object balanceDate) {
        this.balanceDate = balanceDate;
    }

    public ReturnValue withBalanceDate(Object balanceDate) {
        this.balanceDate = balanceDate;
        return this;
    }

    public String getTotalUnit() {
        return totalUnit;
    }

    public void setTotalUnit(String totalUnit) {
        this.totalUnit = totalUnit;
    }

    public ReturnValue withTotalUnit(String totalUnit) {
        this.totalUnit = totalUnit;
        return this;
    }

    public String getStartLeave() {
        return startLeave;
    }

    public void setStartLeave(String startLeave) {
        this.startLeave = startLeave;
    }

    public ReturnValue withStartLeave(String startLeave) {
        this.startLeave = startLeave;
        return this;
    }

    public String getEndLeave() {
        return endLeave;
    }

    public void setEndLeave(String endLeave) {
        this.endLeave = endLeave;
    }

    public ReturnValue withEndLeave(String endLeave) {
        this.endLeave = endLeave;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public ReturnValue withNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ReturnValue withStatus(String status) {
        this.status = status;
        return this;
    }

    public Object getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Object approveDate) {
        this.approveDate = approveDate;
    }

    public ReturnValue withApproveDate(Object approveDate) {
        this.approveDate = approveDate;
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
        dest.writeValue(decisionNumber);
        dest.writeValue(requestDate);
        dest.writeValue(leaveRequestRuleID);
        dest.writeValue(requestType);
        dest.writeValue(balanceDate);
        dest.writeValue(totalUnit);
        dest.writeValue(startLeave);
        dest.writeValue(endLeave);
        dest.writeValue(notes);
        dest.writeValue(status);
        dest.writeValue(approveDate);
        dest.writeValue(fullAccess);
        dest.writeList(exclusionFields);
        dest.writeValue(accessibilityAttribute);
    }

    public int describeContents() {
        return  0;
    }

    @Override
    public String toString() {
        return decisionNumber;
    }

}
