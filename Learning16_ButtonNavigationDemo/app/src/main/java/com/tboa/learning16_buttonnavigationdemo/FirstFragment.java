package com.tboa.learning16_buttonnavigationdemo;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tboa.learning16_buttonnavigationdemo.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FirstViewModel mViewModel;
    private ImageView imageView;

    FragmentFirstBinding binding;

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false);
        imageView = binding.imageView;
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(FirstViewModel.class);
        binding.setData(mViewModel);
        binding.setLifecycleOwner(requireActivity());
        // TODO: Use the ViewModel
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0, 0);
        objectAnimator.setDuration(1000);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!objectAnimator.isRunning()) {
                    objectAnimator.setFloatValues(imageView.getRotation(), imageView.getRotation() + 100);
                    Log.d("myapp", "lalalal");
                    mViewModel.getRotation().setValue(mViewModel.getRotation().getValue() + 100);
                    objectAnimator.start();
                }
            }
        });
    }


}