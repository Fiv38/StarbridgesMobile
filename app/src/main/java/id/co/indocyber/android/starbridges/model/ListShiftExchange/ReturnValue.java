
package id.co.indocyber.android.starbridges.model.ListShiftExchange;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReturnValue implements Serializable, Parcelable
{

    @SerializedName("DecisionNumber")
    @Expose
    private String decisionNumber;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("Shift")
    @Expose
    private String shift;
    @SerializedName("Notes")
    @Expose
    private String notes;
    @SerializedName("EmployeeName")
    @Expose
    private String employeeName;
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
    private final static long serialVersionUID = -2193443971780691226L;

    protected ReturnValue(Parcel in) {
        this.decisionNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.date = ((String) in.readValue((String.class.getClassLoader())));
        this.shift = ((String) in.readValue((String.class.getClassLoader())));
        this.notes = ((String) in.readValue((String.class.getClassLoader())));
        this.employeeName = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ReturnValue() {
    }

    /**
     * 
     * @param decisionNumber
     * @param notes
     * @param date
     * @param shift
     * @param employeeName
     */
    public ReturnValue(String decisionNumber, String date, String shift, String notes, String employeeName) {
        super();
        this.decisionNumber = decisionNumber;
        this.date = date;
        this.shift = shift;
        this.notes = notes;
        this.employeeName = employeeName;
    }

    public String getDecisionNumber() {
        return decisionNumber;
    }

    public void setDecisionNumber(String decisionNumber) {
        this.decisionNumber = decisionNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(decisionNumber);
        dest.writeValue(date);
        dest.writeValue(shift);
        dest.writeValue(notes);
        dest.writeValue(employeeName);
    }

    public int describeContents() {
        return  0;
    }

}
