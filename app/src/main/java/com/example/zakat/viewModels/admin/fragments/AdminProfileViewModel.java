package com.example.zakat.viewModels.admin.fragments;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.zakat.SessionManager;
import com.example.zakat.models.admin.AdminProfile;
import com.example.zakat.models.core.User;
import com.example.zakat.util.Resource;
import com.example.zakat.views.auth.AuthResource;

import javax.inject.Inject;

public class AdminProfileViewModel extends ViewModel {
    private static final String TAG = "ProfileFragmentViewMode";
    private LiveData<Resource<AdminProfile>> adminProfile;
    private SessionManager sessionManager;

    @Inject
    public AdminProfileViewModel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        Log.d(TAG, "ProfileFragmentViewModel: Ready");
    }

    public LiveData<AuthResource<User>> getAuthenticatedUser(){
        return sessionManager.getAuthUser();
    }

    public LiveData<Resource<AdminProfile>> getAdminProfile(){
        return null;
    }

    public void updateProfile(AdminProfile profile){
        Log.d(TAG, "updateProfile: Ready");
    }

}
