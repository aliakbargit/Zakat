package com.example.zakat.repository.user;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.zakat.models.Progress;
import com.example.zakat.models.core.ApplicationToSubmit;
import com.example.zakat.models.core.SuccessOrFailMsg;
import com.example.zakat.models.user.type.Pending;
import com.example.zakat.repository.auth.AuthRepo;
import com.example.zakat.util.SuccessOrErrorResource;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;

import static com.example.zakat.util.Constrains.APPLICATION_PATH_ROOT;
import static com.example.zakat.util.Constrains.APPLICATION_PATH_ALL;
import static com.example.zakat.util.Constrains.APPLICATION_PATH;
import static com.example.zakat.util.Constrains.APPLICATION_PROGRESS_PATH;
import static com.example.zakat.util.Constrains.PENDING_PATH;
import static com.example.zakat.util.Constrains.APPROVED_PATH;
import static com.example.zakat.util.Constrains.REJECTED_PATH;
import static com.example.zakat.util.Constrains.USER_PATH;

public class ApplicationRepository {
    private static final String TAG = "ApplicationRepository";
    private DatabaseReference application = FirebaseDatabase.getInstance().getReference(APPLICATION_PATH);
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String applicationKey;
    private ApplicationToSubmit applicationForZakat;
    private MediatorLiveData<SuccessOrErrorResource<SuccessOrFailMsg>> successOrErrorLiveData;
    private AuthRepo repo;

    @Inject
    public ApplicationRepository(AuthRepo repo) {
        this.repo=repo;
    }

    public void applyForZakat(ApplicationToSubmit toDb){
        applicationForZakat = toDb;
        applicationKey = database.getReference(APPLICATION_PATH_ROOT.concat("/").concat(APPLICATION_PATH_ALL)).push().getKey();
        insetToProgress();
        insertToUserPending();
        insertToAllApplication();
        insertToPending();
    }

    public String getUid(){
        return repo.getUserUid();
    }

    public LiveData<SuccessOrErrorResource<SuccessOrFailMsg>> getSuccessOrFailMsg(){
        if (successOrErrorLiveData == null) {
            successOrErrorLiveData = new MediatorLiveData<>();
            SuccessOrFailMsg mm = new SuccessOrFailMsg();
            mm.setCalled(false);
            mm.setSuccess(true);
            mm.setMsg("Not called");
            successOrErrorLiveData.setValue(SuccessOrErrorResource.success(mm));
        }
        return successOrErrorLiveData;

    }


    public void checkApplicationAlreadyExist(){
        SuccessOrFailMsg mm = new SuccessOrFailMsg();
        DatabaseReference ref = database.getReference(APPLICATION_PATH_ROOT.concat("/").concat(PENDING_PATH));

       ref.child(repo.getUserUid()).addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               if(snapshot.exists()){
                   mm.setCalled(true);
                   mm.setCanInsert(false);
                   mm.setMsg("Data already exist");
                   successOrErrorLiveData.setValue(SuccessOrErrorResource.success(mm));
               }
               else
               {
                   mm.setCalled(true);
                   mm.setCanInsert(true);
                   mm.setMsg("Data does not exist");
                   successOrErrorLiveData.setValue(SuccessOrErrorResource.success(mm));
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {
               mm.setCalled(true);
               mm.setCanInsert(false);
               mm.setMsg("Database error");
                successOrErrorLiveData.setValue(SuccessOrErrorResource.failure("Database error",mm));
           }
       });
    }

    private void insetToProgress(){
        SuccessOrFailMsg mm = new SuccessOrFailMsg();
        Progress progress = new Progress(
                repo.getUserUid(),
                applicationKey,
                Timestamp.now().toString(),
                "10",
                "Application send for reviewing");
        DatabaseReference ref = database.getReference(APPLICATION_PROGRESS_PATH);
        ref.child(getUid()).setValue(progress).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mm.setComplete(true);
                successOrErrorLiveData.setValue(SuccessOrErrorResource.success(mm));
            }
        });
    }

    private void insertToUserPending(){
        SuccessOrFailMsg mm = new SuccessOrFailMsg();
        Pending pending = new Pending(
                repo.getUserUid(),
                applicationKey,
                applicationForZakat.getApplicantInfo().getFullName(),
                applicationForZakat.getApplicantInfo().getApplicationType(),
                "No image",
                Timestamp.now().toString(),
                Timestamp.now().toString()
        );

        DatabaseReference ref = database.getReference(USER_PATH.concat("/").concat(repo.getUserUid()));
        ref.child(PENDING_PATH).child(applicationKey).setValue(pending).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mm.setComplete(true);
                successOrErrorLiveData.setValue(SuccessOrErrorResource.success(mm));
            }
        });
    }

    private void insertToAllApplication(){
        applicationForZakat.setApplicationId(applicationKey);
        SuccessOrFailMsg mm = new SuccessOrFailMsg();
        DatabaseReference ref = database.getReference(APPLICATION_PATH_ROOT.concat("/").concat(APPLICATION_PATH_ALL));
        ref.child(applicationKey).setValue(applicationForZakat);
    }

    private void insertToPending(){
        applicationForZakat.setApplicationId(applicationKey);
        SuccessOrFailMsg mm = new SuccessOrFailMsg();
        DatabaseReference ref = database.getReference(APPLICATION_PATH_ROOT.concat("/").concat(PENDING_PATH));
        ref.child(repo.getUserUid()).setValue(applicationForZakat);
    }


}
