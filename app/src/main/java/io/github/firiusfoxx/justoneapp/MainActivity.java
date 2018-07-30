package io.github.firiusfoxx.justoneapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainTask task = new MainTask(getApplicationContext());
        task.execute();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }


}