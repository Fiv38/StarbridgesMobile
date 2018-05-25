
package com.example.android.starbridges.model.requestconfirmation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReturnValue {

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("EmployeeID")
    @Expose
    private String employeeID;
    @SerializedName("Roster")
    @Expose
    private String roster;
    @SerializedName("RequestDate")
    @Expose
    private String requestDate;
    @SerializedName("EmployeeNIK")
    @Expose
    private String employeeNIK;
    @SerializedName("EmployeeName")
    @Expose
    private String employeeName;
    @SerializedName("LeaveRequestRuleID")
    @Expose
    private Integer leaveRequestRuleID;
    @SerializedName("LeaveRequestType")
    @Expose
    private String leaveRequestType;
    @SerializedName("EmployeeLeaveBalanceUID")
    @Expose
    private String employeeLeaveBalanceUID;
    @SerializedName("CurrentBalance")
    @Expose
    private Integer currentBalance;
    @SerializedName("BalanceExpireDate")
    @Expose
    private String balanceExpireDate;
    @SerializedName("TotalUnit")
    @Expose
    private Integer totalUnit;
    @SerializedName("TotalUnitReduce")
    @Expose
    private Integer totalUnitReduce;
    @SerializedName("StartLeave")
    @Expose
    private String startLeave;
    @SerializedName("EndLeave")
    @Expose
    private String endLeave;
    @SerializedName("LeaveAt")
    @Expose
    private String leaveAt;
    @SerializedName("ReturnAt")
    @Expose
    private String returnAt;
    @SerializedName("MinIntervalViolation")
    @Expose
    private Boolean minIntervalViolation;
    @SerializedName("UnitLimitViolation")
    @Expose
    private Boolean unitLimitViolation;
    @SerializedName("OccurenceViolation")
    @Expose
    private Boolean occurenceViolation;
    @SerializedName("Notes")
    @Expose
    private String notes;
    @SerializedName("AttachmentFile")
    @Expose
    private String attachmentFile;
    @SerializedName("AttachmentID")
    @Expose
    private String attachmentID;
    @SerializedName("DecisionNumber")
    @Expose
    private String decisionNumber;
    @SerializedName("TransactionStatusID")
    @Expose
    private Integer transactionStatusID;
    @SerializedName("ApprovedDate")
    @Expose
    private String approvedDate;
    @SerializedName("IsHalfDay")
    @Expose
    private Boolean isHalfDay;
    @SerializedName("SubmitType")
    @Expose
    private String submitType;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("TransactionStatusSaveOrSubmit")
    @Expose
    private String transactionStatusSaveOrSubmit;
    @SerializedName("Photo")
    @Expose
    private String photo;
    @SerializedName("FullAccess")
    @Expose
    private Boolean fullAccess;
    @SerializedName("ExclusionFields")
    @Expose
    private List<String> exclusionFields = null;
    @SerializedName("AccessibilityAttribute")
    @Expose
    private String accessibilityAttribute;

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getRoster() {
        return roster;
    }

    public void setRoster(String roster) {
        this.roster = roster;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getEmployeeNIK() {
        return employeeNIK;
    }

    public void setEmployeeNIK(String employeeNIK) {
        this.employeeNIK = employeeNIK;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getLeaveRequestRuleID() {
        return leaveRequestRuleID;
    }

    public void setLeaveRequestRuleID(Integer leaveRequestRuleID) {
        this.leaveRequestRuleID = leaveRequestRuleID;
    }

    public String getLeaveRequestType() {
        return leaveRequestType;
    }

    public void setLeaveRequestType(String leaveRequestType) {
        this.leaveRequestType = leaveRequestType;
    }

    public String getEmployeeLeaveBalanceUID() {
        return employeeLeaveBalanceUID;
    }

    public void setEmployeeLeaveBalanceUID(String employeeLeaveBalanceUID) {
        this.employeeLeaveBalanceUID = employeeLeaveBalanceUID;
    }

    public Integer getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Integer currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getBalanceExpireDate() {
        return balanceExpireDate;
    }

    public void setBalanceExpireDate(String balanceExpireDate) {
        this.balanceExpireDate = balanceExpireDate;
    }

    public Integer getTotalUnit() {
        return totalUnit;
    }

    public void setTotalUnit(Integer totalUnit) {
        this.totalUnit = totalUnit;
    }

    public Integer getTotalUnitReduce() {
        return totalUnitReduce;
    }

    public void setTotalUnitReduce(Integer totalUnitReduce) {
        this.totalUnitReduce = totalUnitReduce;
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

    public String getLeaveAt() {
        return leaveAt;
    }

    public void setLeaveAt(String leaveAt) {
        this.leaveAt = leaveAt;
    }

    public String getReturnAt() {
        return returnAt;
    }

    public void setReturnAt(String returnAt) {
        this.returnAt = returnAt;
    }

    public Boolean getMinIntervalViolation() {
        return minIntervalViolation;
    }

    public void setMinIntervalViolation(Boolean minIntervalViolation) {
        this.minIntervalViolation = minIntervalViolation;
    }

    public Boolean getUnitLimitViolation() {
        return unitLimitViolation;
    }

    public void setUnitLimitViolation(Boolean unitLimitViolation) {
        this.unitLimitViolation = unitLimitViolation;
    }

    public Boolean getOccurenceViolation() {
        return occurenceViolation;
    }

    public void setOccurenceViolation(Boolean occurenceViolation) {
        this.occurenceViolation = occurenceViolation;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAttachmentFile() {
        return attachmentFile;
    }

    public void setAttachmentFile(String attachmentFile) {
        this.attachmentFile = attachmentFile;
    }

    public String getAttachmentID() {
        return attachmentID;
    }

    public void setAttachmentID(String attachmentID) {
        this.attachmentID = attachmentID;
    }

    public String getDecisionNumber() {
        return decisionNumber;
    }

    public void setDecisionNumber(String decisionNumber) {
        this.decisionNumber = decisionNumber;
    }

    public Integer getTransactionStatusID() {
        return transactionStatusID;
    }

    public void setTransactionStatusID(Integer transactionStatusID) {
        this.transactionStatusID = transactionStatusID;
    }

    public String getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(String approvedDate) {
        this.approvedDate = approvedDate;
    }

    public Boolean getIsHalfDay() {
        return isHalfDay;
    }

    public void setIsHalfDay(Boolean isHalfDay) {
        this.isHalfDay = isHalfDay;
    }

    public String getSubmitType() {
        return submitType;
    }

    public void setSubmitType(String submitType) {
        this.submitType = submitType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTransactionStatusSaveOrSubmit() {
        return transactionStatusSaveOrSubmit;
    }

    public void setTransactionStatusSaveOrSubmit(String transactionStatusSaveOrSubmit) {
        this.transactionStatusSaveOrSubmit = transactionStatusSaveOrSubmit;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Boolean getFullAccess() {
        return fullAccess;
    }

    public void setFullAccess(Boolean fullAccess) {
        this.fullAccess = fullAccess;
    }

    public List<String> getExclusionFields() {
        return exclusionFields;
    }

    public void setExclusionFields(List<String> exclusionFields) {
        this.exclusionFields = exclusionFields;
    }

    public String getAccessibilityAttribute() {
        return accessibilityAttribute;
    }

    public void setAccessibilityAttribute(String accessibilityAttribute) {
        this.accessibilityAttribute = accessibilityAttribute;
    }

}
