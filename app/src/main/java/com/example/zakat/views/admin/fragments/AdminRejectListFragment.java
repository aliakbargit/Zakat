package com.example.zakat.views.admin.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.zakat.R;
import com.example.zakat.databinding.AdminRejectListBinding;

import dagger.android.support.DaggerFragment;

public class AdminRejectListFragment extends DaggerFragment {
    private static final String TAG = "AdminRejectListFragment";
    private AdminRejectListBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       binding = AdminRejectListBinding.inflate(inflater,container,false);
        return binding.getRoot();

       // return  inflater.inflate(R.layout.admin_reject_list,container,false);
    }
}
