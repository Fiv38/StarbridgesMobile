
package id.co.indocyber.android.starbridges.model.CorrectionDetail;

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
    @SerializedName("EmployeeAttendanceLogUID")
    @Expose
    private String employeeAttendanceLogUID;
    @SerializedName("ActualLogIn")
    @Expose
    private String actualLogIn;
    @SerializedName("ActualBreakStart")
    @Expose
    private String actualBreakStart;
    @SerializedName("ActualBreakEnd")
    @Expose
    private String actualBreakEnd;
    @SerializedName("ActualLogOut")
    @Expose
    private String actualLogOut;
    @SerializedName("ActualOvertimeIn")
    @Expose
    private String actualOvertimeIn;
    @SerializedName("ActualOvertimeOut")
    @Expose
    private String actualOvertimeOut;
    @SerializedName("EmployeeID")
    @Expose
    private int employeeID;
    @SerializedName("RequestDate")
    @Expose
    private String requestDate;
    @SerializedName("TransactionStatusID")
    @Expose
    private int transactionStatusID;
    @SerializedName("DecisionNumber")
    @Expose
    private String decisionNumber;
    @SerializedName("LogDate")
    @Expose
    private String logDate;
    @SerializedName("Day")
    @Expose
    private String day;
    @SerializedName("DayType")
    @Expose
    private String dayType;
    @SerializedName("Roster")
    @Expose
    private String roster;
    @SerializedName("Shift")
    @Expose
    private String shift;
    @SerializedName("Notes")
    @Expose
    private String notes;
    @SerializedName("NightShift")
    @Expose
    private boolean nightShift;
    @SerializedName("ShiftLimit")
    @Expose
    private String shiftLimit;
    @SerializedName("LocationID")
    @Expose
    private String locationID;
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
    private final static long serialVersionUID = -1855903894669945823L;

    protected ReturnValue(Parcel in) {
        this.iD = ((String) in.readValue((String.class.getClassLoader())));
        this.employeeAttendanceLogUID = ((String) in.readValue((String.class.getClassLoader())));
        this.actualLogIn = ((String) in.readValue((String.class.getClassLoader())));
        this.actualBreakStart = ((String) in.readValue((String.class.getClassLoader())));
        this.actualBreakEnd = ((String) in.readValue((String.class.getClassLoader())));
        this.actualLogOut = ((String) in.readValue((String.class.getClassLoader())));
        this.actualOvertimeIn = ((String) in.readValue((String.class.getClassLoader())));
        this.actualOvertimeOut = ((String) in.readValue((String.class.getClassLoader())));
        this.employeeID = ((int) in.readValue((int.class.getClassLoader())));
        this.requestDate = ((String) in.readValue((String.class.getClassLoader())));
        this.transactionStatusID = ((int) in.readValue((int.class.getClassLoader())));
        this.decisionNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.logDate = ((String) in.readValue((String.class.getClassLoader())));
        this.day = ((String) in.readValue((String.class.getClassLoader())));
        this.dayType = ((String) in.readValue((String.class.getClassLoader())));
        this.roster = ((String) in.readValue((String.class.getClassLoader())));
        this.shift = ((String) in.readValue((String.class.getClassLoader())));
        this.notes = ((String) in.readValue((String.class.getClassLoader())));
        this.nightShift = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.shiftLimit = ((String) in.readValue((String.class.getClassLoader())));
        this.locationID = ((String) in.readValue((String.class.getClassLoader())));
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
     * @param accessibilityAttribute
     * @param nightShift
     * @param employeeID
     * @param actualOvertimeIn
     * @param fullAccess
     * @param actualLogIn
     * @param actualBreakEnd
     * @param roster
     * @param locationID
     * @param logDate
     * @param actualOvertimeOut
     * @param employeeAttendanceLogUID
     * @param iD
     * @param exclusionFields
     * @param decisionNumber
     * @param shiftLimit
     * @param actualLogOut
     * @param dayType
     * @param day
     * @param actualBreakStart
     * @param requestDate
     * @param notes
     * @param shift
     * @param transactionStatusID
     */
    public ReturnValue(String iD, String employeeAttendanceLogUID, String actualLogIn, String actualBreakStart, String actualBreakEnd, String actualLogOut, String actualOvertimeIn, String actualOvertimeOut, int employeeID, String requestDate, int transactionStatusID, String decisionNumber, String logDate, String day, String dayType, String roster, String shift, String notes, boolean nightShift, String shiftLimit, String locationID, boolean fullAccess, List<String> exclusionFields, String accessibilityAttribute) {
        super();
        this.iD = iD;
        this.employeeAttendanceLogUID = employeeAttendanceLogUID;
        this.actualLogIn = actualLogIn;
        this.actualBreakStart = actualBreakStart;
        this.actualBreakEnd = actualBreakEnd;
        this.actualLogOut = actualLogOut;
        this.actualOvertimeIn = actualOvertimeIn;
        this.actualOvertimeOut = actualOvertimeOut;
        this.employeeID = employeeID;
        this.requestDate = requestDate;
        this.transactionStatusID = transactionStatusID;
        this.decisionNumber = decisionNumber;
        this.logDate = logDate;
        this.day = day;
        this.dayType = dayType;
        this.roster = roster;
        this.shift = shift;
        this.notes = notes;
        this.nightShift = nightShift;
        this.shiftLimit = shiftLimit;
        this.locationID = locationID;
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

    public String getEmployeeAttendanceLogUID() {
        return employeeAttendanceLogUID;
    }

    public void setEmployeeAttendanceLogUID(String employeeAttendanceLogUID) {
        this.employeeAttendanceLogUID = employeeAttendanceLogUID;
    }

    public ReturnValue withEmployeeAttendanceLogUID(String employeeAttendanceLogUID) {
        this.employeeAttendanceLogUID = employeeAttendanceLogUID;
        return this;
    }

    public String getActualLogIn() {
        return actualLogIn;
    }

    public void setActualLogIn(String actualLogIn) {
        this.actualLogIn = actualLogIn;
    }

    public ReturnValue withActualLogIn(String actualLogIn) {
        this.actualLogIn = actualLogIn;
        return this;
    }

    public String getActualBreakStart() {
        return actualBreakStart;
    }

    public void setActualBreakStart(String actualBreakStart) {
        this.actualBreakStart = actualBreakStart;
    }

    public ReturnValue withActualBreakStart(String actualBreakStart) {
        this.actualBreakStart = actualBreakStart;
        return this;
    }

    public String getActualBreakEnd() {
        return actualBreakEnd;
    }

    public void setActualBreakEnd(String actualBreakEnd) {
        this.actualBreakEnd = actualBreakEnd;
    }

    public ReturnValue withActualBreakEnd(String actualBreakEnd) {
        this.actualBreakEnd = actualBreakEnd;
        return this;
    }

    public String getActualLogOut() {
        return actualLogOut;
    }

    public void setActualLogOut(String actualLogOut) {
        this.actualLogOut = actualLogOut;
    }

    public ReturnValue withActualLogOut(String actualLogOut) {
        this.actualLogOut = actualLogOut;
        return this;
    }

    public String getActualOvertimeIn() {
        return actualOvertimeIn;
    }

    public void setActualOvertimeIn(String actualOvertimeIn) {
        this.actualOvertimeIn = actualOvertimeIn;
    }

    public ReturnValue withActualOvertimeIn(String actualOvertimeIn) {
        this.actualOvertimeIn = actualOvertimeIn;
        return this;
    }

    public String getActualOvertimeOut() {
        return actualOvertimeOut;
    }

    public void setActualOvertimeOut(String actualOvertimeOut) {
        this.actualOvertimeOut = actualOvertimeOut;
    }

    public ReturnValue withActualOvertimeOut(String actualOvertimeOut) {
        this.actualOvertimeOut = actualOvertimeOut;
        return this;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public ReturnValue withEmployeeID(int employeeID) {
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

    public String getDecisionNumber() {
        return decisionNumber;
    }

    public void setDecisionNumber(String decisionNumber) {
        this.decisionNumber = decisionNumber;
    }

    public ReturnValue withDecisionNumber(String decisionNumber) {
        this.decisionNumber = decisionNumber;
        return this;
    }

    public String getLogDate() {
        return logDate;
    }

    public void setLogDate(String logDate) {
        this.logDate = logDate;
    }

    public ReturnValue withLogDate(String logDate) {
        this.logDate = logDate;
        return this;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public ReturnValue withDay(String day) {
        this.day = day;
        return this;
    }

    public String getDayType() {
        return dayType;
    }

    public void setDayType(String dayType) {
        this.dayType = dayType;
    }

    public ReturnValue withDayType(String dayType) {
        this.dayType = dayType;
        return this;
    }

    public String getRoster() {
        return roster;
    }

    public void setRoster(String roster) {
        this.roster = roster;
    }

    public ReturnValue withRoster(String roster) {
        this.roster = roster;
        return this;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public ReturnValue withShift(String shift) {
        this.shift = shift;
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

    public boolean isNightShift() {
        return nightShift;
    }

    public void setNightShift(boolean nightShift) {
        this.nightShift = nightShift;
    }

    public ReturnValue withNightShift(boolean nightShift) {
        this.nightShift = nightShift;
        return this;
    }

    public String getShiftLimit() {
        return shiftLimit;
    }

    public void setShiftLimit(String shiftLimit) {
        this.shiftLimit = shiftLimit;
    }

    public ReturnValue withShiftLimit(String shiftLimit) {
        this.shiftLimit = shiftLimit;
        return this;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public ReturnValue withLocationID(String locationID) {
        this.locationID = locationID;
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
        dest.writeValue(iD);
        dest.writeValue(employeeAttendanceLogUID);
        dest.writeValue(actualLogIn);
        dest.writeValue(actualBreakStart);
        dest.writeValue(actualBreakEnd);
        dest.writeValue(actualLogOut);
        dest.writeValue(actualOvertimeIn);
        dest.writeValue(actualOvertimeOut);
        dest.writeValue(employeeID);
        dest.writeValue(requestDate);
        dest.writeValue(transactionStatusID);
        dest.writeValue(decisionNumber);
        dest.writeValue(logDate);
        dest.writeValue(day);
        dest.writeValue(dayType);
        dest.writeValue(roster);
        dest.writeValue(shift);
        dest.writeValue(notes);
        dest.writeValue(nightShift);
        dest.writeValue(shiftLimit);
        dest.writeValue(locationID);
        dest.writeValue(fullAccess);
        dest.writeList(exclusionFields);
        dest.writeValue(accessibilityAttribute);
    }

    public int describeContents() {
        return  0;
    }

}
