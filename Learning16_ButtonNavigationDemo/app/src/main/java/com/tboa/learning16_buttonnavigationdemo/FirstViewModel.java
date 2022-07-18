package com.tboa.learning16_buttonnavigationdemo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FirstViewModel extends ViewModel {
    private MutableLiveData<Float> rotation;
    // TODO: Implement the ViewModel


    public MutableLiveData<Float> getRotation() {
        if (rotation == null) {
            rotation = new MutableLiveData<>();
            rotation.setValue(0f);
        }
        return rotation;
    }
}