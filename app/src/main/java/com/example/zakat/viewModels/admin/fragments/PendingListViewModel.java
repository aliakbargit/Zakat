package com.example.zakat.viewModels.admin.fragments;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.zakat.models.ApplicationModel;
import com.example.zakat.models.core.ApplicationToSubmit;
import com.example.zakat.repository.admin.AdminApplicationRepo;
import com.example.zakat.util.Constrains.APPLICATION_STATUS;
import com.example.zakat.util.Resource;

import java.util.List;

import javax.inject.Inject;

public class PendingListViewModel extends ViewModel {
    private APPLICATION_STATUS status;
    private static final String TAG = "PendingListViewModel";
    private LiveData<Resource<List<ApplicationToSubmit>>> pendingListLive;
    private AdminApplicationRepo repo;

    @Inject
    public PendingListViewModel(AdminApplicationRepo repo) {
        this.repo = repo;
        Log.d(TAG, "PendingListViewModel: Ready");
    }

    public LiveData<Resource<List<ApplicationToSubmit>>> getPendingListLiveData(){
        return repo.getPendingListLiveData();
    }

    public void approveApplication(ApplicationToSubmit model){
        repo.approveApplication(model);
    }

    public void rejectApplication(ApplicationToSubmit model){
        repo.rejectApplication(model);
    }
    public void readApplication(ApplicationToSubmit toSubmit){
        repo.rejectApplication(toSubmit);
    }

    public void setStatus(APPLICATION_STATUS status){
        this.status = status;
    }

    public APPLICATION_STATUS getStatus() {
        return status;
    }


}
