
package com.example.android.starbridges.model.leaverequest;

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
    private final static long serialVersionUID = 8493951856948692481L;

    protected CustomField(Parcel in) {
    }

    public void writeToParcel(Parcel dest, int flags) {
    }

    public int describeContents() {
        return  0;
    }

}
