package com.example.zakat.views.admin.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.zakat.databinding.AdminApplicationDetailsViewBinding;

import dagger.android.support.DaggerFragment;

public class AdminApplicationDetailsView extends DaggerFragment {
    private static final String TAG = "AdminApplicationDetails";
    private AdminApplicationDetailsViewBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       binding = AdminApplicationDetailsViewBinding.inflate(inflater,container,false);
        return binding.getRoot();

       // return inflater.inflate(R.layout.admin_application_details_view,container,false);
    }
}
