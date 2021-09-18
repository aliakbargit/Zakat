package com.example.zakat.repository.admin;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.zakat.models.Featured;
import com.example.zakat.models.admin.AdminProfile;
import com.example.zakat.models.admin.AdminApplicationListModel;
import com.example.zakat.models.core.ZakatArticle;
import com.example.zakat.util.Constrains.APPLICATION_STATUS;
import com.example.zakat.util.Resource;

import java.util.List;

import javax.inject.Inject;

public class AdminRepo {
    private static final String TAG = "AdminRepo";

    private MediatorLiveData<Resource<List<AdminApplicationListModel>>> ApproveListLiveData;
    private MediatorLiveData<Resource<List<AdminApplicationListModel>>> PendingListLiveData;
    private MediatorLiveData<Resource<List<AdminApplicationListModel>>> RejectListLiveData;


    @Inject
    public AdminRepo() {
    }

    public void addArticle(ZakatArticle article){
        Log.d(TAG, "addArticle: Ready");
    }

    public void addSliderInfo(Featured slider){
        Log.d(TAG, "addSliderInfo: Info got");
    }

    public LiveData<Resource<AdminProfile>> getAdminProfileData(){
        Log.d(TAG, "getAdminProfileData: Method called");
        return null;
    }

    public LiveData<Resource<List<AdminApplicationListModel>>> getApplicationApproveList(){
        Log.d(TAG, "getApplicationApproveList: Method called");
        return null;
    }
    public LiveData<Resource<List<AdminApplicationListModel>>> getApplicationPendingList(){
        Log.d(TAG, "getApplicationPendingList: Method called");
        return null;
    }
    public LiveData<Resource<List<AdminApplicationListModel>>> getApplicationRejectList(){
        Log.d(TAG, "getApplicationRejectList: Method called");
        return null;
    }

    public void processApplication(AdminApplicationListModel application, APPLICATION_STATUS status){
        switch (status){
            case READING:
                Log.d(TAG, "processApplication: Viewing");
                break;
            case APPROVE:
                Log.d(TAG, "processApplication: Approve");
                break;
            case REJECT:
                Log.d(TAG, "processApplication: Rejected");
            case PENDING:
                Log.d(TAG, "processApplication: Application Pending");
            default:
                Log.d(TAG, "processApplication: Nothing happen");
        }

        Log.d(TAG, "processApplication: Method called");
    }

}
