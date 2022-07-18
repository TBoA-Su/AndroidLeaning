package com.tboa.learning16_buttonnavigationdemo;

import androidx.lifecycle.ViewModelProvider;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SecondFragment extends Fragment {

    private SecondViewModel mViewModel;
    private ImageView imageView;

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        imageView = view.findViewById(R.id.imageView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(SecondViewModel.class);
        // TODO: Use the ViewModel
        imageView.setScaleX(mViewModel.scale);
        imageView.setScaleY(mViewModel.scale);
        ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(imageView, "scaleX", 0, 0);
        ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(imageView, "scaleY", 0, 0);
        objectAnimatorX.setDuration(500);
        objectAnimatorY.setDuration(500);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!objectAnimatorX.isRunning() && !objectAnimatorY.isRunning()) {
                    objectAnimatorX.setFloatValues(imageView.getScaleX(), imageView.getScaleX() + 0.1f);
                    objectAnimatorY.setFloatValues(imageView.getScaleY(), imageView.getScaleY() + 0.1f);
                    mViewModel.scale += 0.1;
                    objectAnimatorX.start();
                    objectAnimatorY.start();
                }
            }
        });
    }

}