package com.example.zakat.viewModels.user.fragments.type;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.zakat.models.core.SuccessOrFailMsg;
import com.example.zakat.models.core.ApplicationToSubmit;
import com.example.zakat.repository.user.UserRepo;
import com.example.zakat.util.SuccessOrErrorResource;

import javax.inject.Inject;

public class EducationTypeViewModel extends ViewModel {
    private static final String TAG = "EducationTypeViewModel";
    private UserRepo repo;

    @Inject
    public EducationTypeViewModel(UserRepo repo )
    {
        this.repo = repo;
        Log.d(TAG, "EducationTypeViewModel: Ready");
    }

    public void sendApplication(ApplicationToSubmit toDb){
        repo.applyForZakat(toDb);
        Log.d(TAG, "sendApplication: ");
    }

    public LiveData<SuccessOrErrorResource<SuccessOrFailMsg>> getSuccessOrFailsResult(){
        return repo.getSuccessOrFailMsg();
    }
}
