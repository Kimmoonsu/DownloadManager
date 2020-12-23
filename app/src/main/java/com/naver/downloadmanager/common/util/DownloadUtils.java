package com.naver.downloadmanager.common.util;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.webkit.URLUtil;

import com.naver.downloadmanager.R;

public class DownloadUtils {
    public static final String TAG = "DownloadUtils";

    public static long downloadFile(Context context, String url) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        String mimeType = "*/*";
        String fileName = URLUtil.guessFileName(url, null, mimeType);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url))
                .setTitle(context.getResources().getString(R.string.title_download))
                .setDescription(context.getResources().getString(R.string.description_download))
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
                .setRequiresCharging(false)
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(true);
        if (downloadManager != null) {
            return downloadManager.enqueue(request);
        }
        return -1;
    }

    public static boolean isSuccessDownload(Context context, long id) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(id);
        Cursor cursor = downloadManager.query(query);
        if (!cursor.moveToFirst()) {
            return false;
        }

        int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
        int status = cursor.getInt(columnIndex);
        if (status == DownloadManager.STATUS_SUCCESSFUL) {
            Log.d(TAG, "download successful");
            return true;
        } else if (status == DownloadManager.STATUS_FAILED) {
            Log.d(TAG, "download FAILED");
            return false;
        }
        return false;
    }
}
