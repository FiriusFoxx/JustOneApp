package io.github.firiusfoxx.justoneapp;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConfigFile {

    private int copyFileToExternalStorage(int resourceId, String resourceName, Context context){
        String pathSDCard = Environment.getExternalStorageDirectory() + "/JustOneApp/" + resourceName;
        try{
            InputStream in = context.getResources().openRawResource(resourceId);
            FileOutputStream out = null;
            out = new FileOutputStream(pathSDCard);
            byte[] buff = new byte[1024];
            int read = 0;
            try {
                while ((read = in.read(buff)) > 0) {
                    out.write(buff, 0, read);
                }
            } finally {
                in.close();
                out.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("ConfigFile", "Can't copy config file to " + Environment.getExternalStorageDirectory() + "/JustOneApp/config.xml because the file is missing, ERR 23");
            return 3;
        } catch (IOException e) {
            Log.e("ConfigFile", "Can't copy config file to " + Environment.getExternalStorageDirectory() + "/JustOneApp/config.xml for unknown reasons, ERR 24");
            return 4;
        }
        return 0;
    }

    public int CheckAndCreate(Context context) {
        File Folder = new File(Environment.getExternalStorageDirectory(), "JustOneApp");
        File Config = new File(Environment.getExternalStorageDirectory(), "JustOneApp/config.xml");
        if (!Config.exists()) {
            if (Folder.canWrite()) {
                try {
                    if (!Config.createNewFile()) {
                        Log.e("ConfigFile", "Can't create config file in " + Environment.getExternalStorageDirectory() + "/JustOneApp for unknown reasons, ERR 21");
                        return 1;
                    }
                } catch (IOException e) {
                    Log.e("ConfigFile", "Can't create config file in " + Environment.getExternalStorageDirectory() + "/JustOneApp for unknown reasons, ERR 22");
                    return 2;
                }
                int Copystatus = copyFileToExternalStorage(R.raw.config, "config.xml",context);
                if (Copystatus != 0) {
                    return Copystatus;
                }
            } else {
                Log.e("ConfigFile", "Can't create config file in " + Environment.getExternalStorageDirectory() + "/JustOneApp because we don't have write permissions, ERR 25");
                return 5;
            }
        }
        return 0;
    }
}
