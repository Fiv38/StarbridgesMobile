
package id.co.indocyber.android.starbridges.model.ListTransactionInformation;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReturnValue implements Serializable, Parcelable
{

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("PolicyName")
    @Expose
    private String policyName;
    @SerializedName("RemainingLoan")
    @Expose
    private String remainingLoan;
    @SerializedName("RemainingInstallment")
    @Expose
    private String remainingInstallment;
    @SerializedName("LoanBalanceID")
    @Expose
    private String loanBalanceID;
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
    private final static long serialVersionUID = -3737276267653789532L;

    protected ReturnValue(Parcel in) {
        this.iD = ((String) in.readValue((String.class.getClassLoader())));
        this.policyName = ((String) in.readValue((String.class.getClassLoader())));
        this.remainingLoan = ((String) in.readValue((String.class.getClassLoader())));
        this.remainingInstallment = ((String) in.readValue((String.class.getClassLoader())));
        this.loanBalanceID = ((String) in.readValue((String.class.getClassLoader())));
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
     * @param remainingInstallment
     * @param accessibilityAttribute
     * @param policyName
     * @param fullAccess
     * @param remainingLoan
     * @param loanBalanceID
     * @param iD
     * @param exclusionFields
     */
    public ReturnValue(String iD, String policyName, String remainingLoan, String remainingInstallment, String loanBalanceID, boolean fullAccess, List<Object> exclusionFields, String accessibilityAttribute) {
        super();
        this.iD = iD;
        this.policyName = policyName;
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

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public ReturnValue withPolicyName(String policyName) {
        this.policyName = policyName;
        return this;
    }

    public String getRemainingLoan() {
        return remainingLoan;
    }

    public void setRemainingLoan(String remainingLoan) {
        this.remainingLoan = remainingLoan;
    }

    public ReturnValue withRemainingLoan(String remainingLoan) {
        this.remainingLoan = remainingLoan;
        return this;
    }

    public String getRemainingInstallment() {
        return remainingInstallment;
    }

    public void setRemainingInstallment(String remainingInstallment) {
        this.remainingInstallment = remainingInstallment;
    }

    public ReturnValue withRemainingInstallment(String remainingInstallment) {
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
        dest.writeValue(policyName);
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
