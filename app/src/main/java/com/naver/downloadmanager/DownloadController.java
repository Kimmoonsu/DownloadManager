package com.naver.downloadmanager;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;

import static android.os.Environment.DIRECTORY_PICTURES;
import static android.os.Environment.getExternalStorageDirectory;

public class DownloadController extends AppCompatActivity {
    private long mDownloadId;
    private DownloadManager mDownloadManager = null;
    private DownloadReceiver mDownloadReceiver = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLayout = findViewById(R.id.main_layout);
        mDownloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        intentFilter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        mDownloadReceiver = new DownloadReceiver();
        registerReceiver(mDownloadReceiver, intentFilter);

        Button button = (Button) findViewById(R.id.button);
        requestCameraPermission();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadImageForSDK28();
            }
        });
    }
    private static final int PERMISSION_REQUEST_EXTERNAL_STORAGE = 0;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // BEGIN_INCLUDE(onRequestPermissionsResult)
        if (requestCode == PERMISSION_REQUEST_EXTERNAL_STORAGE) {
            // Request for camera permission.
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission has been granted. Start camera preview Activity.

            } else {
                // Permission request was denied.

            }
        }
        // END_INCLUDE(onRequestPermissionsResult)
    }
    private View mLayout;
    private void requestCameraPermission() {
        Log.d("Moonsu", "requestPermission");
        // Permission has not been granted and must be requested.
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // Display a SnackBar with cda button to request the missing permission.
            Log.d("Moonsu", "external");
            Snackbar.make(mLayout, "ahsdkjlvhajklsd",
                    Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Request the permission
                    ActivityCompat.requestPermissions(DownloadController.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            PERMISSION_REQUEST_EXTERNAL_STORAGE);
                }
            }).show();

        } else {
            Log.d("Moonsu", "else ");
            Snackbar.make(mLayout, "failed", Snackbar.LENGTH_SHORT).show();
            // Request the permission. The result will be received in onRequestPermissionResult().
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_EXTERNAL_STORAGE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == WRITE_REQUEST_CODE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            Log.d("Moonsu", "uri : " + uri.getPath());
            addText(uri);
        }
    }

    public void addText(Uri uri){
//        File file = new File(getExternalCacheDir(), "test.png");
//        Log.d("Moonsu", "path : " + file.getAbsolutePath());
        String url = "https://imageproxy.themaven.net//https%3A%2F%2Fwww.history.com%2F.image%2FMTY4OTA4MzI0ODc4NjkwMDAw%2Fchristmas-tree-gettyimages-1072744106.jpg";

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url))
                .setTitle("Downloading a video")
                .setDescription("Downloading Dev Summit")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                .setDestinationUri(uri)
                .setRequiresCharging(false)
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(true);

        mDownloadId = mDownloadManager.enqueue(request);
    }
    private int WRITE_REQUEST_CODE = 43;
    private void downloadImageForSDK28() {
        File file = new File(getExternalCacheDir(), "test.png");
        Log.d("Moonsu", "from file : " + Uri.fromFile(file));
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Downloads.TITLE, "scoped");
        contentValues.put(MediaStore.Downloads.DISPLAY_NAME, "scoped.jpg");
        contentValues.put(MediaStore.Downloads.MIME_TYPE, "*/*");


//        // If you downloaded to a specific folder inside "Downloads" folder
//        contentValues.put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS + File.separator + "Image");

        // Insert into the database
        ContentResolver database = getContentResolver();
        Uri uri = database.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues);
        if (uri == null) {
            Log.d("Moonsu", "item is null ");
        } else {
            Log.d("Moonsu", "item : " + uri.toString());
        }
        String url = "https://imageproxy.themaven.net//https%3A%2F%2Fwww.history.com%2F.image%2FMTY4OTA4MzI0ODc4NjkwMDAw%2Fchristmas-tree-gettyimages-1072744106.jpg";

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url))
                .setTitle("Downloading a video")
                .setDescription("Downloading Dev Summit")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, uri.getLastPathSegment())
                .setRequiresCharging(false)
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(true);

        mDownloadId = mDownloadManager.enqueue(request);
    }
    private void downloadImage() {
        try {
            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("*/*" );
            intent.putExtra(Intent.EXTRA_TITLE,"merry.png");

            startActivityForResult(intent, WRITE_REQUEST_CODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        File file = new File(getExternalCacheDir(), "test.png");
//        Log.d("Moonsu", "path : " + file.getAbsolutePath());
//        String url = "https://imageproxy.themaven.net//https%3A%2F%2Fwww.history.com%2F.image%2FMTY4OTA4MzI0ODc4NjkwMDAw%2Fchristmas-tree-gettyimages-1072744106.jpg";
//
//        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url))
//                .setTitle("Downloading a video")
//                .setDescription("Downloading Dev Summit")
//                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
//                .setDestinationUri(Uri.fromFile(file))
//                .setRequiresCharging(false)
//                .setAllowedOverMetered(true)
//                .setAllowedOverRoaming(true);
//
//        mDownloadId = mDownloadManager.enqueue(request);


    }

    private class DownloadReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            String actionId = intent.getAction();
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(actionId)) {
                if (mDownloadId == id) {
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(id);
                    Cursor cursor = mDownloadManager.query(query);
                    if (!cursor.moveToFirst()) {
                        return ;
                    }

                    int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                    int status = cursor.getInt(columnIndex);
                    if (status == DownloadManager.STATUS_SUCCESSFUL) {
                        Log.d("Moonsu", "download successful");
                    } else if (status == DownloadManager.STATUS_FAILED) {
                        Log.d("Moonsu", "download FAILED");
                    }
                }
            } else if (DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(actionId)) {
                Log.d("Moonsu", "clicked notifiaction");
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mDownloadReceiver);
    }
}
