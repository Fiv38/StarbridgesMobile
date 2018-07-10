package id.co.indocyber.android.starbridges.utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by user on 07/03/2018.
 */

public class SharedPreferenceUtils {

    public static String getSetting(Context context, String key, String def)
    {// baca data yang disimpan (string)
        SharedPreferences settings=context.getApplicationContext().getSharedPreferences("bootcamp",0);
        String silent=settings.getString(key,def);
        return silent;

    }

    public static void setSetting(Context context, String key, String val)// Simpet data string
    {
        SharedPreferences settings= context.getApplicationContext().getSharedPreferences("bootcamp",0);
        SharedPreferences.Editor editor=settings.edit();
        editor.putString(key, val);
        editor.commit();
    }
}
