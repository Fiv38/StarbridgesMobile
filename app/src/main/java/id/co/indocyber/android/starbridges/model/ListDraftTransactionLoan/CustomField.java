
package id.co.indocyber.android.starbridges.model.ListDraftTransactionLoan;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

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
    private final static long serialVersionUID = 115660802454874387L;

    protected CustomField(Parcel in) {
    }

    public void writeToParcel(Parcel dest, int flags) {
    }

    public int describeContents() {
        return  0;
    }

}
