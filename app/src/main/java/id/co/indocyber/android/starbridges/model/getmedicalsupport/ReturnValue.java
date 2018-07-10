
package id.co.indocyber.android.starbridges.model.getmedicalsupport;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ReturnValue implements Serializable, Parcelable
{

    @SerializedName("EmployeeID")
    @Expose
    private String employeeID;
    @SerializedName("EmployeeName")
    @Expose
    private String employeeName;
    @SerializedName("EmployeeNIK")
    @Expose
    private String employeeNIK;
    @SerializedName("OrganizationUnit")
    @Expose
    private String organizationUnit;
    @SerializedName("Position")
    @Expose
    private String position;
    @SerializedName("Level")
    @Expose
    private String level;
    @SerializedName("Grade")
    @Expose
    private String grade;
    @SerializedName("OperationLocation")
    @Expose
    private String operationLocation;
    @SerializedName("EmployementStatus")
    @Expose
    private String employementStatus;
    @SerializedName("ID")
    @Expose
    private Object iD;
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
    private Object policyTypeID;
    @SerializedName("MedicalPolicyName")
    @Expose
    private Object medicalPolicyName;
    @SerializedName("RemainingBalance")
    @Expose
    private Integer remainingBalance;
    @SerializedName("EmployeeFamilyID")
    @Expose
    private Object employeeFamilyID;
    @SerializedName("EmployeeFamilyName")
    @Expose
    private Object employeeFamilyName;
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
    private Object attachmentFile;
    @SerializedName("ReceiptDate")
    @Expose
    private String receiptDate;
    @SerializedName("DecisionNumber")
    @Expose
    private Object decisionNumber;
    @SerializedName("TransactionStatusID")
    @Expose
    private Integer transactionStatusID;
    @SerializedName("SubmitType")
    @Expose
    private Object submitType;
    @SerializedName("ApprovedDate")
    @Expose
    private Object approvedDate;
    @SerializedName("Message")
    @Expose
    private Object message;
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
    private final static long serialVersionUID = 4128099190044749477L;

    protected ReturnValue(Parcel in) {
        this.employeeID = ((String) in.readValue((String.class.getClassLoader())));
        this.employeeName = ((String) in.readValue((String.class.getClassLoader())));
        this.employeeNIK = ((String) in.readValue((String.class.getClassLoader())));
        this.organizationUnit = ((String) in.readValue((String.class.getClassLoader())));
        this.position = ((String) in.readValue((String.class.getClassLoader())));
        this.level = ((String) in.readValue((String.class.getClassLoader())));
        this.grade = ((String) in.readValue((String.class.getClassLoader())));
        this.operationLocation = ((String) in.readValue((String.class.getClassLoader())));
        this.employementStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.iD = ((Object) in.readValue((Object.class.getClassLoader())));
        this.medicalSupportID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.medicalSupportName = ((String) in.readValue((String.class.getClassLoader())));
        this.medicalPolicyID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.policyTypeID = ((Object) in.readValue((Object.class.getClassLoader())));
        this.medicalPolicyName = ((Object) in.readValue((Object.class.getClassLoader())));
        this.remainingBalance = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.employeeFamilyID = ((Object) in.readValue((Object.class.getClassLoader())));
        this.employeeFamilyName = ((Object) in.readValue((Object.class.getClassLoader())));
        this.medicalClaimPolicyID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalClaim = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalReimbursement = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.attachmentFile = ((Object) in.readValue((Object.class.getClassLoader())));
        this.receiptDate = ((String) in.readValue((String.class.getClassLoader())));
        this.decisionNumber = ((Object) in.readValue((Object.class.getClassLoader())));
        this.transactionStatusID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.submitType = ((Object) in.readValue((Object.class.getClassLoader())));
        this.approvedDate = ((Object) in.readValue((Object.class.getClassLoader())));
        this.message = ((Object) in.readValue((Object.class.getClassLoader())));
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
     * @param medicalSupportID
     * @param position
     * @param employeeFamilyName
     * @param accessibilityAttribute
     * @param remainingBalance
     * @param medicalSupportName
     * @param organizationUnit
     * @param employeeName
     * @param employeeNIK
     * @param medicalClaimPolicyID
     * @param receiptDate
     * @param decisionNumber
     * @param level
     * @param grade
     * @param employementStatus
     * @param totalReimbursement
     * @param transactionStatusID
     * @param employeeFamilyID
     * @param employeeID
     * @param totalClaim
     * @param fullAccess
     * @param attachmentFile
     * @param approvedDate
     * @param submitType
     * @param iD
     * @param medicalPolicyID
     * @param exclusionFields
     * @param message
     * @param policyTypeID
     * @param medicalPolicyName
     * @param operationLocation
     */
    public ReturnValue(String employeeID, String employeeName, String employeeNIK, String organizationUnit, String position, String level, String grade, String operationLocation, String employementStatus, Object iD, Integer medicalSupportID, String medicalSupportName, Integer medicalPolicyID, Object policyTypeID, Object medicalPolicyName, Integer remainingBalance, Object employeeFamilyID, Object employeeFamilyName, Integer medicalClaimPolicyID, Integer totalClaim, Integer totalReimbursement, Object attachmentFile, String receiptDate, Object decisionNumber, Integer transactionStatusID, Object submitType, Object approvedDate, Object message, Boolean fullAccess, List<Object> exclusionFields, String accessibilityAttribute) {
        super();
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.employeeNIK = employeeNIK;
        this.organizationUnit = organizationUnit;
        this.position = position;
        this.level = level;
        this.grade = grade;
        this.operationLocation = operationLocation;
        this.employementStatus = employementStatus;
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
        this.receiptDate = receiptDate;
        this.decisionNumber = decisionNumber;
        this.transactionStatusID = transactionStatusID;
        this.submitType = submitType;
        this.approvedDate = approvedDate;
        this.message = message;
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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeNIK() {
        return employeeNIK;
    }

    public void setEmployeeNIK(String employeeNIK) {
        this.employeeNIK = employeeNIK;
    }

    public String getOrganizationUnit() {
        return organizationUnit;
    }

    public void setOrganizationUnit(String organizationUnit) {
        this.organizationUnit = organizationUnit;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getOperationLocation() {
        return operationLocation;
    }

    public void setOperationLocation(String operationLocation) {
        this.operationLocation = operationLocation;
    }

    public String getEmployementStatus() {
        return employementStatus;
    }

    public void setEmployementStatus(String employementStatus) {
        this.employementStatus = employementStatus;
    }

    public Object getID() {
        return iD;
    }

    public void setID(Object iD) {
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

    public Object getPolicyTypeID() {
        return policyTypeID;
    }

    public void setPolicyTypeID(Object policyTypeID) {
        this.policyTypeID = policyTypeID;
    }

    public Object getMedicalPolicyName() {
        return medicalPolicyName;
    }

    public void setMedicalPolicyName(Object medicalPolicyName) {
        this.medicalPolicyName = medicalPolicyName;
    }

    public Integer getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(Integer remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

    public Object getEmployeeFamilyID() {
        return employeeFamilyID;
    }

    public void setEmployeeFamilyID(Object employeeFamilyID) {
        this.employeeFamilyID = employeeFamilyID;
    }

    public Object getEmployeeFamilyName() {
        return employeeFamilyName;
    }

    public void setEmployeeFamilyName(Object employeeFamilyName) {
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

    public Object getAttachmentFile() {
        return attachmentFile;
    }

    public void setAttachmentFile(Object attachmentFile) {
        this.attachmentFile = attachmentFile;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Object getDecisionNumber() {
        return decisionNumber;
    }

    public void setDecisionNumber(Object decisionNumber) {
        this.decisionNumber = decisionNumber;
    }

    public Integer getTransactionStatusID() {
        return transactionStatusID;
    }

    public void setTransactionStatusID(Integer transactionStatusID) {
        this.transactionStatusID = transactionStatusID;
    }

    public Object getSubmitType() {
        return submitType;
    }

    public void setSubmitType(Object submitType) {
        this.submitType = submitType;
    }

    public Object getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Object approvedDate) {
        this.approvedDate = approvedDate;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
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
        dest.writeValue(employeeID);
        dest.writeValue(employeeName);
        dest.writeValue(employeeNIK);
        dest.writeValue(organizationUnit);
        dest.writeValue(position);
        dest.writeValue(level);
        dest.writeValue(grade);
        dest.writeValue(operationLocation);
        dest.writeValue(employementStatus);
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
        dest.writeValue(receiptDate);
        dest.writeValue(decisionNumber);
        dest.writeValue(transactionStatusID);
        dest.writeValue(submitType);
        dest.writeValue(approvedDate);
        dest.writeValue(message);
        dest.writeValue(fullAccess);
        dest.writeList(exclusionFields);
        dest.writeValue(accessibilityAttribute);
    }

    public int describeContents() {
        return  0;
    }

}
