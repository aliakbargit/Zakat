package com.example.zakat.viewModels.user.fragments.type;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.zakat.models.core.SuccessOrFailMsg;
import com.example.zakat.models.core.ApplicationToSubmit;
import com.example.zakat.repository.user.ApplicationRepository;
import com.example.zakat.util.SuccessOrErrorResource;

import javax.inject.Inject;

public class CommonTypeViewModel extends ViewModel {
    private static final String TAG = "CommonTypeViewModel";
    private ApplicationRepository repo;

    @Inject
    public CommonTypeViewModel(ApplicationRepository repo) {
         this.repo = repo;
    }

    public void sendApplication(ApplicationToSubmit toDb){
       repo.applyForZakat(toDb);
    }

    public LiveData<SuccessOrErrorResource<SuccessOrFailMsg>> getSuccessOrFailsResult(){
        return repo.getSuccessOrFailMsg();
    }

    public void checkApplicationAlreadyExist(){
        repo.checkApplicationAlreadyExist();
    }

    public String getUid(){
        return repo.getUid();
    }



}
