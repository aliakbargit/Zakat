package com.example.zakat.viewModels.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.zakat.SessionManager;
import com.example.zakat.models.core.User;
import com.example.zakat.repository.auth.AuthRepo;
import com.example.zakat.views.auth.AuthResource;

import javax.inject.Inject;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";

    private AuthRepo repo;
    private SessionManager sessionManager;

    @Inject
    public AuthViewModel(AuthRepo repo,SessionManager sessionManager) {
        this.repo=repo;
        this.sessionManager=sessionManager;
        if(repo.getLoggedInUser()){
            previouslyAuthorize();
        }
    }

    public void authenticateWithEmailPassword(String email,String password){
        sessionManager.loginWithEmailPassword(repo.loginWithEmailPassword(email, password));
    }

    public void previouslyAuthorize(){
        sessionManager.loginWithEmailPassword(repo.getLiveDataUser());
    }

    public LiveData<AuthResource<User>> observeAuthState(){
        return sessionManager.getAuthUser();
    }

    public void signOut(){
        sessionManager.logout(repo.signOut());
    }
}
