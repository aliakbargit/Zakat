package com.example.zakat.views.core;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.zakat.R;
import com.example.zakat.databinding.SuccessOrFailureBinding;
import com.example.zakat.models.core.SuccessOrFailureModel;

import dagger.android.support.DaggerFragment;

public class SuccessOrFailure extends DaggerFragment {
    private static final String TAG = "SuccessOrFailure";
    private SuccessOrFailureBinding binding;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       binding = SuccessOrFailureBinding.inflate(inflater,container,false);

        Bundle bundle = getArguments();

       binding.successOrFailureDoneBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               SuccessOrFailureModel failureModel = bundle.getParcelable("SuccessOrErrorData");

               Log.d(TAG, "onClick: Loaded"+failureModel.getClassName());
               navController.navigate(R.id.action_admin_successOrFailure_to_nav_admin_profile);
           }
       });

       return binding.getRoot();
        // return inflater.inflate(R.layout.success_or_failure,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
       // navController.getNavigatorProvider().toString().getClass();
        Log.d(TAG, "onViewCreated: nav");
    }
}
