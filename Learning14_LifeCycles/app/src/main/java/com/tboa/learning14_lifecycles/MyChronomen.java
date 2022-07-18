package com.tboa.learning14_lifecycles;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.Chronometer;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MyChronomen extends Chronometer implements LifecycleObserver {
    private long elapsedTime;

    public MyChronomen(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void pauseMeter() {
        elapsedTime = SystemClock.elapsedRealtime() - getBase();
        stop();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void resumeMeter() {
        setBase(SystemClock.elapsedRealtime() - elapsedTime);
        start();
    }
}
