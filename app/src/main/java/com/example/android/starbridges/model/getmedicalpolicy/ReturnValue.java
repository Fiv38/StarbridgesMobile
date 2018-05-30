
package com.example.android.starbridges.model.getmedicalpolicy;

import java.io.Serializable;
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
    @SerializedName("PolicyName")
    @Expose
    private String policyName;
    @SerializedName("PolicyTypeID")
    @Expose
    private String policyTypeID;
    @SerializedName("PolicyType")
    @Expose
    private String policyType;
    @SerializedName("Service")
    @Expose
    private String service;
    @SerializedName("Class")
    @Expose
    private String _class;
    @SerializedName("Illness")
    @Expose
    private String illness;
    @SerializedName("MaritalStatus")
    @Expose
    private String maritalStatus;
    @SerializedName("Dependency")
    @Expose
    private Integer dependency;
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
    private final static long serialVersionUID = -3221770202144329843L;

    protected ReturnValue(Parcel in) {
        this.iD = ((String) in.readValue((String.class.getClassLoader())));
        this.policyName = ((String) in.readValue((String.class.getClassLoader())));
        this.policyTypeID = ((String) in.readValue((String.class.getClassLoader())));
        this.policyType = ((String) in.readValue((String.class.getClassLoader())));
        this.service = ((String) in.readValue((String.class.getClassLoader())));
        this._class = ((String) in.readValue((String.class.getClassLoader())));
        this.illness = ((String) in.readValue((String.class.getClassLoader())));
        this.maritalStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.dependency = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ReturnValue() {
    }

    /**
     * 
     * @param dependency
     * @param policyTypeID
     * @param policyName
     * @param _class
     * @param illness
     * @param service
     * @param maritalStatus
     * @param iD
     * @param policyType
     */
    public ReturnValue(String iD, String policyName, String policyTypeID, String policyType, String service, String _class, String illness, String maritalStatus, Integer dependency) {
        super();
        this.iD = iD;
        this.policyName = policyName;
        this.policyTypeID = policyTypeID;
        this.policyType = policyType;
        this.service = service;
        this._class = _class;
        this.illness = illness;
        this.maritalStatus = maritalStatus;
        this.dependency = dependency;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getPolicyTypeID() {
        return policyTypeID;
    }

    public void setPolicyTypeID(String policyTypeID) {
        this.policyTypeID = policyTypeID;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getDependency() {
        return dependency;
    }

    public void setDependency(Integer dependency) {
        this.dependency = dependency;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(iD);
        dest.writeValue(policyName);
        dest.writeValue(policyTypeID);
        dest.writeValue(policyType);
        dest.writeValue(service);
        dest.writeValue(_class);
        dest.writeValue(illness);
        dest.writeValue(maritalStatus);
        dest.writeValue(dependency);
    }

    public int describeContents() {
        return  0;
    }

    @Override
    public String toString() {
        return policyName;
    }

}
