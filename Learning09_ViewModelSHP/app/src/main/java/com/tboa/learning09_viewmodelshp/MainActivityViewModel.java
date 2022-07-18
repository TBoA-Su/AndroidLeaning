package com.tboa.learning09_viewmodelshp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;

public class MainActivityViewModel extends AndroidViewModel {
    private final SavedStateHandle handle;
    private final String key = getApplication().getResources().getString(R.string.data_key);
    private final String shpName = getApplication().getResources().getString(R.string.shp_name);

    public MainActivityViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        this.handle = handle;
        if (!handle.contains(key)) {
            load();
        }
    }

    public LiveData<Integer> getNumber() {
        return handle.getLiveData(key);
    }

    private void load() {
        SharedPreferences shp = getApplication().getSharedPreferences(shpName, Context.MODE_PRIVATE);
        int x = shp.getInt(key, 0);
        handle.set(key, x);
    }

    void save() {
        SharedPreferences shp = getApplication().getSharedPreferences(shpName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        //noinspection ConstantConditions
        editor.putInt(key, getNumber().getValue());
        editor.apply();
    }

    public void add(int x) {
        //noinspection ConstantConditions
        handle.set(key, getNumber().getValue() + x);
    }
}
