package id.co.indocyber.android.starbridges.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by user on 5/11/2018.
 */

public class RegIMEI implements Serializable, Parcelable
{

    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("IMEI")
    @Expose
    private String iMEI;
    public final static Creator<RegIMEI> CREATOR = new Creator<RegIMEI>() {


        @SuppressWarnings({
                "unchecked"
        })
        public RegIMEI createFromParcel(Parcel in) {
            return new RegIMEI(in);
        }

        public RegIMEI[] newArray(int size) {
            return (new RegIMEI[size]);
        }

    }
            ;
    private final static long serialVersionUID = -6297578159920580505L;

    protected RegIMEI(Parcel in) {
        this.userName = ((String) in.readValue((String.class.getClassLoader())));
        this.password = ((String) in.readValue((String.class.getClassLoader())));
        this.iMEI = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public RegIMEI() {
    }

    /**
     *
     * @param iMEI
     * @param userName
     * @param password
     */
    public RegIMEI(String userName, String password, String iMEI) {
        super();
        this.userName = userName;
        this.password = password;
        this.iMEI = iMEI;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIMEI() {
        return iMEI;
    }

    public void setIMEI(String iMEI) {
        this.iMEI = iMEI;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(userName);
        dest.writeValue(password);
        dest.writeValue(iMEI);
    }

    public int describeContents() {
        return  0;
    }

}
