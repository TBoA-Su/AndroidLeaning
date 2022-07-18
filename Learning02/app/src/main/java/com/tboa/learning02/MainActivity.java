package com.tboa.learning02;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    String TAG = "MyTag";
    ActionBar tb;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tb = getSupportActionBar();
        tb.setTitle(R.string.name);
        textView = findViewById(R.id.textView);
        if (savedInstanceState != null) {
            String s = savedInstanceState.getString("KEY");
            textView.setText(s);
            tb.setSubtitle(R.string.app_name);
        }
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(R.string.b1);
            }
        });

    }

    //用于临时保存界面数据，防止界面应旋转屏幕而摧毁
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("KEY", textView.getText().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestory");
    }
}