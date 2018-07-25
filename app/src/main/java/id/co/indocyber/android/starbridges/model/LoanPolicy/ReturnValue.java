
package id.co.indocyber.android.starbridges.model.LoanPolicy;

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
    private Integer iD;
    @SerializedName("Code")
    @Expose
    private Object code;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Description")
    @Expose
    private Object description;
    @SerializedName("CreditTypeID")
    @Expose
    private Integer creditTypeID;
    @SerializedName("Percentage")
    @Expose
    private Object percentage;
    @SerializedName("BusinessGroupID")
    @Expose
    private Integer businessGroupID;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("CreatedBy")
    @Expose
    private Integer createdBy;
    @SerializedName("UpdatedDate")
    @Expose
    private Object updatedDate;
    @SerializedName("UpdatedBy")
    @Expose
    private Object updatedBy;
    @SerializedName("DelDate")
    @Expose
    private Object delDate;
    @SerializedName("TotalCredit")
    @Expose
    private Object totalCredit;
    @SerializedName("CreditType")
    @Expose
    private Object creditType;
    @SerializedName("LoanPolicyPositions")
    @Expose
    private List<Object> loanPolicyPositions = null;
    @SerializedName("LoanPolicyOrgUnits")
    @Expose
    private List<Object> loanPolicyOrgUnits = null;
    @SerializedName("LoanPolicyLocations")
    @Expose
    private List<Object> loanPolicyLocations = null;
    @SerializedName("LoanPolicyLevels")
    @Expose
    private List<Object> loanPolicyLevels = null;
    @SerializedName("LoanPolicyGrades")
    @Expose
    private List<Object> loanPolicyGrades = null;
    @SerializedName("LoanPolicyEmployees")
    @Expose
    private List<Object> loanPolicyEmployees = null;
    @SerializedName("EmployeeLoanTransactions")
    @Expose
    private List<Object> employeeLoanTransactions = null;
    @SerializedName("EmployeeLoanBalances")
    @Expose
    private List<Object> employeeLoanBalances = null;
    @SerializedName("LoanSettingPolicies")
    @Expose
    private List<Object> loanSettingPolicies = null;
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
    private final static long serialVersionUID = -6983787876717622462L;

    protected ReturnValue(Parcel in) {
        this.iD = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.code = ((Object) in.readValue((Object.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((Object) in.readValue((Object.class.getClassLoader())));
        this.creditTypeID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.percentage = ((Object) in.readValue((Object.class.getClassLoader())));
        this.businessGroupID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.createdDate = ((String) in.readValue((String.class.getClassLoader())));
        this.createdBy = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.updatedDate = ((Object) in.readValue((Object.class.getClassLoader())));
        this.updatedBy = ((Object) in.readValue((Object.class.getClassLoader())));
        this.delDate = ((Object) in.readValue((Object.class.getClassLoader())));
        this.totalCredit = ((Object) in.readValue((Object.class.getClassLoader())));
        this.creditType = ((Object) in.readValue((Object.class.getClassLoader())));
        in.readList(this.loanPolicyPositions, (Object.class.getClassLoader()));
        in.readList(this.loanPolicyOrgUnits, (Object.class.getClassLoader()));
        in.readList(this.loanPolicyLocations, (Object.class.getClassLoader()));
        in.readList(this.loanPolicyLevels, (Object.class.getClassLoader()));
        in.readList(this.loanPolicyGrades, (Object.class.getClassLoader()));
        in.readList(this.loanPolicyEmployees, (Object.class.getClassLoader()));
        in.readList(this.employeeLoanTransactions, (Object.class.getClassLoader()));
        in.readList(this.employeeLoanBalances, (Object.class.getClassLoader()));
        in.readList(this.loanSettingPolicies, (Object.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ReturnValue() {
    }

    /**
     * 
     * @param loanSettingPolicies
     * @param creditTypeID
     * @param loanPolicyEmployees
     * @param loanPolicyLevels
     * @param businessGroupID
     * @param percentage
     * @param delDate
     * @param updatedDate
     * @param loanPolicyGrades
     * @param code
     * @param employeeLoanBalances
     * @param employeeLoanTransactions
     * @param iD
     * @param updatedBy
     * @param loanPolicyOrgUnits
     * @param loanPolicyPositions
     * @param creditType
     * @param totalCredit
     * @param createdBy
     * @param description
     * @param name
     * @param loanPolicyLocations
     * @param createdDate
     */
    public ReturnValue(Integer iD, Object code, String name, Object description, Integer creditTypeID, Object percentage, Integer businessGroupID, String createdDate, Integer createdBy, Object updatedDate, Object updatedBy, Object delDate, Object totalCredit, Object creditType, List<Object> loanPolicyPositions, List<Object> loanPolicyOrgUnits, List<Object> loanPolicyLocations, List<Object> loanPolicyLevels, List<Object> loanPolicyGrades, List<Object> loanPolicyEmployees, List<Object> employeeLoanTransactions, List<Object> employeeLoanBalances, List<Object> loanSettingPolicies) {
        super();
        this.iD = iD;
        this.code = code;
        this.name = name;
        this.description = description;
        this.creditTypeID = creditTypeID;
        this.percentage = percentage;
        this.businessGroupID = businessGroupID;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.updatedDate = updatedDate;
        this.updatedBy = updatedBy;
        this.delDate = delDate;
        this.totalCredit = totalCredit;
        this.creditType = creditType;
        this.loanPolicyPositions = loanPolicyPositions;
        this.loanPolicyOrgUnits = loanPolicyOrgUnits;
        this.loanPolicyLocations = loanPolicyLocations;
        this.loanPolicyLevels = loanPolicyLevels;
        this.loanPolicyGrades = loanPolicyGrades;
        this.loanPolicyEmployees = loanPolicyEmployees;
        this.employeeLoanTransactions = employeeLoanTransactions;
        this.employeeLoanBalances = employeeLoanBalances;
        this.loanSettingPolicies = loanSettingPolicies;
    }

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public ReturnValue withID(Integer iD) {
        this.iD = iD;
        return this;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public ReturnValue withCode(Object code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReturnValue withName(String name) {
        this.name = name;
        return this;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public ReturnValue withDescription(Object description) {
        this.description = description;
        return this;
    }

    public Integer getCreditTypeID() {
        return creditTypeID;
    }

    public void setCreditTypeID(Integer creditTypeID) {
        this.creditTypeID = creditTypeID;
    }

    public ReturnValue withCreditTypeID(Integer creditTypeID) {
        this.creditTypeID = creditTypeID;
        return this;
    }

    public Object getPercentage() {
        return percentage;
    }

    public void setPercentage(Object percentage) {
        this.percentage = percentage;
    }

    public ReturnValue withPercentage(Object percentage) {
        this.percentage = percentage;
        return this;
    }

    public Integer getBusinessGroupID() {
        return businessGroupID;
    }

    public void setBusinessGroupID(Integer businessGroupID) {
        this.businessGroupID = businessGroupID;
    }

    public ReturnValue withBusinessGroupID(Integer businessGroupID) {
        this.businessGroupID = businessGroupID;
        return this;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public ReturnValue withCreatedDate(String createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public ReturnValue withCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Object getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Object updatedDate) {
        this.updatedDate = updatedDate;
    }

    public ReturnValue withUpdatedDate(Object updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    public Object getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Object updatedBy) {
        this.updatedBy = updatedBy;
    }

    public ReturnValue withUpdatedBy(Object updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public Object getDelDate() {
        return delDate;
    }

    public void setDelDate(Object delDate) {
        this.delDate = delDate;
    }

    public ReturnValue withDelDate(Object delDate) {
        this.delDate = delDate;
        return this;
    }

    public Object getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(Object totalCredit) {
        this.totalCredit = totalCredit;
    }

    public ReturnValue withTotalCredit(Object totalCredit) {
        this.totalCredit = totalCredit;
        return this;
    }

    public Object getCreditType() {
        return creditType;
    }

    public void setCreditType(Object creditType) {
        this.creditType = creditType;
    }

    public ReturnValue withCreditType(Object creditType) {
        this.creditType = creditType;
        return this;
    }

    public List<Object> getLoanPolicyPositions() {
        return loanPolicyPositions;
    }

    public void setLoanPolicyPositions(List<Object> loanPolicyPositions) {
        this.loanPolicyPositions = loanPolicyPositions;
    }

    public ReturnValue withLoanPolicyPositions(List<Object> loanPolicyPositions) {
        this.loanPolicyPositions = loanPolicyPositions;
        return this;
    }

    public List<Object> getLoanPolicyOrgUnits() {
        return loanPolicyOrgUnits;
    }

    public void setLoanPolicyOrgUnits(List<Object> loanPolicyOrgUnits) {
        this.loanPolicyOrgUnits = loanPolicyOrgUnits;
    }

    public ReturnValue withLoanPolicyOrgUnits(List<Object> loanPolicyOrgUnits) {
        this.loanPolicyOrgUnits = loanPolicyOrgUnits;
        return this;
    }

    public List<Object> getLoanPolicyLocations() {
        return loanPolicyLocations;
    }

    public void setLoanPolicyLocations(List<Object> loanPolicyLocations) {
        this.loanPolicyLocations = loanPolicyLocations;
    }

    public ReturnValue withLoanPolicyLocations(List<Object> loanPolicyLocations) {
        this.loanPolicyLocations = loanPolicyLocations;
        return this;
    }

    public List<Object> getLoanPolicyLevels() {
        return loanPolicyLevels;
    }

    public void setLoanPolicyLevels(List<Object> loanPolicyLevels) {
        this.loanPolicyLevels = loanPolicyLevels;
    }

    public ReturnValue withLoanPolicyLevels(List<Object> loanPolicyLevels) {
        this.loanPolicyLevels = loanPolicyLevels;
        return this;
    }

    public List<Object> getLoanPolicyGrades() {
        return loanPolicyGrades;
    }

    public void setLoanPolicyGrades(List<Object> loanPolicyGrades) {
        this.loanPolicyGrades = loanPolicyGrades;
    }

    public ReturnValue withLoanPolicyGrades(List<Object> loanPolicyGrades) {
        this.loanPolicyGrades = loanPolicyGrades;
        return this;
    }

    public List<Object> getLoanPolicyEmployees() {
        return loanPolicyEmployees;
    }

    public void setLoanPolicyEmployees(List<Object> loanPolicyEmployees) {
        this.loanPolicyEmployees = loanPolicyEmployees;
    }

    public ReturnValue withLoanPolicyEmployees(List<Object> loanPolicyEmployees) {
        this.loanPolicyEmployees = loanPolicyEmployees;
        return this;
    }

    public List<Object> getEmployeeLoanTransactions() {
        return employeeLoanTransactions;
    }

    public void setEmployeeLoanTransactions(List<Object> employeeLoanTransactions) {
        this.employeeLoanTransactions = employeeLoanTransactions;
    }

    public ReturnValue withEmployeeLoanTransactions(List<Object> employeeLoanTransactions) {
        this.employeeLoanTransactions = employeeLoanTransactions;
        return this;
    }

    public List<Object> getEmployeeLoanBalances() {
        return employeeLoanBalances;
    }

    public void setEmployeeLoanBalances(List<Object> employeeLoanBalances) {
        this.employeeLoanBalances = employeeLoanBalances;
    }

    public ReturnValue withEmployeeLoanBalances(List<Object> employeeLoanBalances) {
        this.employeeLoanBalances = employeeLoanBalances;
        return this;
    }

    public List<Object> getLoanSettingPolicies() {
        return loanSettingPolicies;
    }

    public void setLoanSettingPolicies(List<Object> loanSettingPolicies) {
        this.loanSettingPolicies = loanSettingPolicies;
    }

    public ReturnValue withLoanSettingPolicies(List<Object> loanSettingPolicies) {
        this.loanSettingPolicies = loanSettingPolicies;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(iD);
        dest.writeValue(code);
        dest.writeValue(name);
        dest.writeValue(description);
        dest.writeValue(creditTypeID);
        dest.writeValue(percentage);
        dest.writeValue(businessGroupID);
        dest.writeValue(createdDate);
        dest.writeValue(createdBy);
        dest.writeValue(updatedDate);
        dest.writeValue(updatedBy);
        dest.writeValue(delDate);
        dest.writeValue(totalCredit);
        dest.writeValue(creditType);
        dest.writeList(loanPolicyPositions);
        dest.writeList(loanPolicyOrgUnits);
        dest.writeList(loanPolicyLocations);
        dest.writeList(loanPolicyLevels);
        dest.writeList(loanPolicyGrades);
        dest.writeList(loanPolicyEmployees);
        dest.writeList(employeeLoanTransactions);
        dest.writeList(employeeLoanBalances);
        dest.writeList(loanSettingPolicies);
    }

    public int describeContents() {
        return  0;
    }

    @Override
    public String toString() {
        return name;
    }
}
