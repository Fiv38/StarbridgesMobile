
package id.co.indocyber.android.starbridges.model.ListDraftTransactionLoanApproved;

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
    @SerializedName("Policy")
    @Expose
    private String policy;
    @SerializedName("RemainingLoan")
    @Expose
    private Integer remainingLoan;
    @SerializedName("RemainingInstallment")
    @Expose
    private Integer remainingInstallment;
    @SerializedName("LoanBalanceID")
    @Expose
    private String loanBalanceID;
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
    private final static long serialVersionUID = -1250088256808815609L;

    protected ReturnValue(Parcel in) {
        this.iD = ((String) in.readValue((String.class.getClassLoader())));
        this.policy = ((String) in.readValue((String.class.getClassLoader())));
        this.remainingLoan = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.remainingInstallment = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.loanBalanceID = ((String) in.readValue((String.class.getClassLoader())));
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
     * @param remainingInstallment
     * @param accessibilityAttribute
     * @param fullAccess
     * @param remainingLoan
     * @param policy
     * @param loanBalanceID
     * @param iD
     * @param exclusionFields
     */
    public ReturnValue(String iD, String policy, Integer remainingLoan, Integer remainingInstallment, String loanBalanceID, Boolean fullAccess, List<Object> exclusionFields, String accessibilityAttribute) {
        super();
        this.iD = iD;
        this.policy = policy;
        this.remainingLoan = remainingLoan;
        this.remainingInstallment = remainingInstallment;
        this.loanBalanceID = loanBalanceID;
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

    public Integer getRemainingLoan() {
        return remainingLoan;
    }

    public void setRemainingLoan(Integer remainingLoan) {
        this.remainingLoan = remainingLoan;
    }

    public ReturnValue withRemainingLoan(Integer remainingLoan) {
        this.remainingLoan = remainingLoan;
        return this;
    }

    public Integer getRemainingInstallment() {
        return remainingInstallment;
    }

    public void setRemainingInstallment(Integer remainingInstallment) {
        this.remainingInstallment = remainingInstallment;
    }

    public ReturnValue withRemainingInstallment(Integer remainingInstallment) {
        this.remainingInstallment = remainingInstallment;
        return this;
    }

    public String getLoanBalanceID() {
        return loanBalanceID;
    }

    public void setLoanBalanceID(String loanBalanceID) {
        this.loanBalanceID = loanBalanceID;
    }

    public ReturnValue withLoanBalanceID(String loanBalanceID) {
        this.loanBalanceID = loanBalanceID;
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
        dest.writeValue(policy);
        dest.writeValue(remainingLoan);
        dest.writeValue(remainingInstallment);
        dest.writeValue(loanBalanceID);
        dest.writeValue(fullAccess);
        dest.writeList(exclusionFields);
        dest.writeValue(accessibilityAttribute);
    }

    public int describeContents() {
        return  0;
    }

}
