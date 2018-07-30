package io.github.firiusfoxx.justoneapp;

import android.os.Environment;
import android.util.Log;

import java.io.File;

public class ConfigFolder {
    public int CheckAndCreate() {
        File Root = new File(Environment.getExternalStorageDirectory(),".");
        File Folder = new File(Environment.getExternalStorageDirectory(), "JustOneApp");
        if (!Root.canRead()) {
            Log.e("ConfigFolder","We don't have read permissions, ERR 11");
            return 1;
        }
        if (!Folder.exists()) {
            if (!Root.canWrite()) {
                Log.e("ConfigFolder","We don't have write permissions, ERR 12");
                return 2;
            }
            Log.i("Config Folder","Creating Folder " + Environment.getExternalStorageDirectory() + "/JustOneApp");
            if (!Folder.mkdir()) {
                Log.e("ConfigFolder","Could not create config folder, ERR 13");
                return 3;
            }

        }
        if (!Folder.isDirectory()) {
            Log.e("Config Folder","File " + Environment.getExternalStorageDirectory() + "/JustOneApp exists as a file not a Folder, ERR 14");
            return 4;
        }
        if (!Folder.canRead()) {
            Log.e("Config Folder","Can't Read in " + Environment.getExternalStorageDirectory() + "/JustOneApp, ERR 15");
            return 5;
        }
        return 0;
    }
}
