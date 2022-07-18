package com.tboa.learning03_viewmodeltest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    MyViewModel myViewModel;

    TextView textView;
    Button button, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        textView = findViewById(R.id.textView);
        textView.setText(String.valueOf(myViewModel.number));
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.number++;
                textView.setText(String.valueOf(myViewModel.number));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.number--;
                textView.setText(String.valueOf(myViewModel.number));
            }
        });
    }
}