
package id.co.indocyber.android.starbridges.model.medicalrequestconfirmation;

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
    private int medicalSupportID;
    @SerializedName("MedicalSupportName")
    @Expose
    private String medicalSupportName;
    @SerializedName("MedicalPolicyID")
    @Expose
    private int medicalPolicyID;
    @SerializedName("PolicyTypeID")
    @Expose
    private String policyTypeID;
    @SerializedName("MedicalPolicyName")
    @Expose
    private String medicalPolicyName;
    @SerializedName("RemainingBalance")
    @Expose
    private int remainingBalance;
    @SerializedName("EmployeeFamilyID")
    @Expose
    private Object employeeFamilyID;
    @SerializedName("EmployeeFamilyName")
    @Expose
    private String employeeFamilyName;
    @SerializedName("MedicalClaimPolicyID")
    @Expose
    private int medicalClaimPolicyID;
    @SerializedName("TotalClaim")
    @Expose
    private int totalClaim;
    @SerializedName("TotalReimbursement")
    @Expose
    private float totalReimbursement;
    @SerializedName("AttachmentFile")
    @Expose
    private Object attachmentFile;
    @SerializedName("AttachmentID")
    @Expose
    private Object attachmentID;
    @SerializedName("ReceiptDate")
    @Expose
    private String receiptDate;
    @SerializedName("DecisionNumber")
    @Expose
    private Object decisionNumber;
    @SerializedName("TransactionStatusID")
    @Expose
    private int transactionStatusID;
    @SerializedName("ApprovedDate")
    @Expose
    private Object approvedDate;
    @SerializedName("Claim")
    @Expose
    private int claim;
    @SerializedName("TransactionStatusSaveOrSubmit")
    @Expose
    private String transactionStatusSaveOrSubmit;
    @SerializedName("FullAccess")
    @Expose
    private boolean fullAccess;
    @SerializedName("ExclusionFields")
    @Expose
    private List<String> exclusionFields = null;
    @SerializedName("AccessibilityAttribute")
    @Expose
    private String accessibilityAttribute;
    public final static Parcelable.Creator<ReturnValue> CREATOR = new Creator<ReturnValue>() {


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
    private final static long serialVersionUID = 8630384195772350531L;

    protected ReturnValue(Parcel in) {
        this.employeeID = ((String) in.readValue((String.class.getClassLoader())));
        this.iD = ((String) in.readValue((String.class.getClassLoader())));
        this.medicalSupportID = ((int) in.readValue((int.class.getClassLoader())));
        this.medicalSupportName = ((String) in.readValue((String.class.getClassLoader())));
        this.medicalPolicyID = ((int) in.readValue((int.class.getClassLoader())));
        this.policyTypeID = ((String) in.readValue((String.class.getClassLoader())));
        this.medicalPolicyName = ((String) in.readValue((String.class.getClassLoader())));
        this.remainingBalance = ((int) in.readValue((int.class.getClassLoader())));
        this.employeeFamilyID = ((Object) in.readValue((Object.class.getClassLoader())));
        this.employeeFamilyName = ((String) in.readValue((String.class.getClassLoader())));
        this.medicalClaimPolicyID = ((int) in.readValue((int.class.getClassLoader())));
        this.totalClaim = ((int) in.readValue((int.class.getClassLoader())));
        this.totalReimbursement = ((float) in.readValue((float.class.getClassLoader())));
        this.attachmentFile = ((Object) in.readValue((Object.class.getClassLoader())));
        this.attachmentID = ((Object) in.readValue((Object.class.getClassLoader())));
        this.receiptDate = ((String) in.readValue((String.class.getClassLoader())));
        this.decisionNumber = ((Object) in.readValue((Object.class.getClassLoader())));
        this.transactionStatusID = ((int) in.readValue((int.class.getClassLoader())));
        this.approvedDate = ((Object) in.readValue((Object.class.getClassLoader())));
        this.claim = ((int) in.readValue((int.class.getClassLoader())));
        this.transactionStatusSaveOrSubmit = ((String) in.readValue((String.class.getClassLoader())));
        this.fullAccess = ((boolean) in.readValue((boolean.class.getClassLoader())));
        in.readList(this.exclusionFields, (java.lang.Object.class.getClassLoader()));
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
    public ReturnValue(String employeeID, String iD, int medicalSupportID, String medicalSupportName, int medicalPolicyID, String policyTypeID, String medicalPolicyName, int remainingBalance, Object employeeFamilyID, String employeeFamilyName, int medicalClaimPolicyID, int totalClaim, float totalReimbursement, Object attachmentFile, Object attachmentID, String receiptDate, Object decisionNumber, int transactionStatusID, Object approvedDate, int claim, String transactionStatusSaveOrSubmit, boolean fullAccess, List<String> exclusionFields, String accessibilityAttribute) {
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

    public ReturnValue withEmployeeID(String employeeID) {
        this.employeeID = employeeID;
        return this;
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

    public int getMedicalSupportID() {
        return medicalSupportID;
    }

    public void setMedicalSupportID(int medicalSupportID) {
        this.medicalSupportID = medicalSupportID;
    }

    public ReturnValue withMedicalSupportID(int medicalSupportID) {
        this.medicalSupportID = medicalSupportID;
        return this;
    }

    public String getMedicalSupportName() {
        return medicalSupportName;
    }

    public void setMedicalSupportName(String medicalSupportName) {
        this.medicalSupportName = medicalSupportName;
    }

    public ReturnValue withMedicalSupportName(String medicalSupportName) {
        this.medicalSupportName = medicalSupportName;
        return this;
    }

    public int getMedicalPolicyID() {
        return medicalPolicyID;
    }

    public void setMedicalPolicyID(int medicalPolicyID) {
        this.medicalPolicyID = medicalPolicyID;
    }

    public ReturnValue withMedicalPolicyID(int medicalPolicyID) {
        this.medicalPolicyID = medicalPolicyID;
        return this;
    }

    public String getPolicyTypeID() {
        return policyTypeID;
    }

    public void setPolicyTypeID(String policyTypeID) {
        this.policyTypeID = policyTypeID;
    }

    public ReturnValue withPolicyTypeID(String policyTypeID) {
        this.policyTypeID = policyTypeID;
        return this;
    }

    public String getMedicalPolicyName() {
        return medicalPolicyName;
    }

    public void setMedicalPolicyName(String medicalPolicyName) {
        this.medicalPolicyName = medicalPolicyName;
    }

    public ReturnValue withMedicalPolicyName(String medicalPolicyName) {
        this.medicalPolicyName = medicalPolicyName;
        return this;
    }

    public int getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(int remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

    public ReturnValue withRemainingBalance(int remainingBalance) {
        this.remainingBalance = remainingBalance;
        return this;
    }

    public Object getEmployeeFamilyID() {
        return employeeFamilyID;
    }

    public void setEmployeeFamilyID(Object employeeFamilyID) {
        this.employeeFamilyID = employeeFamilyID;
    }

    public ReturnValue withEmployeeFamilyID(Object employeeFamilyID) {
        this.employeeFamilyID = employeeFamilyID;
        return this;
    }

    public String getEmployeeFamilyName() {
        return employeeFamilyName;
    }

    public void setEmployeeFamilyName(String employeeFamilyName) {
        this.employeeFamilyName = employeeFamilyName;
    }

    public ReturnValue withEmployeeFamilyName(String employeeFamilyName) {
        this.employeeFamilyName = employeeFamilyName;
        return this;
    }

    public int getMedicalClaimPolicyID() {
        return medicalClaimPolicyID;
    }

    public void setMedicalClaimPolicyID(int medicalClaimPolicyID) {
        this.medicalClaimPolicyID = medicalClaimPolicyID;
    }

    public ReturnValue withMedicalClaimPolicyID(int medicalClaimPolicyID) {
        this.medicalClaimPolicyID = medicalClaimPolicyID;
        return this;
    }

    public int getTotalClaim() {
        return totalClaim;
    }

    public void setTotalClaim(int totalClaim) {
        this.totalClaim = totalClaim;
    }

    public ReturnValue withTotalClaim(int totalClaim) {
        this.totalClaim = totalClaim;
        return this;
    }

    public float getTotalReimbursement() {
        return totalReimbursement;
    }

    public void setTotalReimbursement(float totalReimbursement) {
        this.totalReimbursement = totalReimbursement;
    }

    public ReturnValue withTotalReimbursement(float totalReimbursement) {
        this.totalReimbursement = totalReimbursement;
        return this;
    }

    public Object getAttachmentFile() {
        return attachmentFile;
    }

    public void setAttachmentFile(Object attachmentFile) {
        this.attachmentFile = attachmentFile;
    }

    public ReturnValue withAttachmentFile(Object attachmentFile) {
        this.attachmentFile = attachmentFile;
        return this;
    }

    public Object getAttachmentID() {
        return attachmentID;
    }

    public void setAttachmentID(Object attachmentID) {
        this.attachmentID = attachmentID;
    }

    public ReturnValue withAttachmentID(Object attachmentID) {
        this.attachmentID = attachmentID;
        return this;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public ReturnValue withReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
        return this;
    }

    public Object getDecisionNumber() {
        return decisionNumber;
    }

    public void setDecisionNumber(Object decisionNumber) {
        this.decisionNumber = decisionNumber;
    }

    public ReturnValue withDecisionNumber(Object decisionNumber) {
        this.decisionNumber = decisionNumber;
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

    public Object getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Object approvedDate) {
        this.approvedDate = approvedDate;
    }

    public ReturnValue withApprovedDate(Object approvedDate) {
        this.approvedDate = approvedDate;
        return this;
    }

    public int getClaim() {
        return claim;
    }

    public void setClaim(int claim) {
        this.claim = claim;
    }

    public ReturnValue withClaim(int claim) {
        this.claim = claim;
        return this;
    }

    public String getTransactionStatusSaveOrSubmit() {
        return transactionStatusSaveOrSubmit;
    }

    public void setTransactionStatusSaveOrSubmit(String transactionStatusSaveOrSubmit) {
        this.transactionStatusSaveOrSubmit = transactionStatusSaveOrSubmit;
    }

    public ReturnValue withTransactionStatusSaveOrSubmit(String transactionStatusSaveOrSubmit) {
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

    public List<String> getExclusionFields() {
        return exclusionFields;
    }

    public void setExclusionFields(List<String> exclusionFields) {
        this.exclusionFields = exclusionFields;
    }

    public ReturnValue withExclusionFields(List<String> exclusionFields) {
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
