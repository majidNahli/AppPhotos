package nehli.ma.co.appphotos.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Majid on 03/06/2016.
 */
public class PreferencesManager {

    public static void putStringPref(Context mContext, String key, String value) {

        SharedPreferences sharedPref = mContext.getSharedPreferences("SHARED", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getStringPref(Context mContext, String key) {
        SharedPreferences sharedPref = mContext.getSharedPreferences("SHARED", Context.MODE_PRIVATE);
        return sharedPref.getString(key, null);

    }
}
