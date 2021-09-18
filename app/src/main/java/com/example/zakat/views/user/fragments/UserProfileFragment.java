package com.example.zakat.views.user.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.zakat.R;
import com.example.zakat.databinding.UserProfileBinding;
import com.example.zakat.models.user.UserProfile;
import com.example.zakat.util.Resource;
import com.example.zakat.viewModels.ViewModelProviderFactory;
import com.example.zakat.viewModels.user.fragments.UserProfileViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class UserProfileFragment extends DaggerFragment {
    private UserProfileBinding binding;
    private UserProfileViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    private static final String TAG = "UserProfileFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = UserProfileBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this,providerFactory).get(UserProfileViewModel.class);

        viewModel.getUserProfile().removeObservers(this);
        viewModel.getUserProfile().observe(getViewLifecycleOwner(), new Observer<Resource<UserProfile>>() {
            @Override
            public void onChanged(Resource<UserProfile> userProfileResource) {
                if(userProfileResource != null){
                    switch (userProfileResource.status){
                        case LOADING:
                            Log.d(TAG, "onChanged: ");
                            break;
                        case SUCCESS:
                            Log.d(TAG, "onChanged: Success");
                            setUserProfileData(userProfileResource.data);
                            break;
                        case ERROR:
                            Log.d(TAG, "onChanged: Error");
                            break;
                    }
                }
            }
        });
    }

    private void setUserProfileData(UserProfile profile){
        Log.d(TAG, "setUserProfileData: "+profile.toString());
        binding.userName.setText(profile.getName()==null?"":profile.getName());
        binding.joinDate.setText(profile.getCratedOn()==null?"":profile.getCratedOn());

    }
}
