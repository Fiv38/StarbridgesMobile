
package id.co.indocyber.android.starbridges.model.EditShiftExchange;

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
    @SerializedName("EmployeeID")
    @Expose
    private String employeeID;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("ShiftID")
    @Expose
    private String shiftID;
    @SerializedName("ShiftName")
    @Expose
    private String shiftName;
    @SerializedName("Notes")
    @Expose
    private String notes;
    @SerializedName("TransactionStatusID")
    @Expose
    private Integer transactionStatusID;
    @SerializedName("AttachmentFile")
    @Expose
    private Object attachmentFile;
    @SerializedName("AttachmentID")
    @Expose
    private Object attachmentID;
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
    private final static long serialVersionUID = -5432180216274207243L;

    protected ReturnValue(Parcel in) {
        this.iD = ((String) in.readValue((String.class.getClassLoader())));
        this.employeeID = ((String) in.readValue((String.class.getClassLoader())));
        this.date = ((String) in.readValue((String.class.getClassLoader())));
        this.shiftID = ((String) in.readValue((String.class.getClassLoader())));
        this.shiftName = ((String) in.readValue((String.class.getClassLoader())));
        this.notes = ((String) in.readValue((String.class.getClassLoader())));
        this.transactionStatusID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.attachmentFile = ((Object) in.readValue((Object.class.getClassLoader())));
        this.attachmentID = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ReturnValue() {
    }

    /**
     * 
     * @param employeeID
     * @param shiftName
     * @param attachmentFile
     * @param attachmentID
     * @param notes
     * @param shiftID
     * @param date
     * @param iD
     * @param transactionStatusID
     */
    public ReturnValue(String iD, String employeeID, String date, String shiftID, String shiftName, String notes, Integer transactionStatusID, Object attachmentFile, Object attachmentID) {
        super();
        this.iD = iD;
        this.employeeID = employeeID;
        this.date = date;
        this.shiftID = shiftID;
        this.shiftName = shiftName;
        this.notes = notes;
        this.transactionStatusID = transactionStatusID;
        this.attachmentFile = attachmentFile;
        this.attachmentID = attachmentID;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShiftID() {
        return shiftID;
    }

    public void setShiftID(String shiftID) {
        this.shiftID = shiftID;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getTransactionStatusID() {
        return transactionStatusID;
    }

    public void setTransactionStatusID(Integer transactionStatusID) {
        this.transactionStatusID = transactionStatusID;
    }

    public Object getAttachmentFile() {
        return attachmentFile;
    }

    public void setAttachmentFile(Object attachmentFile) {
        this.attachmentFile = attachmentFile;
    }

    public Object getAttachmentID() {
        return attachmentID;
    }

    public void setAttachmentID(Object attachmentID) {
        this.attachmentID = attachmentID;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(iD);
        dest.writeValue(employeeID);
        dest.writeValue(date);
        dest.writeValue(shiftID);
        dest.writeValue(shiftName);
        dest.writeValue(notes);
        dest.writeValue(transactionStatusID);
        dest.writeValue(attachmentFile);
        dest.writeValue(attachmentID);
    }

    public int describeContents() {
        return  0;
    }

}
