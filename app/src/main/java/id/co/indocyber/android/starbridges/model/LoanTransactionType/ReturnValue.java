
package id.co.indocyber.android.starbridges.model.LoanTransactionType;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReturnValue implements Serializable, Parcelable
{

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Installment")
    @Expose
    private Object installment;
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
    private final static long serialVersionUID = -6288384596773644730L;

    protected ReturnValue(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.installment = ((Object) in.readValue((Object.class.getClassLoader())));
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
     * @param id
     * @param name
     */
    public ReturnValue(Integer id, String name, Object installment) {
        super();
        this.id = id;
        this.name = name;
        this.installment = installment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReturnValue withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReturnValue withName(String name) {
        this.name = name;
        return this;
    }

    public Object getInstallment() {
        return installment;
    }

    public void setInstallment(Object installment) {
        this.installment = installment;
    }

    public ReturnValue withInstallment(Object installment) {
        this.installment = installment;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(installment);
    }

    public int describeContents() {
        return  0;
    }

    @Override
    public String toString() {
        return name;
    }
}
