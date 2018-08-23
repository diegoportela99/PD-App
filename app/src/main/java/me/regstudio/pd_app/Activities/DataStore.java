package me.regstudio.pd_app.Activities;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import me.regstudio.pd_app.R;

public class DataStore extends AppCompatActivity {
    private static final int RC_PERMISSION_STORAGE = 90;

    //Internal files can be read only in the context of your App, they are private app files
    //External files are available from outside the context of your App
    //https://developer.android.com/training/basics/data-storage/files.html

    /** Internal storage -> allows you to store files in your app directory /data/data/packageName/files
     * Flags for internal storage
     * MODE_PRIVATE ONLY your app can use it
     * MODE_APPEND if the file exists then write to the end of it
     * MODE_WORLD_READABLE can be read by other applications
     * MODE_WORLD_WRITEABLE can be edited by other apps
     */

    /**
     * External storage -> world readable storage, may not be available so we have to check (is mountable)
     * if it is available or not
     * Two types of files :
     * - public : can be shared with other apps (i.e media files[music,video, pictures)
     * - private : your app files , they are removed when uninstalling your app
     */

    String internalStorageFileStr = "This string is used in writeInternalStorageFile and then retrieved in readInternalStorageFile";
    String internalStorageCacheStr = "This string is used in writeTemp and then retrieved in readTemp";
    String externalStoragePublicFileStr = "This string is used in writeExternalStoragePublicFile and then retrieved in readExternalStoragePublicFile";
    String externalStoragePrivateFileStr = "This string is used in writeExternalStoragePrivateFile and then retrieved in readExternalStoragePrivateFile";
    private TextView txtWhereAreTheDirs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_files_storage);
        //txtWhereAreTheDirs = (TextView) findViewById(R.id.txtWhereAreTheDirs);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission_group.STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, RC_PERMISSION_STORAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case RC_PERMISSION_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted succesfully", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(this, "You have to give permission for this demo to work properly", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    public void readInternalStorageFile(View view) {
        try {
            FileInputStream fis = openFileInput("writeInternalStorageFile.txt");
            String str = IOHelper.stringFromStream(fis);
            txtWhereAreTheDirs.setText(str);
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeInternalStorageFile(View view) {
        try {
            FileOutputStream fos = openFileOutput("writeInternalStorageFile.txt", MODE_PRIVATE);
            try {
                fos.write(internalStorageFileStr.getBytes(), 0, internalStorageFileStr.length());
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void writeInternalStorageFile2(View view) {
        File internalDir = getFilesDir();
        File f = new File(internalDir, "writeInternalStorageFile.txt");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(internalStorageFileStr.getBytes(), 0, internalStorageFileStr.length());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readExternalStoragePrivateFile(View view) {
        //We have to request the permission
        if (isExternalStorageReadable()) {
            File dirPics = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
            File f = new File(dirPics, "externalStoragePrivateFile.txt");
            String str = null;
            try {
                str = IOHelper.stringFromFile(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
            txtWhereAreTheDirs.setText(str);

        }

    }

    public void writeExternalStoragePrivateFile(View view) {
        //requires permission
        File dirPics = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS); //deleted when app uninstalls
        File f = new File(dirPics, "externalStoragePrivateFile.txt");
        try {
            IOHelper.writeToFile(f, externalStoragePrivateFileStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readExternalStoragePublicFile(View view) {
        try {
            String str = IOHelper.stringFromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "externalPublicFile.txt"));
            txtWhereAreTheDirs.setText(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeExternalStoragePublicFile(View view) {
        //requires permission
        if (!isExternalStorageWritable()) {
            Toast.makeText(this, "External Storage not readable", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            IOHelper.writeToFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "externalPublicFile.txt"), externalStoragePublicFileStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readTempFile(View view) {
        File cacheDir = getCacheDir();
        try {
            String str = IOHelper.stringFromFile(new File(cacheDir, "cacheFile.txt"));
            txtWhereAreTheDirs.setText(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeTempFile(View view) {
        try {
            File cacheDir = getCacheDir();
            IOHelper.writeToFile(new File(cacheDir, "cacheFile.txt"), internalStorageCacheStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void whereAreTheDirs(View view) {

        String str = "Internal Storage";
        //Internal Storage

        File filesDir = getFilesDir();
        str += "\ngetFilesDir :" + filesDir.getAbsolutePath();
        File cacheDir = getCacheDir();
        str += "\ngetCacheDir :" + cacheDir.getAbsolutePath();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            File dataDir = getDataDir();
            str += "\ngetDataDir :" + dataDir.getAbsolutePath();
        }
        File tempFile = null;
        try {
            tempFile = File.createTempFile("suffix", "prefix");
            str += "\nTemp Dir " + tempFile.getParent();
            tempFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] dbList = databaseList();
        str += "\nDatabase List :";
        for (String db : dbList)
            str += "\n\t- " + db;

        String[] fileList = fileList();
        str += "\nFile List :";
        for (String file : fileList)
            str += "\n\t- " + file;

        //External Storage
        if (!isExternalStorageReadable()) {
            str += "\nExternal Storage not available or not readable";
            txtWhereAreTheDirs.setText(str);
            return;
        }

        str += "\n\nExternal Storage available and readable";
        File externalCacheDir = getExternalCacheDir();
        str += "\ngetExternalCacheDir :" + externalCacheDir.getAbsolutePath();

        File externalStorageDir = Environment.getExternalStorageDirectory();
        str += "\ngetExternalStorageDirectory :" + externalStorageDir.getAbsolutePath();

        File externalFilesDirDocument = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        str += "\ngetExternalFilesDir :" + externalFilesDirDocument.getAbsolutePath();

        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        str += "\nEnvironment.getExternalStoragePublicDirectory :" + externalStoragePublicDirectory.getAbsolutePath();

        txtWhereAreTheDirs.setText(str);
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state))
            return true;

        return false;
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
            return true;

        return false;
    }