package com.example.zakat.views.admin.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.zakat.R;
import com.example.zakat.databinding.AdminAddSliderBinding;

import dagger.android.support.DaggerFragment;

public class AdminAddSliderFragment extends DaggerFragment {
    private static final String TAG = "AdminAddSliderFragment";
    private AdminAddSliderBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     binding = AdminAddSliderBinding.inflate(inflater,container,false);
        return binding.getRoot();

     //   return  inflater.inflate(R.layout.admin_add_slider,container,false);
    }
}
