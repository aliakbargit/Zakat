package com.example.zakat.views.admin.fragments;

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

import com.example.zakat.databinding.AdminProfileBinding;
import com.example.zakat.models.core.User;
import com.example.zakat.viewModels.ViewModelProviderFactory;
import com.example.zakat.viewModels.admin.fragments.AdminProfileViewModel;
import com.example.zakat.views.auth.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class AdminProfileFragment extends DaggerFragment {
    private static final String TAG = "ProfileFragment";
    private AdminProfileBinding binding;
    private AdminProfileViewModel viewModel;
    private NavController navController;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AdminProfileBinding.inflate(inflater,container,false);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        navController = Navigation.findNavController(view);


        viewModel = new ViewModelProvider(this,providerFactory).get(AdminProfileViewModel.class);

        subscribeAuthObserve();

    }

    private void subscribeAuthObserve(){
        viewModel.getAuthenticatedUser().removeObservers(getViewLifecycleOwner());
        viewModel.getAuthenticatedUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                switch (userAuthResource.status){
                    case LOADING:
                        Log.d(TAG, "onChanged: loading");
                        break;
                    case ERROR:
                        Log.d(TAG, "onChanged: Error");
                        break;
                    case AUTHENTICATED:
                        setUserDetails(userAuthResource.data);
                        break;
                    case NOT_AUTHENTICATED:
                        Log.d(TAG, "onChanged: Not Authenticated");
                        break;
                }
            }
        });
    }

    private void setUserDetails(User data) {
        Log.d(TAG, "setUserDetails: success"+data.toString());
    }

}
