package com.tboa.learning12_navviewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> number;

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Integer> getNumber() {
        if (number == null) {
            number = new MutableLiveData<>();
            number.setValue(0);
        }
        return number;
    }

    public void add(int x) {
        number.setValue(getNumber().getValue() + x);
        if (getNumber().getValue() < 0) {
            Toast.makeText(getApplication(), "已达下限", Toast.LENGTH_LONG).show();
            number.setValue(0);
        } else if (getNumber().getValue() > 10) {
            Toast.makeText(getApplication(), "已达上限", Toast.LENGTH_LONG).show();
            number.setValue(10);
        }
    }

}
