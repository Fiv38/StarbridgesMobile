
package id.co.indocyber.android.starbridges.model.getclaimpolicy;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReturnValue implements Serializable, Parcelable
{

    @SerializedName("Value")
    @Expose
    private Integer value;
    @SerializedName("Text")
    @Expose
    private String text;
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
    private final static long serialVersionUID = 3483441414533881706L;

    protected ReturnValue(Parcel in) {
        this.value = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.text = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ReturnValue() {
    }

    /**
     * 
     * @param text
     * @param value
     */
    public ReturnValue(Integer value, String text) {
        super();
        this.value = value;
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(value);
        dest.writeValue(text);
    }

    public int describeContents() {
        return  0;
    }

    @Override
    public String toString() {
        return text;
    }
}
