
package id.co.indocyber.android.starbridges.model.leaverequest;

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
    @SerializedName("DecisionNumber")
    @Expose
    private String decisionNumber;
    @SerializedName("RequestDate")
    @Expose
    private String requestDate;
    @SerializedName("LeaveRequestRuleID")
    @Expose
    private Integer leaveRequestRuleID;
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
    private String approveDate;
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
    private final static long serialVersionUID = -1771277015796732493L;

    protected ReturnValue(Parcel in) {
        this.iD = ((String) in.readValue((String.class.getClassLoader())));
        this.decisionNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.requestDate = ((String) in.readValue((String.class.getClassLoader())));
        this.leaveRequestRuleID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.requestType = ((String) in.readValue((String.class.getClassLoader())));
        this.balanceDate = ((Object) in.readValue((Object.class.getClassLoader())));
        this.totalUnit = ((String) in.readValue((String.class.getClassLoader())));
        this.startLeave = ((String) in.readValue((String.class.getClassLoader())));
        this.endLeave = ((String) in.readValue((String.class.getClassLoader())));
        this.notes = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.approveDate = ((String) in.readValue((String.class.getClassLoader())));
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
    public ReturnValue(String iD, String decisionNumber, String requestDate, Integer leaveRequestRuleID, String requestType, Object balanceDate, String totalUnit, String startLeave, String endLeave, String notes, String status, String approveDate, Boolean fullAccess, List<Object> exclusionFields, String accessibilityAttribute) {
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

    public String getDecisionNumber() {
        return decisionNumber;
    }

    public void setDecisionNumber(String decisionNumber) {
        this.decisionNumber = decisionNumber;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public Integer getLeaveRequestRuleID() {
        return leaveRequestRuleID;
    }

    public void setLeaveRequestRuleID(Integer leaveRequestRuleID) {
        this.leaveRequestRuleID = leaveRequestRuleID;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Object getBalanceDate() {
        return balanceDate;
    }

    public void setBalanceDate(Object balanceDate) {
        this.balanceDate = balanceDate;
    }

    public String getTotalUnit() {
        return totalUnit;
    }

    public void setTotalUnit(String totalUnit) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate;
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

}
