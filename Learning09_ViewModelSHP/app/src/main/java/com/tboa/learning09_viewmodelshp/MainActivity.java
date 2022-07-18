package com.tboa.learning09_viewmodelshp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.tboa.learning09_viewmodelshp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    MainActivityViewModel mainActivityViewModel;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        binding.setData(mainActivityViewModel);
        binding.setLifecycleOwner(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mainActivityViewModel.save();
    }
}