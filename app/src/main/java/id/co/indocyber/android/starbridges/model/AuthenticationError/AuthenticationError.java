

package id.co.indocyber.android.starbridges.model.AuthenticationError;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthenticationError implements Serializable, Parcelable
{

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("error_description")
    @Expose
    private String errorDescription;
    public final static Parcelable.Creator<AuthenticationError> CREATOR = new Creator<AuthenticationError>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AuthenticationError createFromParcel(Parcel in) {
            return new AuthenticationError(in);
        }

        public AuthenticationError[] newArray(int size) {
            return (new AuthenticationError[size]);
        }

    }
            ;
    private final static long serialVersionUID = -3626330965567062727L;

    protected AuthenticationError(Parcel in) {
        this.error = ((String) in.readValue((String.class.getClassLoader())));
        this.errorDescription = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public AuthenticationError() {
    }

    /**
     *
     * @param error
     * @param errorDescription
     */
    public AuthenticationError(String error, String errorDescription) {
        super();
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public AuthenticationError withError(String error) {
        this.error = error;
        return this;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public AuthenticationError withErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(errorDescription);
    }

    public int describeContents() {
        return 0;
    }

}