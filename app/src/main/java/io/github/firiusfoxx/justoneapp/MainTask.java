package io.github.firiusfoxx.justoneapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

public class MainTask extends AsyncTask<Void,Void,Void> {

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private Context mContext;

    public MainTask(Context context) {
        mContext = context;
    }

    private void ToastError(final int module, final int err) {
        mHandler.post(new Runnable() {
            public void run() {
                Toast.makeText(mContext,"ERR "+ module + err ,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected Void doInBackground(Void... voids) {
        ConfigFolder ConfigFolder = new ConfigFolder();
        int ConfigFolderResult = ConfigFolder.CheckAndCreate();
        if (ConfigFolderResult != 0) {
            ToastError(1,ConfigFolderResult);
            return null;
        }
        ConfigFile ConfigFile = new ConfigFile();
        int ConfigFileResult = ConfigFile.CheckAndCreate(mContext);
        if (ConfigFileResult != 0) {
           ToastError(2,ConfigFileResult);
            return null;
        }
        ConfigParser ConfigParser = new ConfigParser();
        int ConfigParserResult = ConfigParser.parse();
        if (ConfigParserResult != 0) {
            ToastError(3,ConfigParserResult);
            return null;
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Log.e("MainTask", "Task Interrupted, ERR 41");
            ToastError(4,1);
            return null;
        }
        LaunchPackage LaunchPackage = new LaunchPackage();
        int LaunchPackageResult = LaunchPackage.LaunchPackage(ConfigParser.getPackageName(), mContext);
        if (LaunchPackageResult != 0) {
            ToastError(5,LaunchPackageResult);
            return null;
        }
        return null;
    }

}
