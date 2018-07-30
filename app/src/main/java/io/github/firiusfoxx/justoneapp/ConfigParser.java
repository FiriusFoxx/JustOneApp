package io.github.firiusfoxx.justoneapp;

import android.os.Environment;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ConfigParser {

    private String PackageName;
    private boolean DenySettings;

    public int parse() {
        InputStream stream;
        try {
            stream = new FileInputStream(Environment.getExternalStorageDirectory() + "/JustOneApp/config.xml" );
        } catch (FileNotFoundException e) {
            Log.e("ConfigParser","Config File " + Environment.getExternalStorageDirectory() + "/JustOneApp/config.xml not found, ERR 31");
            return 1;
        }
        XMLParser XMLParser = new XMLParser();
        List<XMLParser.Entry> entries;
        try {
            entries = XMLParser.parse(stream);
        } catch (XmlPullParserException e) {
            Log.e("ConfigParser","Can't Parse Config File " + Environment.getExternalStorageDirectory() + "/JustOneApp/config.xml for Unknown Reasons, ERR 32");
            return 2;
        } catch (IOException e) {
            Log.e("ConfigParser","Can't Parse Config File " + Environment.getExternalStorageDirectory() + "/JustOneApp/config.xml for Unknown Reasons, ERR 33");
            return 3;
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    Log.e("ConfigParser","Can't close stream for Unknown Reasons, ERR 34");
                    return 4;
                }
            }
        }
        if (entries != null) {
            for (XMLParser.Entry entry : entries) {
                Log.v("ConfigParser","The Package Name is "+ entry.PackageName);
                Log.v("ConfigParser","Deny Settings is " + entry.DenySettings);
                PackageName = entry.PackageName;
                DenySettings = entry.DenySettings.equals("true") || entry.DenySettings.equals("1") || entry.DenySettings.equals("yes") || entry.DenySettings.equals("on");
            }
        } else {
            Log.e("ConfigParser","Entries is null, ERR 35");
            return 5;
        }
        return 0;
    }

    public String getPackageName() {
        return PackageName;
    }

    public boolean getDenySettings() {
        return DenySettings;
    }
}
