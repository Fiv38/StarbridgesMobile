
package id.co.indocyber.android.starbridges.model.ListLoanSchedule;

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
    @SerializedName("Amount")
    @Expose
    private int amount;
    @SerializedName("ProcessStep")
    @Expose
    private String processStep;
    @SerializedName("Installment")
    @Expose
    private int installment;
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
    private final static long serialVersionUID = -2462783911234334878L;

    protected ReturnValue(Parcel in) {
        this.iD = ((String) in.readValue((String.class.getClassLoader())));
        this.amount = ((int) in.readValue((int.class.getClassLoader())));
        this.processStep = ((String) in.readValue((String.class.getClassLoader())));
        this.installment = ((int) in.readValue((int.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ReturnValue() {
    }

    /**
     * 
     * @param installment
     * @param amount
     * @param processStep
     * @param iD
     */
    public ReturnValue(String iD, int amount, String processStep, int installment) {
        super();
        this.iD = iD;
        this.amount = amount;
        this.processStep = processStep;
        this.installment = installment;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ReturnValue withAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public String getProcessStep() {
        return processStep;
    }

    public void setProcessStep(String processStep) {
        this.processStep = processStep;
    }

    public ReturnValue withProcessStep(String processStep) {
        this.processStep = processStep;
        return this;
    }

    public int getInstallment() {
        return installment;
    }

    public void setInstallment(int installment) {
        this.installment = installment;
    }

    public ReturnValue withInstallment(int installment) {
        this.installment = installment;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(iD);
        dest.writeValue(amount);
        dest.writeValue(processStep);
        dest.writeValue(installment);
    }

    public int describeContents() {
        return  0;
    }

}
