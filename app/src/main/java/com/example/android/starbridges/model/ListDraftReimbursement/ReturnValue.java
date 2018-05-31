
package com.example.android.starbridges.model.ListDraftReimbursement;

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
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("IsPreProcess")
    @Expose
    private boolean isPreProcess;
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
    private Object attachmentFile;
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
    private final static long serialVersionUID = -2633549520083906082L;

    protected ReturnValue(Parcel in) {
        this.iD = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.isPreProcess = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.amount = ((String) in.readValue((String.class.getClassLoader())));
        this.transactionDate = ((String) in.readValue((String.class.getClassLoader())));
        this.attachmentFile = ((Object) in.readValue((Object.class.getClassLoader())));
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
     * @param amount
     * @param accessibilityAttribute
     * @param fullAccess
     * @param description
     * @param attachmentFile
     * @param isPreProcess
     * @param type
     * @param iD
     * @param transactionDate
     * @param exclusionFields
     */
    public ReturnValue(String iD, String type, boolean isPreProcess, String description, String amount, String transactionDate, Object attachmentFile, boolean fullAccess, List<Object> exclusionFields, String accessibilityAttribute) {
        super();
        this.iD = iD;
        this.type = type;
        this.isPreProcess = isPreProcess;
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
        dest.writeValue(type);
        dest.writeValue(isPreProcess);
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
