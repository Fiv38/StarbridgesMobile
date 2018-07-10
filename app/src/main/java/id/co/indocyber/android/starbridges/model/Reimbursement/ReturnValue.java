
package id.co.indocyber.android.starbridges.model.Reimbursement;

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
    @SerializedName("ApprovedDate")
    @Expose
    private Object approvedDate;
    @SerializedName("ProcessPeriod")
    @Expose
    private Object processPeriod;
    @SerializedName("IsPreProcess")
    @Expose
    private boolean isPreProcess;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Amount")
    @Expose
    private String amount;
    @SerializedName("TransactionDate")
    @Expose
    private String transactionDate;
    @SerializedName("AttachmentFile")
    @Expose
    private String attachmentFile;
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
    private final static long serialVersionUID = 7555449033137547319L;

    protected ReturnValue(Parcel in) {
        this.iD = ((String) in.readValue((String.class.getClassLoader())));
        this.decisionNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.approvedDate = ((Object) in.readValue((Object.class.getClassLoader())));
        this.processPeriod = ((Object) in.readValue((Object.class.getClassLoader())));
        this.isPreProcess = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.amount = ((String) in.readValue((String.class.getClassLoader())));
        this.transactionDate = ((String) in.readValue((String.class.getClassLoader())));
        this.attachmentFile = ((String) in.readValue((String.class.getClassLoader())));
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
     * @param processPeriod
     * @param fullAccess
     * @param attachmentFile
     * @param approvedDate
     * @param type
     * @param iD
     * @param transactionDate
     * @param exclusionFields
     * @param amount
     * @param decisionNumber
     * @param description
     * @param isPreProcess
     */
    public ReturnValue(String iD, String decisionNumber, Object approvedDate, Object processPeriod, boolean isPreProcess, String type, String description, String amount, String transactionDate, String attachmentFile, boolean fullAccess, List<Object> exclusionFields, String accessibilityAttribute) {
        super();
        this.iD = iD;
        this.decisionNumber = decisionNumber;
        this.approvedDate = approvedDate;
        this.processPeriod = processPeriod;
        this.isPreProcess = isPreProcess;
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.attachmentFile = attachmentFile;
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

    public Object getProcessPeriod() {
        return processPeriod;
    }

    public void setProcessPeriod(Object processPeriod) {
        this.processPeriod = processPeriod;
    }

    public ReturnValue withProcessPeriod(Object processPeriod) {
        this.processPeriod = processPeriod;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ReturnValue withType(String type) {
        this.type = type;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public ReturnValue withAmount(String amount) {
        this.amount = amount;
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
        dest.writeValue(approvedDate);
        dest.writeValue(processPeriod);
        dest.writeValue(isPreProcess);
        dest.writeValue(type);
        dest.writeValue(description);
        dest.writeValue(amount);
        dest.writeValue(transactionDate);
        dest.writeValue(attachmentFile);
        dest.writeValue(fullAccess);
        dest.writeList(exclusionFields);
        dest.writeValue(accessibilityAttribute);
    }

    public int describeContents() {
        return  0;
    }

}
