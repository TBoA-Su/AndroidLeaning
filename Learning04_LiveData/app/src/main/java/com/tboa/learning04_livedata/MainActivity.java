package com.tboa.learning04_livedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ViewModelWithLiveData viewModelWithLiveData;
    TextView textView;
    ImageButton imageButton1, imageButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        imageButton1 = findViewById(R.id.imageButton);
        imageButton2 = findViewById(R.id.imageButton2);
        viewModelWithLiveData = new ViewModelProvider(this).get(ViewModelWithLiveData.class);
        viewModelWithLiveData.getLikedNumber().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textView.setText(String.valueOf(integer));
            }
        });
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModelWithLiveData.addLikedNumber(1);
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModelWithLiveData.addLikedNumber(-1);
            }
        });
    }
}