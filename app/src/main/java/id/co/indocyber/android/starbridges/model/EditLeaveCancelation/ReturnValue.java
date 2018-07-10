
package id.co.indocyber.android.starbridges.model.EditLeaveCancelation;

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
    @SerializedName("EmployeeID")
    @Expose
    private String employeeID;
    @SerializedName("RequestorID")
    @Expose
    private int requestorID;
    @SerializedName("LeaveRequestDecisionNumber")
    @Expose
    private Object leaveRequestDecisionNumber;
    @SerializedName("LeaveRequestTransactionID")
    @Expose
    private String leaveRequestTransactionID;
    @SerializedName("LeaveRequestType")
    @Expose
    private Object leaveRequestType;
    @SerializedName("LeaveRequestRuleID")
    @Expose
    private int leaveRequestRuleID;
    @SerializedName("RequestDate")
    @Expose
    private String requestDate;
    @SerializedName("LeaveFrom")
    @Expose
    private String leaveFrom;
    @SerializedName("LeaveTo")
    @Expose
    private String leaveTo;
    @SerializedName("CancelFrom")
    @Expose
    private String cancelFrom;
    @SerializedName("CancelTo")
    @Expose
    private String cancelTo;
    @SerializedName("Notes")
    @Expose
    private String notes;
    @SerializedName("AttachmentFile")
    @Expose
    private String attachmentFile;
    @SerializedName("AttachmentID")
    @Expose
    private int attachmentID;
    @SerializedName("AdditionalBalance")
    @Expose
    private int additionalBalance;
    @SerializedName("TransactionStatusID")
    @Expose
    private int transactionStatusID;
    @SerializedName("TotalUnitReduce")
    @Expose
    private int totalUnitReduce;
    @SerializedName("TransactionStatusSaveOrSubmit")
    @Expose
    private Object transactionStatusSaveOrSubmit;
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
    private final static long serialVersionUID = 2556011504863695638L;

    protected ReturnValue(Parcel in) {
        this.iD = ((String) in.readValue((String.class.getClassLoader())));
        this.employeeID = ((String) in.readValue((String.class.getClassLoader())));
        this.requestorID = ((int) in.readValue((int.class.getClassLoader())));
        this.leaveRequestDecisionNumber = ((Object) in.readValue((Object.class.getClassLoader())));
        this.leaveRequestTransactionID = ((String) in.readValue((String.class.getClassLoader())));
        this.leaveRequestType = ((Object) in.readValue((Object.class.getClassLoader())));
        this.leaveRequestRuleID = ((int) in.readValue((int.class.getClassLoader())));
        this.requestDate = ((String) in.readValue((String.class.getClassLoader())));
        this.leaveFrom = ((String) in.readValue((String.class.getClassLoader())));
        this.leaveTo = ((String) in.readValue((String.class.getClassLoader())));
        this.cancelFrom = ((String) in.readValue((String.class.getClassLoader())));
        this.cancelTo = ((String) in.readValue((String.class.getClassLoader())));
        this.notes = ((String) in.readValue((String.class.getClassLoader())));
        this.attachmentFile = ((String) in.readValue((String.class.getClassLoader())));
        this.attachmentID = ((int) in.readValue((int.class.getClassLoader())));
        this.additionalBalance = ((int) in.readValue((int.class.getClassLoader())));
        this.transactionStatusID = ((int) in.readValue((int.class.getClassLoader())));
        this.totalUnitReduce = ((int) in.readValue((int.class.getClassLoader())));
        this.transactionStatusSaveOrSubmit = ((Object) in.readValue((Object.class.getClassLoader())));
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
     * @param leaveRequestDecisionNumber
     * @param cancelTo
     * @param accessibilityAttribute
     * @param employeeID
     * @param leaveRequestRuleID
     * @param fullAccess
     * @param leaveRequestType
     * @param attachmentFile
     * @param iD
     * @param exclusionFields
     * @param totalUnitReduce
     * @param leaveFrom
     * @param leaveRequestTransactionID
     * @param leaveTo
     * @param cancelFrom
     * @param transactionStatusSaveOrSubmit
     * @param attachmentID
     * @param requestDate
     * @param notes
     * @param additionalBalance
     * @param transactionStatusID
     * @param requestorID
     */
    public ReturnValue(String iD, String employeeID, int requestorID, Object leaveRequestDecisionNumber, String leaveRequestTransactionID, Object leaveRequestType, int leaveRequestRuleID, String requestDate, String leaveFrom, String leaveTo, String cancelFrom, String cancelTo, String notes, String attachmentFile, int attachmentID, int additionalBalance, int transactionStatusID, int totalUnitReduce, Object transactionStatusSaveOrSubmit, boolean fullAccess, List<Object> exclusionFields, String accessibilityAttribute) {
        super();
        this.iD = iD;
        this.employeeID = employeeID;
        this.requestorID = requestorID;
        this.leaveRequestDecisionNumber = leaveRequestDecisionNumber;
        this.leaveRequestTransactionID = leaveRequestTransactionID;
        this.leaveRequestType = leaveRequestType;
        this.leaveRequestRuleID = leaveRequestRuleID;
        this.requestDate = requestDate;
        this.leaveFrom = leaveFrom;
        this.leaveTo = leaveTo;
        this.cancelFrom = cancelFrom;
        this.cancelTo = cancelTo;
        this.notes = notes;
        this.attachmentFile = attachmentFile;
        this.attachmentID = attachmentID;
        this.additionalBalance = additionalBalance;
        this.transactionStatusID = transactionStatusID;
        this.totalUnitReduce = totalUnitReduce;
        this.transactionStatusSaveOrSubmit = transactionStatusSaveOrSubmit;
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

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public ReturnValue withEmployeeID(String employeeID) {
        this.employeeID = employeeID;
        return this;
    }

    public int getRequestorID() {
        return requestorID;
    }

    public void setRequestorID(int requestorID) {
        this.requestorID = requestorID;
    }

    public ReturnValue withRequestorID(int requestorID) {
        this.requestorID = requestorID;
        return this;
    }

    public Object getLeaveRequestDecisionNumber() {
        return leaveRequestDecisionNumber;
    }

    public void setLeaveRequestDecisionNumber(Object leaveRequestDecisionNumber) {
        this.leaveRequestDecisionNumber = leaveRequestDecisionNumber;
    }

    public ReturnValue withLeaveRequestDecisionNumber(Object leaveRequestDecisionNumber) {
        this.leaveRequestDecisionNumber = leaveRequestDecisionNumber;
        return this;
    }

    public String getLeaveRequestTransactionID() {
        return leaveRequestTransactionID;
    }

    public void setLeaveRequestTransactionID(String leaveRequestTransactionID) {
        this.leaveRequestTransactionID = leaveRequestTransactionID;
    }

    public ReturnValue withLeaveRequestTransactionID(String leaveRequestTransactionID) {
        this.leaveRequestTransactionID = leaveRequestTransactionID;
        return this;
    }

    public Object getLeaveRequestType() {
        return leaveRequestType;
    }

    public void setLeaveRequestType(Object leaveRequestType) {
        this.leaveRequestType = leaveRequestType;
    }

    public ReturnValue withLeaveRequestType(Object leaveRequestType) {
        this.leaveRequestType = leaveRequestType;
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

    public String getLeaveFrom() {
        return leaveFrom;
    }

    public void setLeaveFrom(String leaveFrom) {
        this.leaveFrom = leaveFrom;
    }

    public ReturnValue withLeaveFrom(String leaveFrom) {
        this.leaveFrom = leaveFrom;
        return this;
    }

    public String getLeaveTo() {
        return leaveTo;
    }

    public void setLeaveTo(String leaveTo) {
        this.leaveTo = leaveTo;
    }

    public ReturnValue withLeaveTo(String leaveTo) {
        this.leaveTo = leaveTo;
        return this;
    }

    public String getCancelFrom() {
        return cancelFrom;
    }

    public void setCancelFrom(String cancelFrom) {
        this.cancelFrom = cancelFrom;
    }

    public ReturnValue withCancelFrom(String cancelFrom) {
        this.cancelFrom = cancelFrom;
        return this;
    }

    public String getCancelTo() {
        return cancelTo;
    }

    public void setCancelTo(String cancelTo) {
        this.cancelTo = cancelTo;
    }

    public ReturnValue withCancelTo(String cancelTo) {
        this.cancelTo = cancelTo;
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

    public String getAttachmentFile() {
        return attachmentFile;
    }

    public void setAttachmentFile(String attachmentFile) {
        this.attachmentFile = attachmentFile;
    }

    public ReturnValue withAttachmentFile(String attachmentFile) {
        this.attachmentFile = attachmentFile;
        return this;
    }

    public int getAttachmentID() {
        return attachmentID;
    }

    public void setAttachmentID(int attachmentID) {
        this.attachmentID = attachmentID;
    }

    public ReturnValue withAttachmentID(int attachmentID) {
        this.attachmentID = attachmentID;
        return this;
    }

    public int getAdditionalBalance() {
        return additionalBalance;
    }

    public void setAdditionalBalance(int additionalBalance) {
        this.additionalBalance = additionalBalance;
    }

    public ReturnValue withAdditionalBalance(int additionalBalance) {
        this.additionalBalance = additionalBalance;
        return this;
    }

    public int getTransactionStatusID() {
        return transactionStatusID;
    }

    public void setTransactionStatusID(int transactionStatusID) {
        this.transactionStatusID = transactionStatusID;
    }

    public ReturnValue withTransactionStatusID(int transactionStatusID) {
        this.transactionStatusID = transactionStatusID;
        return this;
    }

    public int getTotalUnitReduce() {
        return totalUnitReduce;
    }

    public void setTotalUnitReduce(int totalUnitReduce) {
        this.totalUnitReduce = totalUnitReduce;
    }

    public ReturnValue withTotalUnitReduce(int totalUnitReduce) {
        this.totalUnitReduce = totalUnitReduce;
        return this;
    }

    public Object getTransactionStatusSaveOrSubmit() {
        return transactionStatusSaveOrSubmit;
    }

    public void setTransactionStatusSaveOrSubmit(Object transactionStatusSaveOrSubmit) {
        this.transactionStatusSaveOrSubmit = transactionStatusSaveOrSubmit;
    }

    public ReturnValue withTransactionStatusSaveOrSubmit(Object transactionStatusSaveOrSubmit) {
        this.transactionStatusSaveOrSubmit = transactionStatusSaveOrSubmit;
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
        dest.writeValue(employeeID);
        dest.writeValue(requestorID);
        dest.writeValue(leaveRequestDecisionNumber);
        dest.writeValue(leaveRequestTransactionID);
        dest.writeValue(leaveRequestType);
        dest.writeValue(leaveRequestRuleID);
        dest.writeValue(requestDate);
        dest.writeValue(leaveFrom);
        dest.writeValue(leaveTo);
        dest.writeValue(cancelFrom);
        dest.writeValue(cancelTo);
        dest.writeValue(notes);
        dest.writeValue(attachmentFile);
        dest.writeValue(attachmentID);
        dest.writeValue(additionalBalance);
        dest.writeValue(transactionStatusID);
        dest.writeValue(totalUnitReduce);
        dest.writeValue(transactionStatusSaveOrSubmit);
        dest.writeValue(fullAccess);
        dest.writeList(exclusionFields);
        dest.writeValue(accessibilityAttribute);
    }

    public int describeContents() {
        return  0;
    }

}
