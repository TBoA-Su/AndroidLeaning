package com.tboa.learning14_lifecycles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {
    //    Chronometer chronometer;
//    private long elapsedTime;
    MyChronomen myChronomen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myChronomen = findViewById(R.id.meter);
//        chronomen.setBase(SystemClock.elapsedRealtime());
//        chronometer.start();
        getLifecycle().addObserver(myChronomen);
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        elapsedTime = SystemClock.elapsedRealtime() - chronometer.getBase();
//        chronometer.stop();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        chronometer.setBase(SystemClock.elapsedRealtime() - elapsedTime);
//        chronometer.start();
//    }
}