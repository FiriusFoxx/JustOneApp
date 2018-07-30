package io.github.firiusfoxx.justoneapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

public class LaunchPackage {

    private boolean isPackageExisted(String targetPackage, Context context){
        PackageManager pm=context.getPackageManager();
        try {
            PackageInfo info=pm.getPackageInfo(targetPackage,PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
        return true;
    }

    public int LaunchPackage(String PackageName,Context context) {
        if (!isPackageExisted(PackageName,context)) {
            Log.e("LaunchPackage",PackageName + " is not installed on this device, ERR 51");
            return 1;
        }
        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(PackageName);
        context.startActivity(launchIntent);
        return 0;
    }

}
