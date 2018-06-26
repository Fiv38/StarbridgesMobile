
package com.example.android.starbridges.model.listmedicalclaim;

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
    @SerializedName("Family")
    @Expose
    private String family;
    @SerializedName("PolicyName")
    @Expose
    private String policyName;
    @SerializedName("MedicalSupport")
    @Expose
    private String medicalSupport;
    @SerializedName("Service")
    @Expose
    private String service;
    @SerializedName("Institution")
    @Expose
    private String institution;
    @SerializedName("Claim")
    @Expose
    private String claim;
    @SerializedName("Reimbursement")
    @Expose
    private String reimbursement;
    @SerializedName("ReceiptDate")
    @Expose
    private String receiptDate;
    @SerializedName("AttachmentFile")
    @Expose
    private Object attachmentFile;
    @SerializedName("FullAccess")
    @Expose
    private Boolean fullAccess;
    @SerializedName("ExclusionFields")
    @Expose
    private List<Object> exclusionFields = null;
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
    private final static long serialVersionUID = 3301520482751459769L;

    protected ReturnValue(Parcel in) {
        this.iD = ((String) in.readValue((String.class.getClassLoader())));
        this.decisionNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.family = ((String) in.readValue((String.class.getClassLoader())));
        this.policyName = ((String) in.readValue((String.class.getClassLoader())));
        this.medicalSupport = ((String) in.readValue((String.class.getClassLoader())));
        this.service = ((String) in.readValue((String.class.getClassLoader())));
        this.institution = ((String) in.readValue((String.class.getClassLoader())));
        this.claim = ((String) in.readValue((String.class.getClassLoader())));
        this.reimbursement = ((String) in.readValue((String.class.getClassLoader())));
        this.receiptDate = ((String) in.readValue((String.class.getClassLoader())));
        this.attachmentFile = ((Object) in.readValue((Object.class.getClassLoader())));
        this.fullAccess = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
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
     * @param accessibilityAttribute
     * @param fullAccess
     * @param attachmentFile
     * @param reimbursement
     * @param iD
     * @param exclusionFields
     * @param decisionNumber
     * @param policyName
     * @param receiptDate
     * @param family
     * @param service
     * @param claim
     * @param medicalSupport
     * @param institution
     */
    public ReturnValue(String iD, String decisionNumber, String family, String policyName, String medicalSupport, String service, String institution, String claim, String reimbursement, String receiptDate, Object attachmentFile, Boolean fullAccess, List<Object> exclusionFields, String accessibilityAttribute) {
        super();
        this.iD = iD;
        this.decisionNumber = decisionNumber;
        this.family = family;
        this.policyName = policyName;
        this.medicalSupport = medicalSupport;
        this.service = service;
        this.institution = institution;
        this.claim = claim;
        this.reimbursement = reimbursement;
        this.receiptDate = receiptDate;
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

    public String getDecisionNumber() {
        return decisionNumber;
    }

    public void setDecisionNumber(String decisionNumber) {
        this.decisionNumber = decisionNumber;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getMedicalSupport() {
        return medicalSupport;
    }

    public void setMedicalSupport(String medicalSupport) {
        this.medicalSupport = medicalSupport;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getClaim() {
        return claim;
    }

    public void setClaim(String claim) {
        this.claim = claim;
    }

    public String getReimbursement() {
        return reimbursement;
    }

    public void setReimbursement(String reimbursement) {
        this.reimbursement = reimbursement;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Object getAttachmentFile() {
        return attachmentFile;
    }

    public void setAttachmentFile(Object attachmentFile) {
        this.attachmentFile = attachmentFile;
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
        dest.writeValue(family);
        dest.writeValue(policyName);
        dest.writeValue(medicalSupport);
        dest.writeValue(service);
        dest.writeValue(institution);
        dest.writeValue(claim);
        dest.writeValue(reimbursement);
        dest.writeValue(receiptDate);
        dest.writeValue(attachmentFile);
        dest.writeValue(fullAccess);
        dest.writeList(exclusionFields);
        dest.writeValue(accessibilityAttribute);
    }

    public int describeContents() {
        return  0;
    }

}
