package com.example.zakat.views.user.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.zakat.R;
import com.example.zakat.databinding.UserApplyPhaseOneBinding;
import com.example.zakat.models.ApplicationModel;
import com.example.zakat.models.core.User;
import com.example.zakat.viewModels.ViewModelProviderFactory;
import com.example.zakat.viewModels.user.fragments.UserApplyViewModel;
import com.example.zakat.views.auth.AuthResource;


import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

import static com.example.zakat.util.Constrains.USER_APPLY_PHASE_ONE;

public class UserApplyPhaseOneFragment extends DaggerFragment {
    private static final String TAG = "UserApplyPhaseOne";
    private UserApplyPhaseOneBinding binding;
    private NavController controller;
    private UserApplyViewModel viewModel;
    private String uid;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       binding = UserApplyPhaseOneBinding.inflate(inflater,container,false);

       binding.userApplyNextPhaseBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               setSendApplicationNextPhase();
           }
       });

        return binding.getRoot();
       // return  inflater.inflate(R.layout.user_apply_phase_one,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        controller = Navigation.findNavController(view);
        viewModel = new ViewModelProvider(this,providerFactory).get(UserApplyViewModel.class);
        uid = viewModel.getUser();
    }

    private void setSendApplicationNextPhase(){
        Log.d(TAG, "setSendApplicationNextPhase: "+uid);
        String applicationType = binding.applicantZakatCategory.getSelectedItem().toString();
        ApplicationModel applicationModel = new ApplicationModel(
                uid,"Ali Akbor",
                "65756456456456",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                applicationType,
                "",
                "",
                ""
        );
        Bundle bundle = new Bundle();
        bundle.putParcelable(USER_APPLY_PHASE_ONE,applicationModel);
        controller.navigate(R.id.action_user_apply_to_final_submission,bundle);
    }
}
