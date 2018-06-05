
package com.example.android.starbridges.model.SubmitOvertime;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReturnValue implements Serializable, Parcelable
{

    @SerializedName("ShiftName")
    @Expose
    private String shiftName;
    @SerializedName("OvertimeStart")
    @Expose
    private String overtimeStart;
    @SerializedName("OvertimeEnd")
    @Expose
    private String overtimeEnd;
    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("EmployeeID")
    @Expose
    private String employeeID;
    @SerializedName("RequestDate")
    @Expose
    private String requestDate;
    @SerializedName("OvertimeDate")
    @Expose
    private String overtimeDate;
    @SerializedName("From")
    @Expose
    private String from;
    @SerializedName("To")
    @Expose
    private String to;
    @SerializedName("Notes")
    @Expose
    private String notes;
    @SerializedName("AttachmentID")
    @Expose
    private int attachmentID;
    @SerializedName("AttachmentFile")
    @Expose
    private String attachmentFile;
    @SerializedName("TransactionStatusID")
    @Expose
    private int transactionStatusID;
    @SerializedName("SubmitType")
    @Expose
    private String submitType;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("MaxOvertimePerDay")
    @Expose
    private String maxOvertimePerDay;
    @SerializedName("MaxOvertimePerWeek")
    @Expose
    private String maxOvertimePerWeek;
    @SerializedName("MaxOvertimePerMonth")
    @Expose
    private String maxOvertimePerMonth;
    @SerializedName("TotalRequestCurrentWeek")
    @Expose
    private String totalRequestCurrentWeek;
    @SerializedName("TotalRequestCurrentMonth")
    @Expose
    private String totalRequestCurrentMonth;
    @SerializedName("TransactionStatusSaveOrSubmit")
    @Expose
    private String transactionStatusSaveOrSubmit;
    @SerializedName("FullAccess")
    @Expose
    private boolean fullAccess;
    @SerializedName("ExclusionFields")
    @Expose
    private List<String> exclusionFields = null;
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
    private final static long serialVersionUID = 3261090723750998588L;

    protected ReturnValue(Parcel in) {
        this.shiftName = ((String) in.readValue((String.class.getClassLoader())));
        this.overtimeStart = ((String) in.readValue((String.class.getClassLoader())));
        this.overtimeEnd = ((String) in.readValue((String.class.getClassLoader())));
        this.iD = ((String) in.readValue((String.class.getClassLoader())));
        this.employeeID = ((String) in.readValue((String.class.getClassLoader())));
        this.requestDate = ((String) in.readValue((String.class.getClassLoader())));
        this.overtimeDate = ((String) in.readValue((String.class.getClassLoader())));
        this.from = ((String) in.readValue((String.class.getClassLoader())));
        this.to = ((String) in.readValue((String.class.getClassLoader())));
        this.notes = ((String) in.readValue((String.class.getClassLoader())));
        this.attachmentID = ((int) in.readValue((int.class.getClassLoader())));
        this.attachmentFile = ((String) in.readValue((String.class.getClassLoader())));
        this.transactionStatusID = ((int) in.readValue((int.class.getClassLoader())));
        this.submitType = ((String) in.readValue((String.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.maxOvertimePerDay = ((String) in.readValue((String.class.getClassLoader())));
        this.maxOvertimePerWeek = ((String) in.readValue((String.class.getClassLoader())));
        this.maxOvertimePerMonth = ((String) in.readValue((String.class.getClassLoader())));
        this.totalRequestCurrentWeek = ((String) in.readValue((String.class.getClassLoader())));
        this.totalRequestCurrentMonth = ((String) in.readValue((String.class.getClassLoader())));
        this.transactionStatusSaveOrSubmit = ((String) in.readValue((String.class.getClassLoader())));
        this.fullAccess = ((boolean) in.readValue((boolean.class.getClassLoader())));
        in.readList(this.exclusionFields, (String.class.getClassLoader()));
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
     * @param to
     * @param accessibilityAttribute
     * @param employeeID
     * @param shiftName
     * @param fullAccess
     * @param maxOvertimePerWeek
     * @param attachmentFile
     * @param overtimeEnd
     * @param submitType
     * @param from
     * @param totalRequestCurrentMonth
     * @param totalRequestCurrentWeek
     * @param iD
     * @param exclusionFields
     * @param overtimeDate
     * @param message
     * @param overtimeStart
     * @param maxOvertimePerDay
     * @param transactionStatusSaveOrSubmit
     * @param maxOvertimePerMonth
     * @param requestDate
     * @param attachmentID
     * @param notes
     * @param transactionStatusID
     */
    public ReturnValue(String shiftName, String overtimeStart, String overtimeEnd, String iD, String employeeID, String requestDate, String overtimeDate, String from, String to, String notes, int attachmentID, String attachmentFile, int transactionStatusID, String submitType, String message, String maxOvertimePerDay, String maxOvertimePerWeek, String maxOvertimePerMonth, String totalRequestCurrentWeek, String totalRequestCurrentMonth, String transactionStatusSaveOrSubmit, boolean fullAccess, List<String> exclusionFields, String accessibilityAttribute) {
        super();
        this.shiftName = shiftName;
        this.overtimeStart = overtimeStart;
        this.overtimeEnd = overtimeEnd;
        this.iD = iD;
        this.employeeID = employeeID;
        this.requestDate = requestDate;
        this.overtimeDate = overtimeDate;
        this.from = from;
        this.to = to;
        this.notes = notes;
        this.attachmentID = attachmentID;
        this.attachmentFile = attachmentFile;
        this.transactionStatusID = transactionStatusID;
        this.submitType = submitType;
        this.message = message;
        this.maxOvertimePerDay = maxOvertimePerDay;
        this.maxOvertimePerWeek = maxOvertimePerWeek;
        this.maxOvertimePerMonth = maxOvertimePerMonth;
        this.totalRequestCurrentWeek = totalRequestCurrentWeek;
        this.totalRequestCurrentMonth = totalRequestCurrentMonth;
        this.transactionStatusSaveOrSubmit = transactionStatusSaveOrSubmit;
        this.fullAccess = fullAccess;
        this.exclusionFields = exclusionFields;
        this.accessibilityAttribute = accessibilityAttribute;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public ReturnValue withShiftName(String shiftName) {
        this.shiftName = shiftName;
        return this;
    }

    public String getOvertimeStart() {
        return overtimeStart;
    }

    public void setOvertimeStart(String overtimeStart) {
        this.overtimeStart = overtimeStart;
    }

    public ReturnValue withOvertimeStart(String overtimeStart) {
        this.overtimeStart = overtimeStart;
        return this;
    }

    public String getOvertimeEnd() {
        return overtimeEnd;
    }

    public void setOvertimeEnd(String overtimeEnd) {
        this.overtimeEnd = overtimeEnd;
    }

    public ReturnValue withOvertimeEnd(String overtimeEnd) {
        this.overtimeEnd = overtimeEnd;
        return this;
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

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public ReturnValue withEmployeeID(String employeeID) {
        this.employeeID = employeeID;
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

    public String getOvertimeDate() {
        return overtimeDate;
    }

    public void setOvertimeDate(String overtimeDate) {
        this.overtimeDate = overtimeDate;
    }

    public ReturnValue withOvertimeDate(String overtimeDate) {
        this.overtimeDate = overtimeDate;
        return this;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public ReturnValue withFrom(String from) {
        this.from = from;
        return this;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public ReturnValue withTo(String to) {
        this.to = to;
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

    public int getAttachmentID() {
        return attachmentID;
    }

    public void setAttachmentID(int attachmentID) {
        this.attachmentID = attachmentID;
    }

    public ReturnValue withAttachmentID(int attachmentID) {
        this.attachmentID = attachmentID;
        return this;
    }

    public String getAttachmentFile() {
        return attachmentFile;
    }

    public void setAttachmentFile(String attachmentFile) {
        this.attachmentFile = attachmentFile;
    }

    public ReturnValue withAttachmentFile(String attachmentFile) {
        this.attachmentFile = attachmentFile;
        return this;
    }

    public int getTransactionStatusID() {
        return transactionStatusID;
    }

    public void setTransactionStatusID(int transactionStatusID) {
        this.transactionStatusID = transactionStatusID;
    }

    public ReturnValue withTransactionStatusID(int transactionStatusID) {
        this.transactionStatusID = transactionStatusID;
        return this;
    }

    public String getSubmitType() {
        return submitType;
    }

    public void setSubmitType(String submitType) {
        this.submitType = submitType;
    }

    public ReturnValue withSubmitType(String submitType) {
        this.submitType = submitType;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ReturnValue withMessage(String message) {
        this.message = message;
        return this;
    }

    public String getMaxOvertimePerDay() {
        return maxOvertimePerDay;
    }

    public void setMaxOvertimePerDay(String maxOvertimePerDay) {
        this.maxOvertimePerDay = maxOvertimePerDay;
    }

    public ReturnValue withMaxOvertimePerDay(String maxOvertimePerDay) {
        this.maxOvertimePerDay = maxOvertimePerDay;
        return this;
    }

    public String getMaxOvertimePerWeek() {
        return maxOvertimePerWeek;
    }

    public void setMaxOvertimePerWeek(String maxOvertimePerWeek) {
        this.maxOvertimePerWeek = maxOvertimePerWeek;
    }

    public ReturnValue withMaxOvertimePerWeek(String maxOvertimePerWeek) {
        this.maxOvertimePerWeek = maxOvertimePerWeek;
        return this;
    }

    public String getMaxOvertimePerMonth() {
        return maxOvertimePerMonth;
    }

    public void setMaxOvertimePerMonth(String maxOvertimePerMonth) {
        this.maxOvertimePerMonth = maxOvertimePerMonth;
    }

    public ReturnValue withMaxOvertimePerMonth(String maxOvertimePerMonth) {
        this.maxOvertimePerMonth = maxOvertimePerMonth;
        return this;
    }

    public String getTotalRequestCurrentWeek() {
        return totalRequestCurrentWeek;
    }

    public void setTotalRequestCurrentWeek(String totalRequestCurrentWeek) {
        this.totalRequestCurrentWeek = totalRequestCurrentWeek;
    }

    public ReturnValue withTotalRequestCurrentWeek(String totalRequestCurrentWeek) {
        this.totalRequestCurrentWeek = totalRequestCurrentWeek;
        return this;
    }

    public String getTotalRequestCurrentMonth() {
        return totalRequestCurrentMonth;
    }

    public void setTotalRequestCurrentMonth(String totalRequestCurrentMonth) {
        this.totalRequestCurrentMonth = totalRequestCurrentMonth;
    }

    public ReturnValue withTotalRequestCurrentMonth(String totalRequestCurrentMonth) {
        this.totalRequestCurrentMonth = totalRequestCurrentMonth;
        return this;
    }

    public String getTransactionStatusSaveOrSubmit() {
        return transactionStatusSaveOrSubmit;
    }

    public void setTransactionStatusSaveOrSubmit(String transactionStatusSaveOrSubmit) {
        this.transactionStatusSaveOrSubmit = transactionStatusSaveOrSubmit;
    }

    public ReturnValue withTransactionStatusSaveOrSubmit(String transactionStatusSaveOrSubmit) {
        this.transactionStatusSaveOrSubmit = transactionStatusSaveOrSubmit;
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

    public List<String> getExclusionFields() {
        return exclusionFields;
    }

    public void setExclusionFields(List<String> exclusionFields) {
        this.exclusionFields = exclusionFields;
    }

    public ReturnValue withExclusionFields(List<String> exclusionFields) {
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
        dest.writeValue(shiftName);
        dest.writeValue(overtimeStart);
        dest.writeValue(overtimeEnd);
        dest.writeValue(iD);
        dest.writeValue(employeeID);
        dest.writeValue(requestDate);
        dest.writeValue(overtimeDate);
        dest.writeValue(from);
        dest.writeValue(to);
        dest.writeValue(notes);
        dest.writeValue(attachmentID);
        dest.writeValue(attachmentFile);
        dest.writeValue(transactionStatusID);
        dest.writeValue(submitType);
        dest.writeValue(message);
        dest.writeValue(maxOvertimePerDay);
        dest.writeValue(maxOvertimePerWeek);
        dest.writeValue(maxOvertimePerMonth);
        dest.writeValue(totalRequestCurrentWeek);
        dest.writeValue(totalRequestCurrentMonth);
        dest.writeValue(transactionStatusSaveOrSubmit);
        dest.writeValue(fullAccess);
        dest.writeList(exclusionFields);
        dest.writeValue(accessibilityAttribute);
    }

    public int describeContents() {
        return  0;
    }

}
