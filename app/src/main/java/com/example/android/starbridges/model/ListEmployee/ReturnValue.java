
package com.example.android.starbridges.model.ListEmployee;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReturnValue implements Serializable, Parcelable
{

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("NIK")
    @Expose
    private String nIK;
    @SerializedName("Position")
    @Expose
    private String position;
    @SerializedName("EmploymentStatus")
    @Expose
    private Integer employmentStatus;

    @Override
    public String toString() {
        return fullName;
    }

    @SerializedName("JobTitle")
    @Expose


    private Object jobTitle;
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
    private final static long serialVersionUID = -4231364284151775314L;

    protected ReturnValue(Parcel in) {
        this.iD = ((String) in.readValue((String.class.getClassLoader())));
        this.fullName = ((String) in.readValue((String.class.getClassLoader())));
        this.nIK = ((String) in.readValue((String.class.getClassLoader())));
        this.position = ((String) in.readValue((String.class.getClassLoader())));
        this.employmentStatus = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.jobTitle = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ReturnValue() {
    }

    /**
     * 
     * @param position
     * @param nIK
     * @param fullName
     * @param iD
     * @param jobTitle
     * @param employmentStatus
     */
    public ReturnValue(String iD, String fullName, String nIK, String position, Integer employmentStatus, Object jobTitle) {
        super();
        this.iD = iD;
        this.fullName = fullName;
        this.nIK = nIK;
        this.position = position;
        this.employmentStatus = employmentStatus;
        this.jobTitle = jobTitle;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNIK() {
        return nIK;
    }

    public void setNIK(String nIK) {
        this.nIK = nIK;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(Integer employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public Object getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(Object jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(iD);
        dest.writeValue(fullName);
        dest.writeValue(nIK);
        dest.writeValue(position);
        dest.writeValue(employmentStatus);
        dest.writeValue(jobTitle);
    }

    public int describeContents() {
        return  0;
    }

}
