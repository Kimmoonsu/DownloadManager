package com.naver.downloadmanager.common.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.naver.downloadmanager.data.datasource.URLData;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkCapabilities networkCapabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
        if (networkCapabilities != null) {
            return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) | networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);
        }
        return false;
    }

    public static String toJson(List<URLData> urls) {
        Gson gson = new Gson();
        String result = gson.toJson(urls);
        return result;
    }

    public static List<URLData> fromJson(String data) {
        Gson gson = new Gson();
        List<URLData> urls = gson.fromJson(data, new TypeToken<List<URLData>>(){}.getType());
        return urls;
    }
}
