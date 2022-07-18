package com.tboa.learning13_calculationtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    NavController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplication(), getString(R.string.app_name), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        controller = Navigation.findNavController(this, R.id.fragmentContainerView);
        NavigationUI.setupActionBarWithNavController(this, controller);
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (controller.getCurrentDestination().getId() == R.id.questionFragment) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.quit_dialog_title));
            builder.setPositiveButton(R.string.dialog_postive_messgae, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    controller.navigateUp();
                }
            });
            builder.setNegativeButton(R.string.dialog_negative_message, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else if (controller.getCurrentDestination().getId() == R.id.winFragment) {
            controller.navigate(R.id.action_winFragment_to_titleFragment);
        } else if (controller.getCurrentDestination().getId() == R.id.loseFragment) {
            controller.navigate(R.id.action_loseFragment_to_titleFragment);
        }
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (controller.getCurrentDestination().getId() == R.id.titleFragment) {
            finish();
        } else {
            onSupportNavigateUp();
        }
    }
}