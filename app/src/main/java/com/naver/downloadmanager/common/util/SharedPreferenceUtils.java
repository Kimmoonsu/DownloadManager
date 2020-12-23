package com.naver.downloadmanager.common.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.naver.downloadmanager.common.Constant;
import com.naver.downloadmanager.data.datasource.URLData;

import java.util.ArrayList;
import java.util.List;

public class SharedPreferenceUtils {

    public static void setStringPreference(Context context, String key, String value) {
        SharedPreferences pref = context.getSharedPreferences(Constant.PRE_KEY, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getStringPreference(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences(Constant.PRE_KEY, context.MODE_PRIVATE);
        if (pref.contains(key)) {
            return pref.getString(key, "");
        }
        return null;
    }

    public static void setData(Context context, String key, List<URLData> urlData) {
        String result = Utils.toJson(urlData);
        setStringPreference(context, key, result);
    }

    public static List<URLData> getData(Context context, String key) {
        String result = getStringPreference(context, key);
        if (result == null) {
            return new ArrayList<>();
        }
        return Utils.fromJson(result);
    }

}
