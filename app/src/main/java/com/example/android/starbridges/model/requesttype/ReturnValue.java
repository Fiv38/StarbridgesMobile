
package com.example.android.starbridges.model.requesttype;

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
    private Integer iD;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Description")
    @Expose
    private Object description;
    @SerializedName("IsReduceBalance")
    @Expose
    private Boolean isReduceBalance;
    @SerializedName("IsHalfDay")
    @Expose
    private Boolean isHalfDay;
    @SerializedName("IsPaidLeave")
    @Expose
    private Boolean isPaidLeave;
    @SerializedName("MinBandOfService")
    @Expose
    private Integer minBandOfService;
    @SerializedName("MinRequestInterval")
    @Expose
    private Object minRequestInterval;
    @SerializedName("IsFixedUnit")
    @Expose
    private Boolean isFixedUnit;
    @SerializedName("FixedUnit")
    @Expose
    private Object fixedUnit;
    @SerializedName("MaxRequestUnit")
    @Expose
    private Integer maxRequestUnit;
    @SerializedName("MinRequestUnit")
    @Expose
    private Integer minRequestUnit;
    @SerializedName("UseMaxOccurence")
    @Expose
    private Boolean useMaxOccurence;
    @SerializedName("OccurenceIntervalTypeID")
    @Expose
    private Object occurenceIntervalTypeID;
    @SerializedName("MaxOccurenceInterval")
    @Expose
    private Object maxOccurenceInterval;
    @SerializedName("OccurenceQuantity")
    @Expose
    private Object occurenceQuantity;
    @SerializedName("BusinessGroupID")
    @Expose
    private Integer businessGroupID;
    @SerializedName("CreatedBy")
    @Expose
    private Integer createdBy;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("UpdatedBy")
    @Expose
    private Object updatedBy;
    @SerializedName("UpdatedDate")
    @Expose
    private Object updatedDate;
    @SerializedName("DelDate")
    @Expose
    private Object delDate;
    @SerializedName("OccurenceIntervalType")
    @Expose
    private Object occurenceIntervalType;
    @SerializedName("LeaveRequestTransactions")
    @Expose
    private List<Object> leaveRequestTransactions = null;
    @SerializedName("LeaveRequestPositions")
    @Expose
    private List<Object> leaveRequestPositions = null;
    @SerializedName("LeaveRequestOrgUnits")
    @Expose
    private List<Object> leaveRequestOrgUnits = null;
    @SerializedName("LeaveRequestLocations")
    @Expose
    private List<Object> leaveRequestLocations = null;
    @SerializedName("LeaveRequestLevels")
    @Expose
    private List<Object> leaveRequestLevels = null;
    @SerializedName("LeaveRequestGrades")
    @Expose
    private List<Object> leaveRequestGrades = null;
    @SerializedName("JointLeaveTransactions")
    @Expose
    private List<Object> jointLeaveTransactions = null;
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
    private final static long serialVersionUID = -3744094056438177872L;

    protected ReturnValue(Parcel in) {
        this.iD = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.code = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((Object) in.readValue((Object.class.getClassLoader())));
        this.isReduceBalance = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.isHalfDay = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.isPaidLeave = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.minBandOfService = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.minRequestInterval = ((Object) in.readValue((Object.class.getClassLoader())));
        this.isFixedUnit = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.fixedUnit = ((Object) in.readValue((Object.class.getClassLoader())));
        this.maxRequestUnit = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.minRequestUnit = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.useMaxOccurence = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.occurenceIntervalTypeID = ((Object) in.readValue((Object.class.getClassLoader())));
        this.maxOccurenceInterval = ((Object) in.readValue((Object.class.getClassLoader())));
        this.occurenceQuantity = ((Object) in.readValue((Object.class.getClassLoader())));
        this.businessGroupID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.createdBy = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.createdDate = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedBy = ((Object) in.readValue((Object.class.getClassLoader())));
        this.updatedDate = ((Object) in.readValue((Object.class.getClassLoader())));
        this.delDate = ((Object) in.readValue((Object.class.getClassLoader())));
        this.occurenceIntervalType = ((Object) in.readValue((Object.class.getClassLoader())));
        in.readList(this.leaveRequestTransactions, (Object.class.getClassLoader()));
        in.readList(this.leaveRequestPositions, (Object.class.getClassLoader()));
        in.readList(this.leaveRequestOrgUnits, (Object.class.getClassLoader()));
        in.readList(this.leaveRequestLocations, (Object.class.getClassLoader()));
        in.readList(this.leaveRequestLevels, (Object.class.getClassLoader()));
        in.readList(this.leaveRequestGrades, (Object.class.getClassLoader()));
        in.readList(this.jointLeaveTransactions, (Object.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ReturnValue() {
    }

    /**
     * 
     * @param occurenceIntervalType
     * @param leaveRequestTransactions
     * @param jointLeaveTransactions
     * @param leaveRequestLevels
     * @param fixedUnit
     * @param delDate
     * @param leaveRequestPositions
     * @param updatedBy
     * @param leaveRequestOrgUnits
     * @param maxOccurenceInterval
     * @param leaveRequestGrades
     * @param isPaidLeave
     * @param isHalfDay
     * @param description
     * @param occurenceQuantity
     * @param name
     * @param occurenceIntervalTypeID
     * @param createdDate
     * @param isFixedUnit
     * @param minBandOfService
     * @param businessGroupID
     * @param minRequestUnit
     * @param leaveRequestLocations
     * @param updatedDate
     * @param code
     * @param iD
     * @param createdBy
     * @param maxRequestUnit
     * @param isReduceBalance
     * @param minRequestInterval
     * @param useMaxOccurence
     */
    public ReturnValue(Integer iD, String code, String name, Object description, Boolean isReduceBalance, Boolean isHalfDay, Boolean isPaidLeave, Integer minBandOfService, Object minRequestInterval, Boolean isFixedUnit, Object fixedUnit, Integer maxRequestUnit, Integer minRequestUnit, Boolean useMaxOccurence, Object occurenceIntervalTypeID, Object maxOccurenceInterval, Object occurenceQuantity, Integer businessGroupID, Integer createdBy, String createdDate, Object updatedBy, Object updatedDate, Object delDate, Object occurenceIntervalType, List<Object> leaveRequestTransactions, List<Object> leaveRequestPositions, List<Object> leaveRequestOrgUnits, List<Object> leaveRequestLocations, List<Object> leaveRequestLevels, List<Object> leaveRequestGrades, List<Object> jointLeaveTransactions) {
        super();
        this.iD = iD;
        this.code = code;
        this.name = name;
        this.description = description;
        this.isReduceBalance = isReduceBalance;
        this.isHalfDay = isHalfDay;
        this.isPaidLeave = isPaidLeave;
        this.minBandOfService = minBandOfService;
        this.minRequestInterval = minRequestInterval;
        this.isFixedUnit = isFixedUnit;
        this.fixedUnit = fixedUnit;
        this.maxRequestUnit = maxRequestUnit;
        this.minRequestUnit = minRequestUnit;
        this.useMaxOccurence = useMaxOccurence;
        this.occurenceIntervalTypeID = occurenceIntervalTypeID;
        this.maxOccurenceInterval = maxOccurenceInterval;
        this.occurenceQuantity = occurenceQuantity;
        this.businessGroupID = businessGroupID;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
        this.delDate = delDate;
        this.occurenceIntervalType = occurenceIntervalType;
        this.leaveRequestTransactions = leaveRequestTransactions;
        this.leaveRequestPositions = leaveRequestPositions;
        this.leaveRequestOrgUnits = leaveRequestOrgUnits;
        this.leaveRequestLocations = leaveRequestLocations;
        this.leaveRequestLevels = leaveRequestLevels;
        this.leaveRequestGrades = leaveRequestGrades;
        this.jointLeaveTransactions = jointLeaveTransactions;
    }

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Boolean getIsReduceBalance() {
        return isReduceBalance;
    }

    public void setIsReduceBalance(Boolean isReduceBalance) {
        this.isReduceBalance = isReduceBalance;
    }

    public Boolean getIsHalfDay() {
        return isHalfDay;
    }

    public void setIsHalfDay(Boolean isHalfDay) {
        this.isHalfDay = isHalfDay;
    }

    public Boolean getIsPaidLeave() {
        return isPaidLeave;
    }

    public void setIsPaidLeave(Boolean isPaidLeave) {
        this.isPaidLeave = isPaidLeave;
    }

    public Integer getMinBandOfService() {
        return minBandOfService;
    }

    public void setMinBandOfService(Integer minBandOfService) {
        this.minBandOfService = minBandOfService;
    }

    public Object getMinRequestInterval() {
        return minRequestInterval;
    }

    public void setMinRequestInterval(Object minRequestInterval) {
        this.minRequestInterval = minRequestInterval;
    }

    public Boolean getIsFixedUnit() {
        return isFixedUnit;
    }

    public void setIsFixedUnit(Boolean isFixedUnit) {
        this.isFixedUnit = isFixedUnit;
    }

    public Object getFixedUnit() {
        return fixedUnit;
    }

    public void setFixedUnit(Object fixedUnit) {
        this.fixedUnit = fixedUnit;
    }

    public Integer getMaxRequestUnit() {
        return maxRequestUnit;
    }

    public void setMaxRequestUnit(Integer maxRequestUnit) {
        this.maxRequestUnit = maxRequestUnit;
    }

    public Integer getMinRequestUnit() {
        return minRequestUnit;
    }

    public void setMinRequestUnit(Integer minRequestUnit) {
        this.minRequestUnit = minRequestUnit;
    }

    public Boolean getUseMaxOccurence() {
        return useMaxOccurence;
    }

    public void setUseMaxOccurence(Boolean useMaxOccurence) {
        this.useMaxOccurence = useMaxOccurence;
    }

    public Object getOccurenceIntervalTypeID() {
        return occurenceIntervalTypeID;
    }

    public void setOccurenceIntervalTypeID(Object occurenceIntervalTypeID) {
        this.occurenceIntervalTypeID = occurenceIntervalTypeID;
    }

    public Object getMaxOccurenceInterval() {
        return maxOccurenceInterval;
    }

    public void setMaxOccurenceInterval(Object maxOccurenceInterval) {
        this.maxOccurenceInterval = maxOccurenceInterval;
    }

    public Object getOccurenceQuantity() {
        return occurenceQuantity;
    }

    public void setOccurenceQuantity(Object occurenceQuantity) {
        this.occurenceQuantity = occurenceQuantity;
    }

    public Integer getBusinessGroupID() {
        return businessGroupID;
    }

    public void setBusinessGroupID(Integer businessGroupID) {
        this.businessGroupID = businessGroupID;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Object getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Object updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Object getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Object updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Object getDelDate() {
        return delDate;
    }

    public void setDelDate(Object delDate) {
        this.delDate = delDate;
    }

    public Object getOccurenceIntervalType() {
        return occurenceIntervalType;
    }

    public void setOccurenceIntervalType(Object occurenceIntervalType) {
        this.occurenceIntervalType = occurenceIntervalType;
    }

    public List<Object> getLeaveRequestTransactions() {
        return leaveRequestTransactions;
    }

    public void setLeaveRequestTransactions(List<Object> leaveRequestTransactions) {
        this.leaveRequestTransactions = leaveRequestTransactions;
    }

    public List<Object> getLeaveRequestPositions() {
        return leaveRequestPositions;
    }

    public void setLeaveRequestPositions(List<Object> leaveRequestPositions) {
        this.leaveRequestPositions = leaveRequestPositions;
    }

    public List<Object> getLeaveRequestOrgUnits() {
        return leaveRequestOrgUnits;
    }

    public void setLeaveRequestOrgUnits(List<Object> leaveRequestOrgUnits) {
        this.leaveRequestOrgUnits = leaveRequestOrgUnits;
    }

    public List<Object> getLeaveRequestLocations() {
        return leaveRequestLocations;
    }

    public void setLeaveRequestLocations(List<Object> leaveRequestLocations) {
        this.leaveRequestLocations = leaveRequestLocations;
    }

    public List<Object> getLeaveRequestLevels() {
        return leaveRequestLevels;
    }

    public void setLeaveRequestLevels(List<Object> leaveRequestLevels) {
        this.leaveRequestLevels = leaveRequestLevels;
    }

    public List<Object> getLeaveRequestGrades() {
        return leaveRequestGrades;
    }

    public void setLeaveRequestGrades(List<Object> leaveRequestGrades) {
        this.leaveRequestGrades = leaveRequestGrades;
    }

    public List<Object> getJointLeaveTransactions() {
        return jointLeaveTransactions;
    }

    public void setJointLeaveTransactions(List<Object> jointLeaveTransactions) {
        this.jointLeaveTransactions = jointLeaveTransactions;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(iD);
        dest.writeValue(code);
        dest.writeValue(name);
        dest.writeValue(description);
        dest.writeValue(isReduceBalance);
        dest.writeValue(isHalfDay);
        dest.writeValue(isPaidLeave);
        dest.writeValue(minBandOfService);
        dest.writeValue(minRequestInterval);
        dest.writeValue(isFixedUnit);
        dest.writeValue(fixedUnit);
        dest.writeValue(maxRequestUnit);
        dest.writeValue(minRequestUnit);
        dest.writeValue(useMaxOccurence);
        dest.writeValue(occurenceIntervalTypeID);
        dest.writeValue(maxOccurenceInterval);
        dest.writeValue(occurenceQuantity);
        dest.writeValue(businessGroupID);
        dest.writeValue(createdBy);
        dest.writeValue(createdDate);
        dest.writeValue(updatedBy);
        dest.writeValue(updatedDate);
        dest.writeValue(delDate);
        dest.writeValue(occurenceIntervalType);
        dest.writeList(leaveRequestTransactions);
        dest.writeList(leaveRequestPositions);
        dest.writeList(leaveRequestOrgUnits);
        dest.writeList(leaveRequestLocations);
        dest.writeList(leaveRequestLevels);
        dest.writeList(leaveRequestGrades);
        dest.writeList(jointLeaveTransactions);
    }

    public int describeContents() {
        return  0;
    }

    @Override
    public String toString() {
        return name;
    }

}
