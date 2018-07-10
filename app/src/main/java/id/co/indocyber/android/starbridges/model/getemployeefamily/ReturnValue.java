
package id.co.indocyber.android.starbridges.model.getemployeefamily;

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
    @SerializedName("AwaitingUpdate")
    @Expose
    private Object awaitingUpdate;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Relation")
    @Expose
    private String relation;
    @SerializedName("RelationStart")
    @Expose
    private String relationStart;
    @SerializedName("RelationEnd")
    @Expose
    private Object relationEnd;
    @SerializedName("MedicalBenefit")
    @Expose
    private Object medicalBenefit;
    @SerializedName("IsDependent")
    @Expose
    private String isDependent;
    @SerializedName("IdentityTypeID")
    @Expose
    private String identityTypeID;
    @SerializedName("IdentityNumber")
    @Expose
    private String identityNumber;
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
    private final static long serialVersionUID = 827885769477614034L;

    protected ReturnValue(Parcel in) {
        this.iD = ((String) in.readValue((String.class.getClassLoader())));
        this.awaitingUpdate = ((Object) in.readValue((Object.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.relation = ((String) in.readValue((String.class.getClassLoader())));
        this.relationStart = ((String) in.readValue((String.class.getClassLoader())));
        this.relationEnd = ((Object) in.readValue((Object.class.getClassLoader())));
        this.medicalBenefit = ((Object) in.readValue((Object.class.getClassLoader())));
        this.isDependent = ((String) in.readValue((String.class.getClassLoader())));
        this.identityTypeID = ((String) in.readValue((String.class.getClassLoader())));
        this.identityNumber = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ReturnValue() {
    }

    /**
     * 
     * @param relationEnd
     * @param relationStart
     * @param isDependent
     * @param medicalBenefit
     * @param name
     * @param relation
     * @param identityTypeID
     * @param identityNumber
     * @param iD
     * @param awaitingUpdate
     */
    public ReturnValue(String iD, Object awaitingUpdate, String name, String relation, String relationStart, Object relationEnd, Object medicalBenefit, String isDependent, String identityTypeID, String identityNumber) {
        super();
        this.iD = iD;
        this.awaitingUpdate = awaitingUpdate;
        this.name = name;
        this.relation = relation;
        this.relationStart = relationStart;
        this.relationEnd = relationEnd;
        this.medicalBenefit = medicalBenefit;
        this.isDependent = isDependent;
        this.identityTypeID = identityTypeID;
        this.identityNumber = identityNumber;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public Object getAwaitingUpdate() {
        return awaitingUpdate;
    }

    public void setAwaitingUpdate(Object awaitingUpdate) {
        this.awaitingUpdate = awaitingUpdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getRelationStart() {
        return relationStart;
    }

    public void setRelationStart(String relationStart) {
        this.relationStart = relationStart;
    }

    public Object getRelationEnd() {
        return relationEnd;
    }

    public void setRelationEnd(Object relationEnd) {
        this.relationEnd = relationEnd;
    }

    public Object getMedicalBenefit() {
        return medicalBenefit;
    }

    public void setMedicalBenefit(Object medicalBenefit) {
        this.medicalBenefit = medicalBenefit;
    }

    public String getIsDependent() {
        return isDependent;
    }

    public void setIsDependent(String isDependent) {
        this.isDependent = isDependent;
    }

    public String getIdentityTypeID() {
        return identityTypeID;
    }

    public void setIdentityTypeID(String identityTypeID) {
        this.identityTypeID = identityTypeID;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(iD);
        dest.writeValue(awaitingUpdate);
        dest.writeValue(name);
        dest.writeValue(relation);
        dest.writeValue(relationStart);
        dest.writeValue(relationEnd);
        dest.writeValue(medicalBenefit);
        dest.writeValue(isDependent);
        dest.writeValue(identityTypeID);
        dest.writeValue(identityNumber);
    }

    public int describeContents() {
        return  0;
    }

    @Override
    public String toString() {
        return name;
    }

}
