package com.tboa.learning07_viewmodelrestore;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private final static String myInt_Key = "myInt_Key";
    SavedStateHandle handle;

    public MainViewModel(SavedStateHandle handle) {
        this.handle = handle;
    }

    public MutableLiveData<Integer> getNumber() {
        if (!handle.contains(myInt_Key)) {
            handle.set(myInt_Key, 0);
        }
        return handle.getLiveData(myInt_Key);
    }

    public void add() {
        getNumber().setValue(getNumber().getValue() + 1);
    }
}
