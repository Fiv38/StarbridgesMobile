
package id.co.indocyber.android.starbridges.model.ListDraftTransactionLoan;

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
    @SerializedName("TransactionType")
    @Expose
    private String transactionType;
    @SerializedName("Policy")
    @Expose
    private String policy;
    @SerializedName("StartDate")
    @Expose
    private String startDate;
    @SerializedName("CreditAmount")
    @Expose
    private Integer creditAmount;
    @SerializedName("Installment")
    @Expose
    private Object installment;
    @SerializedName("Amount")
    @Expose
    private Integer amount;
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
    private final static long serialVersionUID = -6431974618304406035L;

    protected ReturnValue(Parcel in) {
        this.iD = ((String) in.readValue((String.class.getClassLoader())));
        this.transactionType = ((String) in.readValue((String.class.getClassLoader())));
        this.policy = ((String) in.readValue((String.class.getClassLoader())));
        this.startDate = ((String) in.readValue((String.class.getClassLoader())));
        this.creditAmount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.installment = ((Object) in.readValue((Object.class.getClassLoader())));
        this.amount = ((Integer) in.readValue((Integer.class.getClassLoader())));
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
     * @param amount
     * @param installment
     * @param startDate
     * @param transactionType
     * @param accessibilityAttribute
     * @param fullAccess
     * @param creditAmount
     * @param policy
     * @param iD
     * @param exclusionFields
     */
    public ReturnValue(String iD, String transactionType, String policy, String startDate, Integer creditAmount, Object installment, Integer amount, Boolean fullAccess, List<Object> exclusionFields, String accessibilityAttribute) {
        super();
        this.iD = iD;
        this.transactionType = transactionType;
        this.policy = policy;
        this.startDate = startDate;
        this.creditAmount = creditAmount;
        this.installment = installment;
        this.amount = amount;
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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public ReturnValue withTransactionType(String transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public ReturnValue withPolicy(String policy) {
        this.policy = policy;
        return this;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public ReturnValue withStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public Integer getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Integer creditAmount) {
        this.creditAmount = creditAmount;
    }

    public ReturnValue withCreditAmount(Integer creditAmount) {
        this.creditAmount = creditAmount;
        return this;
    }

    public Object getInstallment() {
        return installment;
    }

    public void setInstallment(Object installment) {
        this.installment = installment;
    }

    public ReturnValue withInstallment(Object installment) {
        this.installment = installment;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public ReturnValue withAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public Boolean getFullAccess() {
        return fullAccess;
    }

    public void setFullAccess(Boolean fullAccess) {
        this.fullAccess = fullAccess;
    }

    public ReturnValue withFullAccess(Boolean fullAccess) {
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
        dest.writeValue(transactionType);
        dest.writeValue(policy);
        dest.writeValue(startDate);
        dest.writeValue(creditAmount);
        dest.writeValue(installment);
        dest.writeValue(amount);
        dest.writeValue(fullAccess);
        dest.writeList(exclusionFields);
        dest.writeValue(accessibilityAttribute);
    }

    public int describeContents() {
        return  0;
    }

}
