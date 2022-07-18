package com.tboa.learning07_viewmodelrestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.tboa.learning07_viewmodelrestore.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    MainViewModel mainViewModel;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setData(mainViewModel);
        binding.setLifecycleOwner(this);
    }
}