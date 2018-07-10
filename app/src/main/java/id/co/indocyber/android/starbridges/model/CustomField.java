package id.co.indocyber.android.starbridges.model;

/**
 * Created by user on 5/9/2018.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class CustomField implements Serializable, Parcelable
{

    public final static Creator<CustomField> CREATOR = new Creator<CustomField>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CustomField createFromParcel(Parcel in) {
            return new CustomField(in);
        }

        public CustomField[] newArray(int size) {
            return (new CustomField[size]);
        }

    }
            ;
    private final static long serialVersionUID = -5042544134802743662L;

    protected CustomField(Parcel in) {
    }

    public void writeToParcel(Parcel dest, int flags) {
    }

    public int describeContents() {
        return  0;
    }

}