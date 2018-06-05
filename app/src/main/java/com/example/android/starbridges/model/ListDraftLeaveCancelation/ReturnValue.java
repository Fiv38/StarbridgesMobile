
package com.example.android.starbridges.model.ListDraftLeaveCancelation;

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
    private Object decisionNumber;
    @SerializedName("RequestDate")
    @Expose
    private String requestDate;
    @SerializedName("RequestType")
    @Expose
    private String requestType;
    @SerializedName("CancelFrom")
    @Expose
    private String cancelFrom;
    @SerializedName("CancelTo")
    @Expose
    private String cancelTo;
    @SerializedName("Notes")
    @Expose
    private String notes;
    @SerializedName("RequestFrom")
    @Expose
    private String requestFrom;
    @SerializedName("RequestTo")
    @Expose
    private String requestTo;
    @SerializedName("AdditionalBalance")
    @Expose
    private int additionalBalance;
    @SerializedName("FullAccess")
    @Expose
    private boolean fullAccess;
    @SerializedName("ExclusionFields")
    @Expose
    private List<Object> exclusionFields = null;
    @SerializedName("AccessibilityAttribute")
    @Expose
    private String accessibilityAttribute;

    private boolean isSelected;

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
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
    private final static long serialVersionUID = 2963860832212208587L;

    protected ReturnValue(Parcel in) {
        this.iD = ((String) in.readValue((String.class.getClassLoader())));
        this.decisionNumber = ((Object) in.readValue((Object.class.getClassLoader())));
        this.requestDate = ((String) in.readValue((String.class.getClassLoader())));
        this.requestType = ((String) in.readValue((String.class.getClassLoader())));
        this.cancelFrom = ((String) in.readValue((String.class.getClassLoader())));
        this.cancelTo = ((String) in.readValue((String.class.getClassLoader())));
        this.notes = ((String) in.readValue((String.class.getClassLoader())));
        this.requestFrom = ((String) in.readValue((String.class.getClassLoader())));
        this.requestTo = ((String) in.readValue((String.class.getClassLoader())));
        this.additionalBalance = ((int) in.readValue((int.class.getClassLoader())));
        this.fullAccess = ((boolean) in.readValue((boolean.class.getClassLoader())));
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
     * @param cancelTo
     * @param accessibilityAttribute
     * @param fullAccess
     * @param requestType
     * @param iD
     * @param exclusionFields
     * @param requestTo
     * @param decisionNumber
     * @param requestFrom
     * @param cancelFrom
     * @param requestDate
     * @param notes
     * @param additionalBalance
     */
    public ReturnValue(String iD, Object decisionNumber, String requestDate, String requestType, String cancelFrom, String cancelTo, String notes, String requestFrom, String requestTo, int additionalBalance, boolean fullAccess, List<Object> exclusionFields, String accessibilityAttribute) {
        super();
        this.iD = iD;
        this.decisionNumber = decisionNumber;
        this.requestDate = requestDate;
        this.requestType = requestType;
        this.cancelFrom = cancelFrom;
        this.cancelTo = cancelTo;
        this.notes = notes;
        this.requestFrom = requestFrom;
        this.requestTo = requestTo;
        this.additionalBalance = additionalBalance;
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

    public Object getDecisionNumber() {
        return decisionNumber;
    }

    public void setDecisionNumber(Object decisionNumber) {
        this.decisionNumber = decisionNumber;
    }

    public ReturnValue withDecisionNumber(Object decisionNumber) {
        this.decisionNumber = decisionNumber;
        return this;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public ReturnValue withRequestDate(String requestDate) {
        this.requestDate = requestDate;
        return this;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public ReturnValue withRequestType(String requestType) {
        this.requestType = requestType;
        return this;
    }

    public String getCancelFrom() {
        return cancelFrom;
    }

    public void setCancelFrom(String cancelFrom) {
        this.cancelFrom = cancelFrom;
    }

    public ReturnValue withCancelFrom(String cancelFrom) {
        this.cancelFrom = cancelFrom;
        return this;
    }

    public String getCancelTo() {
        return cancelTo;
    }

    public void setCancelTo(String cancelTo) {
        this.cancelTo = cancelTo;
    }

    public ReturnValue withCancelTo(String cancelTo) {
        this.cancelTo = cancelTo;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public ReturnValue withNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public String getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(String requestFrom) {
        this.requestFrom = requestFrom;
    }

    public ReturnValue withRequestFrom(String requestFrom) {
        this.requestFrom = requestFrom;
        return this;
    }

    public String getRequestTo() {
        return requestTo;
    }

    public void setRequestTo(String requestTo) {
        this.requestTo = requestTo;
    }

    public ReturnValue withRequestTo(String requestTo) {
        this.requestTo = requestTo;
        return this;
    }

    public int getAdditionalBalance() {
        return additionalBalance;
    }

    public void setAdditionalBalance(int additionalBalance) {
        this.additionalBalance = additionalBalance;
    }

    public ReturnValue withAdditionalBalance(int additionalBalance) {
        this.additionalBalance = additionalBalance;
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
        dest.writeValue(decisionNumber);
        dest.writeValue(requestDate);
        dest.writeValue(requestType);
        dest.writeValue(cancelFrom);
        dest.writeValue(cancelTo);
        dest.writeValue(notes);
        dest.writeValue(requestFrom);
        dest.writeValue(requestTo);
        dest.writeValue(additionalBalance);
        dest.writeValue(fullAccess);
        dest.writeList(exclusionFields);
        dest.writeValue(accessibilityAttribute);
    }

    public int describeContents() {
        return  0;
    }


}
