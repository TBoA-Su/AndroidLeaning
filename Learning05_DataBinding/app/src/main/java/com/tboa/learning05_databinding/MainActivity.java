package com.tboa.learning05_databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tboa.learning05_databinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    MainViewModel mainViewModel;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setData(mainViewModel);
        binding.setLifecycleOwner(this);
    }
}