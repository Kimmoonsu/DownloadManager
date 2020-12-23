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

    public static List<URLData> initData() {
        List<URLData> urls = new ArrayList<>();
        String urlData[] = {
                "https://imageproxy.themaven.net//https%3A%2F%2Fwww.history.com%2F.image%2FMTY4OTA4MzI0ODc4NjkwMDAw%2Fchristmas-tree-gettyimages-1072744106.jpg",
                "https://www.history.com/.image/c_fill%2Ccs_srgb%2Cfl_progressive%2Ch_400%2Cq_auto:good%2Cw_620/MTY4ODE4ODA4MzY1MDAwNDY1/christmas-gettyimages-184652817.jpg",
                "https://api.time.com/wp-content/uploads/2019/06/what-is-half-christmas-workaholics.jpg",
                "https://www.scotsman.com/webimg/b25lY21zOjBlZjVkYThkLThjMTktNDM4Yi1hZjBhLTk2MWM3MTMyZDVlZjo4ZjhmMzY0MC1jYzBiLTQ2ZTYtOWUwMi05YmU3MTdkMTRhYjQ=.jpg",
                "https://www.history.com/.image/ar_16:9%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cg_faces:center%2Cq_auto:good%2Cw_768/MTY5MDk1ODMyOTUwNTQ4MzYy/american-christmas-traditions-gettyimages-487756624.jpg",
                "https://www.thoughtco.com/thmb/EeiZ2qNO8kAdeTXZ1MUFCWiwMEQ=/1885x1414/smart/filters:no_upscale()/close-up-of-christmas-tree-898733426-5c79da8a46e0fb00018bd7fb.jpg",
                "https://static.officeholidays.com/images/1280x853c/christmas.jpg",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4"
        };
        for (int i = 0; i < urlData.length; i++) {
            urls.add(new URLData((i+1), urlData[i]));
        }
        return urls;
    }
}
