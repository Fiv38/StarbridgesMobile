
package com.example.android.starbridges.model.EditReimbursement;

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
    @SerializedName("EmployeeID")
    @Expose
    private String employeeID;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Amount")
    @Expose
    private int amount;
    @SerializedName("ReimbursementTypeID")
    @Expose
    private int reimbursementTypeID;
    @SerializedName("IsPreProcess")
    @Expose
    private boolean isPreProcess;
    @SerializedName("TransactionDate")
    @Expose
    private String transactionDate;
    @SerializedName("TransactionStatusID")
    @Expose
    private int transactionStatusID;
    @SerializedName("DecisionNumber")
    @Expose
    private String decisionNumber;
    @SerializedName("AttachmentFile")
    @Expose
    private Object attachmentFile;
    @SerializedName("AttachmentID")
    @Expose
    private Object attachmentID;
    @SerializedName("Message")
    @Expose
    private Object message;
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
    private final static long serialVersionUID = -4725922101336877419L;

    protected ReturnValue(Parcel in) {
        this.iD = ((String) in.readValue((String.class.getClassLoader())));
        this.employeeID = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.amount = ((int) in.readValue((int.class.getClassLoader())));
        this.reimbursementTypeID = ((int) in.readValue((int.class.getClassLoader())));
        this.isPreProcess = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.transactionDate = ((String) in.readValue((String.class.getClassLoader())));
        this.transactionStatusID = ((int) in.readValue((int.class.getClassLoader())));
        this.decisionNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.attachmentFile = ((Object) in.readValue((Object.class.getClassLoader())));
        this.attachmentID = ((Object) in.readValue((Object.class.getClassLoader())));
        this.message = ((Object) in.readValue((Object.class.getClassLoader())));
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
     * @param employeeID
     * @param reimbursementTypeID
     * @param fullAccess
     * @param attachmentFile
     * @param iD
     * @param transactionDate
     * @param exclusionFields
     * @param amount
     * @param message
     * @param decisionNumber
     * @param description
     * @param isPreProcess
     * @param attachmentID
     * @param transactionStatusID
     */
    public ReturnValue(String iD, String employeeID, String description, int amount, int reimbursementTypeID, boolean isPreProcess, String transactionDate, int transactionStatusID, String decisionNumber, Object attachmentFile, Object attachmentID, Object message, boolean fullAccess, List<Object> exclusionFields, String accessibilityAttribute) {
        super();
        this.iD = iD;
        this.employeeID = employeeID;
        this.description = description;
        this.amount = amount;
        this.reimbursementTypeID = reimbursementTypeID;
        this.isPreProcess = isPreProcess;
        this.transactionDate = transactionDate;
        this.transactionStatusID = transactionStatusID;
        this.decisionNumber = decisionNumber;
        this.attachmentFile = attachmentFile;
        this.attachmentID = attachmentID;
        this.message = message;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ReturnValue withDescription(String description) {
        this.description = description;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ReturnValue withAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public int getReimbursementTypeID() {
        return reimbursementTypeID;
    }

    public void setReimbursementTypeID(int reimbursementTypeID) {
        this.reimbursementTypeID = reimbursementTypeID;
    }

    public ReturnValue withReimbursementTypeID(int reimbursementTypeID) {
        this.reimbursementTypeID = reimbursementTypeID;
        return this;
    }

    public boolean isIsPreProcess() {
        return isPreProcess;
    }

    public void setIsPreProcess(boolean isPreProcess) {
        this.isPreProcess = isPreProcess;
    }

    public ReturnValue withIsPreProcess(boolean isPreProcess) {
        this.isPreProcess = isPreProcess;
        return this;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public ReturnValue withTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
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

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public ReturnValue withMessage(Object message) {
        this.message = message;
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
        dest.writeValue(description);
        dest.writeValue(amount);
        dest.writeValue(reimbursementTypeID);
        dest.writeValue(isPreProcess);
        dest.writeValue(transactionDate);
        dest.writeValue(transactionStatusID);
        dest.writeValue(decisionNumber);
        dest.writeValue(attachmentFile);
        dest.writeValue(attachmentID);
        dest.writeValue(message);
        dest.writeValue(fullAccess);
        dest.writeList(exclusionFields);
        dest.writeValue(accessibilityAttribute);
    }

    public int describeContents() {
        return  0;
    }

}
