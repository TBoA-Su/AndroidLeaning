package com.tboa.learning10_navigationdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        NavHostFragment navHostFragment= (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
//        NavController controller = navHostFragment.getNavController();
//        NavigationUI.setupActionBarWithNavController(this, controller);

    }

    @Override
    protected void onStart() {
        super.onStart();
        NavController controller = Navigation.findNavController(this, R.id.fragmentContainerView);
        NavigationUI.setupActionBarWithNavController(this, controller);
    }

    @Override
    public boolean onSupportNavigateUp() {
//        return super.onSupportNavigateUp();
        NavController controller = Navigation.findNavController(this, R.id.fragmentContainerView);
        return controller.navigateUp();
    }
}