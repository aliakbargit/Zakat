package com.example.zakat.viewModels.admin.fragments;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.zakat.models.ApplicationModel;
import com.example.zakat.util.Resource;

import java.util.List;

import javax.inject.Inject;

public class RejectListViewModel extends ViewModel {
    private static final String TAG = "RejectListViewModel";
    private LiveData<Resource<List<ApplicationModel>>> rejectListLiveData;

    @Inject
    public RejectListViewModel() {
        Log.d(TAG, "RejectListViewModel: Ready");
    }


    public LiveData<Resource<List<ApplicationModel>>> getRejectedApplicationList(){
        return null;
    }

    public void approveApplication(ApplicationModel model){

    }

    public void deleteApplication(ApplicationModel model){

    }

}
