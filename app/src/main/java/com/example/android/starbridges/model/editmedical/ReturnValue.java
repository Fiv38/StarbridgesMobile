
package com.example.android.starbridges.model.editmedical;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReturnValue implements Serializable, Parcelable
{

    @SerializedName("EmployeeID")
    @Expose
    private String employeeID;
    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("MedicalSupportID")
    @Expose
    private Integer medicalSupportID;
    @SerializedName("MedicalSupportName")
    @Expose
    private String medicalSupportName;
    @SerializedName("MedicalPolicyID")
    @Expose
    private Integer medicalPolicyID;
    @SerializedName("PolicyTypeID")
    @Expose
    private String policyTypeID;
    @SerializedName("MedicalPolicyName")
    @Expose
    private String medicalPolicyName;
    @SerializedName("RemainingBalance")
    @Expose
    private Integer remainingBalance;
    @SerializedName("EmployeeFamilyID")
    @Expose
    private String employeeFamilyID;
    @SerializedName("EmployeeFamilyName")
    @Expose
    private String employeeFamilyName;
    @SerializedName("MedicalClaimPolicyID")
    @Expose
    private Integer medicalClaimPolicyID;
    @SerializedName("TotalClaim")
    @Expose
    private Integer totalClaim;
    @SerializedName("TotalReimbursement")
    @Expose
    private Integer totalReimbursement;
    @SerializedName("AttachmentFile")
    @Expose
    private String attachmentFile;
    @SerializedName("AttachmentID")
    @Expose
    private String attachmentID;
    @SerializedName("ReceiptDate")
    @Expose
    private String receiptDate;
    @SerializedName("DecisionNumber")
    @Expose
    private String decisionNumber;
    @SerializedName("TransactionStatusID")
    @Expose
    private Integer transactionStatusID;
    @SerializedName("ApprovedDate")
    @Expose
    private String approvedDate;
    @SerializedName("Claim")
    @Expose
    private Integer claim;
    @SerializedName("TransactionStatusSaveOrSubmit")
    @Expose
    private String transactionStatusSaveOrSubmit;
    @SerializedName("FullAccess")
    @Expose
    private Boolean fullAccess;
    @SerializedName("ExclusionFields")
    @Expose
    private List<String> exclusionFields = null;
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
    private final static long serialVersionUID = 7835033572298910236L;

    protected ReturnValue(Parcel in) {
        this.employeeID = ((String) in.readValue((String.class.getClassLoader())));
        this.iD = ((String) in.readValue((String.class.getClassLoader())));
        this.medicalSupportID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.medicalSupportName = ((String) in.readValue((String.class.getClassLoader())));
        this.medicalPolicyID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.policyTypeID = ((String) in.readValue((String.class.getClassLoader())));
        this.medicalPolicyName = ((String) in.readValue((String.class.getClassLoader())));
        this.remainingBalance = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.employeeFamilyID = ((String) in.readValue((String.class.getClassLoader())));
        this.employeeFamilyName = ((String) in.readValue((String.class.getClassLoader())));
        this.medicalClaimPolicyID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalClaim = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalReimbursement = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.attachmentFile = ((String) in.readValue((String.class.getClassLoader())));
        this.attachmentID = ((String) in.readValue((String.class.getClassLoader())));
        this.receiptDate = ((String) in.readValue((String.class.getClassLoader())));
        this.decisionNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.transactionStatusID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.approvedDate = ((String) in.readValue((String.class.getClassLoader())));
        this.claim = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.transactionStatusSaveOrSubmit = ((String) in.readValue((String.class.getClassLoader())));
        this.fullAccess = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.exclusionFields, (String.class.getClassLoader()));
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
     * @param medicalSupportID
     * @param employeeFamilyName
     * @param accessibilityAttribute
     * @param employeeID
     * @param totalClaim
     * @param fullAccess
     * @param remainingBalance
     * @param attachmentFile
     * @param approvedDate
     * @param medicalSupportName
     * @param iD
     * @param medicalPolicyID
     * @param exclusionFields
     * @param decisionNumber
     * @param receiptDate
     * @param policyTypeID
     * @param medicalClaimPolicyID
     * @param transactionStatusSaveOrSubmit
     * @param medicalPolicyName
     * @param claim
     * @param attachmentID
     * @param totalReimbursement
     * @param transactionStatusID
     * @param employeeFamilyID
     */
    public ReturnValue(String employeeID, String iD, Integer medicalSupportID, String medicalSupportName, Integer medicalPolicyID, String policyTypeID, String medicalPolicyName, Integer remainingBalance, String employeeFamilyID, String employeeFamilyName, Integer medicalClaimPolicyID, Integer totalClaim, Integer totalReimbursement, String attachmentFile, String attachmentID, String receiptDate, String decisionNumber, Integer transactionStatusID, String approvedDate, Integer claim, String transactionStatusSaveOrSubmit, Boolean fullAccess, List<String> exclusionFields, String accessibilityAttribute) {
        super();
        this.employeeID = employeeID;
        this.iD = iD;
        this.medicalSupportID = medicalSupportID;
        this.medicalSupportName = medicalSupportName;
        this.medicalPolicyID = medicalPolicyID;
        this.policyTypeID = policyTypeID;
        this.medicalPolicyName = medicalPolicyName;
        this.remainingBalance = remainingBalance;
        this.employeeFamilyID = employeeFamilyID;
        this.employeeFamilyName = employeeFamilyName;
        this.medicalClaimPolicyID = medicalClaimPolicyID;
        this.totalClaim = totalClaim;
        this.totalReimbursement = totalReimbursement;
        this.attachmentFile = attachmentFile;
        this.attachmentID = attachmentID;
        this.receiptDate = receiptDate;
        this.decisionNumber = decisionNumber;
        this.transactionStatusID = transactionStatusID;
        this.approvedDate = approvedDate;
        this.claim = claim;
        this.transactionStatusSaveOrSubmit = transactionStatusSaveOrSubmit;
        this.fullAccess = fullAccess;
        this.exclusionFields = exclusionFields;
        this.accessibilityAttribute = accessibilityAttribute;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public Integer getMedicalSupportID() {
        return medicalSupportID;
    }

    public void setMedicalSupportID(Integer medicalSupportID) {
        this.medicalSupportID = medicalSupportID;
    }

    public String getMedicalSupportName() {
        return medicalSupportName;
    }

    public void setMedicalSupportName(String medicalSupportName) {
        this.medicalSupportName = medicalSupportName;
    }

    public Integer getMedicalPolicyID() {
        return medicalPolicyID;
    }

    public void setMedicalPolicyID(Integer medicalPolicyID) {
        this.medicalPolicyID = medicalPolicyID;
    }

    public String getPolicyTypeID() {
        return policyTypeID;
    }

    public void setPolicyTypeID(String policyTypeID) {
        this.policyTypeID = policyTypeID;
    }

    public String getMedicalPolicyName() {
        return medicalPolicyName;
    }

    public void setMedicalPolicyName(String medicalPolicyName) {
        this.medicalPolicyName = medicalPolicyName;
    }

    public Integer getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(Integer remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

    public String getEmployeeFamilyID() {
        return employeeFamilyID;
    }

    public void setEmployeeFamilyID(String employeeFamilyID) {
        this.employeeFamilyID = employeeFamilyID;
    }

    public String getEmployeeFamilyName() {
        return employeeFamilyName;
    }

    public void setEmployeeFamilyName(String employeeFamilyName) {
        this.employeeFamilyName = employeeFamilyName;
    }

    public Integer getMedicalClaimPolicyID() {
        return medicalClaimPolicyID;
    }

    public void setMedicalClaimPolicyID(Integer medicalClaimPolicyID) {
        this.medicalClaimPolicyID = medicalClaimPolicyID;
    }

    public Integer getTotalClaim() {
        return totalClaim;
    }

    public void setTotalClaim(Integer totalClaim) {
        this.totalClaim = totalClaim;
    }

    public Integer getTotalReimbursement() {
        return totalReimbursement;
    }

    public void setTotalReimbursement(Integer totalReimbursement) {
        this.totalReimbursement = totalReimbursement;
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

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
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

    public Integer getClaim() {
        return claim;
    }

    public void setClaim(Integer claim) {
        this.claim = claim;
    }

    public String getTransactionStatusSaveOrSubmit() {
        return transactionStatusSaveOrSubmit;
    }

    public void setTransactionStatusSaveOrSubmit(String transactionStatusSaveOrSubmit) {
        this.transactionStatusSaveOrSubmit = transactionStatusSaveOrSubmit;
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(employeeID);
        dest.writeValue(iD);
        dest.writeValue(medicalSupportID);
        dest.writeValue(medicalSupportName);
        dest.writeValue(medicalPolicyID);
        dest.writeValue(policyTypeID);
        dest.writeValue(medicalPolicyName);
        dest.writeValue(remainingBalance);
        dest.writeValue(employeeFamilyID);
        dest.writeValue(employeeFamilyName);
        dest.writeValue(medicalClaimPolicyID);
        dest.writeValue(totalClaim);
        dest.writeValue(totalReimbursement);
        dest.writeValue(attachmentFile);
        dest.writeValue(attachmentID);
        dest.writeValue(receiptDate);
        dest.writeValue(decisionNumber);
        dest.writeValue(transactionStatusID);
        dest.writeValue(approvedDate);
        dest.writeValue(claim);
        dest.writeValue(transactionStatusSaveOrSubmit);
        dest.writeValue(fullAccess);
        dest.writeList(exclusionFields);
        dest.writeValue(accessibilityAttribute);
    }

    public int describeContents() {
        return  0;
    }

}
