package com.naver.downloadmanager.common.util;

import android.app.DownloadManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

public class DownloadUtils {

    public static long downloadFile(Context context, String url) {
        DownloadManager mDownloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        ContentValues contentValues = new ContentValues();
//        contentValues.put(MediaStore.Downloads.TITLE, "scoped");
//        contentValues.put(MediaStore.Downloads.DISPLAY_NAME, "scoped.jpg");
        contentValues.put(MediaStore.Downloads.MIME_TYPE, "*/*");


//        // If you downloaded to a specific folder inside "Downloads" folder
//        contentValues.put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS + File.separator + "Image");

        // Insert into the database
        ContentResolver contentResolver = context.getContentResolver();
//        if (contentResolver != null) {
//            Cursor cursor = contentResolver.query(MediaStore.Downloads.EXTERNAL_CONTENT_URI, null, null, null);
//            int idColumn = cursor.getColumnIndex(MediaStore.Downloads._ID);
//            int nameColumn = cursor.getColumnIndex(MediaStore.Downloads.DISPLAY_NAME);
//
//        }
        Uri uri = contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues);
        if (uri == null) {
            Log.d("Moonsu", "item is null ");
            return -1;
        } else {
            Log.d("Moonsu", "item : " + uri.toString());
        }
//        String url = "https://imageproxy.themaven.net//https%3A%2F%2Fwww.history.com%2F.image%2FMTY4OTA4MzI0ODc4NjkwMDAw%2Fchristmas-tree-gettyimages-1072744106.jpg";

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url))
                .setTitle("Downloading a video")
                .setDescription("Downloading Dev Summit")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, uri.getPath())
                .setRequiresCharging(false)
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(true);

        return mDownloadManager.enqueue(request);
    }

    public static boolean isSuccessDownload(Context context, long id) {
        DownloadManager mDownloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(id);
        Cursor cursor = mDownloadManager.query(query);
        if (!cursor.moveToFirst()) {
            return false;
        }

        int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
        int status = cursor.getInt(columnIndex);
        if (status == DownloadManager.STATUS_SUCCESSFUL) {
            Log.d("Moonsu", "download successful");
            return true;
        } else if (status == DownloadManager.STATUS_FAILED) {
            Log.d("Moonsu", "download FAILED");
            return false;
        }
        return false;
    }
}
