
package id.co.indocyber.android.starbridges.model.ListOvertime;

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
    private String iD;
    @SerializedName("OvertimeDate")
    @Expose
    private String overtimeDate;
    @SerializedName("RequestedDuration")
    @Expose
    private String requestedDuration;
    @SerializedName("EmployeeNIK")
    @Expose
    private Object employeeNIK;
    @SerializedName("EmployeeName")
    @Expose
    private Object employeeName;
    @SerializedName("DecisionNumber")
    @Expose
    private String decisionNumber;
    @SerializedName("RequestDate")
    @Expose
    private String requestDate;
    @SerializedName("ApprovedDate")
    @Expose
    private String approvedDate;
    @SerializedName("AttachmentURL")
    @Expose
    private Object attachmentURL;
    @SerializedName("OvertimeStart")
    @Expose
    private String overtimeStart;
    @SerializedName("OvertimeEnd")
    @Expose
    private String overtimeEnd;
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
    private final static long serialVersionUID = -5716518027938282667L;

    protected ReturnValue(Parcel in) {
        this.iD = ((String) in.readValue((String.class.getClassLoader())));
        this.overtimeDate = ((String) in.readValue((String.class.getClassLoader())));
        this.requestedDuration = ((String) in.readValue((String.class.getClassLoader())));
        this.employeeNIK = ((Object) in.readValue((Object.class.getClassLoader())));
        this.employeeName = ((Object) in.readValue((Object.class.getClassLoader())));
        this.decisionNumber = ((String) in.readValue((Object.class.getClassLoader())));
        this.requestDate = ((String) in.readValue((String.class.getClassLoader())));
        this.approvedDate = ((String) in.readValue((String.class.getClassLoader())));
        this.attachmentURL = ((Object) in.readValue((Object.class.getClassLoader())));
        this.overtimeStart = ((String) in.readValue((String.class.getClassLoader())));
        this.overtimeEnd = ((String) in.readValue((String.class.getClassLoader())));
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
     * @param accessibilityAttribute
     * @param attachmentURL
     * @param requestedDuration
     * @param fullAccess
     * @param overtimeEnd
     * @param approvedDate
     * @param iD
     * @param exclusionFields
     * @param overtimeDate
     * @param employeeName
     * @param overtimeStart
     * @param employeeNIK
     * @param decisionNumber
     * @param requestDate
     */
    public ReturnValue(String iD, String overtimeDate, String requestedDuration, Object employeeNIK, Object employeeName, String decisionNumber, String requestDate, String approvedDate, Object attachmentURL, String overtimeStart, String overtimeEnd, Boolean fullAccess, List<Object> exclusionFields, String accessibilityAttribute) {
        super();
        this.iD = iD;
        this.overtimeDate = overtimeDate;
        this.requestedDuration = requestedDuration;
        this.employeeNIK = employeeNIK;
        this.employeeName = employeeName;
        this.decisionNumber = decisionNumber;
        this.requestDate = requestDate;
        this.approvedDate = approvedDate;
        this.attachmentURL = attachmentURL;
        this.overtimeStart = overtimeStart;
        this.overtimeEnd = overtimeEnd;
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

    public String getOvertimeDate() {
        return overtimeDate;
    }

    public void setOvertimeDate(String overtimeDate) {
        this.overtimeDate = overtimeDate;
    }

    public String getRequestedDuration() {
        return requestedDuration;
    }

    public void setRequestedDuration(String requestedDuration) {
        this.requestedDuration = requestedDuration;
    }

    public Object getEmployeeNIK() {
        return employeeNIK;
    }

    public void setEmployeeNIK(Object employeeNIK) {
        this.employeeNIK = employeeNIK;
    }

    public Object getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(Object employeeName) {
        this.employeeName = employeeName;
    }

    public String getDecisionNumber() {
        return decisionNumber;
    }

    public void setDecisionNumber(String decisionNumber) {
        this.decisionNumber = decisionNumber;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(String approvedDate) {
        this.approvedDate = approvedDate;
    }

    public Object getAttachmentURL() {
        return attachmentURL;
    }

    public void setAttachmentURL(Object attachmentURL) {
        this.attachmentURL = attachmentURL;
    }

    public String getOvertimeStart() {
        return overtimeStart;
    }

    public void setOvertimeStart(String overtimeStart) {
        this.overtimeStart = overtimeStart;
    }

    public String getOvertimeEnd() {
        return overtimeEnd;
    }

    public void setOvertimeEnd(String overtimeEnd) {
        this.overtimeEnd = overtimeEnd;
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
        dest.writeValue(overtimeDate);
        dest.writeValue(requestedDuration);
        dest.writeValue(employeeNIK);
        dest.writeValue(employeeName);
        dest.writeValue(decisionNumber);
        dest.writeValue(requestDate);
        dest.writeValue(approvedDate);
        dest.writeValue(attachmentURL);
        dest.writeValue(overtimeStart);
        dest.writeValue(overtimeEnd);
        dest.writeValue(fullAccess);
        dest.writeList(exclusionFields);
        dest.writeValue(accessibilityAttribute);
    }

    public int describeContents() {
        return  0;
    }

}
