package com.example.zakat.repository.admin;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.zakat.models.core.ApplicationToSubmit;
import com.example.zakat.repository.auth.AuthRepo;
import com.example.zakat.util.Resource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.zakat.util.Constrains.APPLICATION_PATH_ROOT;
import static com.example.zakat.util.Constrains.PENDING_PATH;

public class AdminApplicationRepo {
    public static final String TAG = "AdminApplicationRepo";
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private AuthRepo repo;
    private MediatorLiveData<Resource<List<ApplicationToSubmit>>> listApplicationPending;

    @Inject
    public AdminApplicationRepo(AuthRepo repo) {
        this.repo = repo;
    }

    public LiveData<Resource<List<ApplicationToSubmit>>> getPendingListLiveData(){
        DatabaseReference ref = database.getReference(APPLICATION_PATH_ROOT.concat("/").concat(PENDING_PATH));
        if(listApplicationPending == null){
            listApplicationPending = new MediatorLiveData<>();
            listApplicationPending.setValue(Resource.loading(null));
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<ApplicationToSubmit> dataList = new ArrayList<>();
                    if(snapshot.exists()){
                        for(DataSnapshot ds:snapshot.getChildren()){
                            dataList.add(ds.getValue(ApplicationToSubmit.class));
                        }
                        listApplicationPending.setValue(Resource.success(dataList));
                    }
                    else
                        listApplicationPending.setValue(Resource.error("Sorry error",null));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    listApplicationPending.setValue(Resource.error("No internet connection",null));
                }
            });
        }
        return listApplicationPending;
    }

    public void approveApplication(ApplicationToSubmit model){
        Log.d(TAG, "approveApplication: I got Approved");
    }

    public void rejectApplication(ApplicationToSubmit model){
        Log.d(TAG, "rejectApplication: I got rejected");

    }
    public void readApplication(ApplicationToSubmit toSubmit){
        Log.d(TAG, "readApplication: I got read");

    }
}
