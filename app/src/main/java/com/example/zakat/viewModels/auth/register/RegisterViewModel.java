package com.example.zakat.viewModels.auth.register;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.zakat.SessionManager;
import com.example.zakat.models.RegisterModel;
import com.example.zakat.models.core.User;
import com.example.zakat.repository.auth.AuthRepo;
import com.example.zakat.views.auth.AuthResource;

import javax.inject.Inject;

public class RegisterViewModel extends ViewModel {
    private static final String TAG = "RegisterViewModel";
    private AuthRepo repo;
    private SessionManager sessionManager;

    @Inject
    public RegisterViewModel(SessionManager sessionManager, AuthRepo repo )
    {
        this.repo = repo;
        Log.d(TAG, "RegisterViewModel: Ready");
    }

    public void registerWithEmailPassword(RegisterModel model){
        repo.registerWithEmailPassword(model);
    }

    public LiveData<AuthResource<User>> observeAuthState(){
        return sessionManager.getAuthUser();
    }

    public LiveData<AuthResource<User>> registrationSuccessful(){
        return repo.getSuccessFullRegistration();
    }



}
