package com.tboa.learning08_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyData myData = new MyData(getApplicationContext());

        myData.number = 1000;
        myData.save();
        int y = myData.load();
        String TAG = "myLog";
        Log.d(TAG, "onCreate" + y);
        Toast.makeText(getApplicationContext(), "vrb", Toast.LENGTH_LONG).show();
    }
}