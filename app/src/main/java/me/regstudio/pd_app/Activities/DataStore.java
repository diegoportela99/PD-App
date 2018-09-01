package me.regstudio.pd_app.Activities;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by diego on 8/23/2018.
 */

//class used for storing information used by app and database
public class DataStore {

    //constructor
    public DataStore() {

    }

    
    //added to JSON files and or Folders to store information and upload to database.

    public void addText() {}
    public void addHeartRate() {}
    public void addFile() {}
    public void addVideo() {}
    public void addPersonalInfo() {}

    private byte BUFFER = 127; // EDIT THIS -------------------------

    public void zip(String[] _files, String zipFileName) {
        try {
            BufferedInputStream origin = null;
            FileOutputStream dest = new FileOutputStream(zipFileName);
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
                    dest));
            byte data[] = new byte[BUFFER];

            for (int i = 0; i < _files.length; i++) {
                Log.v("Compress", "Adding: " + _files[i]);
                FileInputStream fi = new FileInputStream(_files[i]);
                origin = new BufferedInputStream(fi, BUFFER);

                ZipEntry entry = new ZipEntry(_files[i].substring(_files[i].lastIndexOf("/") + 1));
                out.putNextEntry(entry);
                int count;

                while ((count = origin.read(data, 0, BUFFER)) != -1) {
                    out.write(data, 0, count);
                }
                origin.close();
            }

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //To unzip .. use :
    //ZipManager zipManager = new ZipManager();
    //zipManager.unzip(inputPath + inputFile, outputPath);

    public void unzip(String _zipFile, String _targetLocation) {

        //create target location folder if not exist
        //dirChecker(targetLocatioan);

        try {
            FileInputStream fin = new FileInputStream(_zipFile);
            ZipInputStream zin = new ZipInputStream(fin);
            ZipEntry ze = null;
            while ((ze = zin.getNextEntry()) != null) {

                //create dir if required while unzipping
                if (ze.isDirectory()) {
                    //dirChecker(ze.getName());
                } else {
                    FileOutputStream fout = new FileOutputStream(_targetLocation + ze.getName());
                    for (int c = zin.read(); c != -1; c = zin.read()) {
                        fout.write(c);
                    }

                    zin.closeEntry();
                    fout.close();
                }

            }
            zin.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    // functions may be used in next iteration

    public String readText() {return "";}
    public int readHeart() {return 0;}
    public int readPersonalInfo() {return 0;}


}
