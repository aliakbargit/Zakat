package com.example.zakat;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.zakat.models.core.User;
import com.example.zakat.repository.auth.AuthRepo;
import com.example.zakat.views.auth.AuthResource;

import javax.inject.Inject;

public class SessionManager {

    public static final String TAG="SessionManager";
    private  MediatorLiveData<AuthResource<User>> cachedUser = new MediatorLiveData<>();

    @Inject
    public SessionManager() {
    }

    @Inject
    AuthRepo repo;

    public void loginWithEmailPassword(final LiveData<AuthResource<User>> source){
        if(cachedUser != null){
            cachedUser.setValue(AuthResource.loading((User)null));
            cachedUser.addSource(source, new Observer<AuthResource<User>>() {
                @Override
                public void onChanged(AuthResource<User> userAuthResource) {
                    cachedUser.setValue(userAuthResource);
                    cachedUser.removeSource(source);
                }
            });
        }
        else {
            Log.d(TAG, "loginWithEmailPassword: Previously login");
        }
    }

    public void loginWithGoogle(){

    }

    public void loginWithPhoneNumber(final LiveData<AuthResource<User>> source){
    }

    public LiveData<AuthResource<User>> getAuthUser(){
        return cachedUser;
    }

    public void logout(final LiveData<AuthResource<User>> user){
        repo.signOut();
        cachedUser.setValue(AuthResource.<User>logout());
    }


}
