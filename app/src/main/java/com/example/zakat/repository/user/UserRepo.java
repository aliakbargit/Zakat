package com.example.zakat.repository.user;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.zakat.models.ApplicationModel;
import com.example.zakat.models.Featured;
import com.example.zakat.models.Progress;
import com.example.zakat.models.core.ApplicationToSubmit;
import com.example.zakat.models.core.SuccessOrFailMsg;
import com.example.zakat.models.core.ZakatArticle;
import com.example.zakat.models.user.UserProfile;
import com.example.zakat.repository.auth.AuthRepo;
import com.example.zakat.util.Resource;
import com.example.zakat.util.SuccessOrErrorResource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.zakat.util.Constrains.APPLICATION_PATH;
import static com.example.zakat.util.Constrains.APPLICATION_PATH_ALL;
import static com.example.zakat.util.Constrains.APPLICATION_PATH_ROOT;
import static com.example.zakat.util.Constrains.APPLICATION_PROGRESS_PATH;
import static com.example.zakat.util.Constrains.ARTICLE_PATH;
import static com.example.zakat.util.Constrains.FEATURED;
import static com.example.zakat.util.Constrains.USER_PATH;

public class UserRepo {
    private static final String TAG = "UserRepo";
    private AuthRepo repo;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference(FEATURED);
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference(ARTICLE_PATH);
    private DatabaseReference application = FirebaseDatabase.getInstance().getReference(APPLICATION_PATH);
    private MediatorLiveData<Resource<List<ZakatArticle>>> articleListLiveData;
    private MediatorLiveData<Resource<List<Featured>>> sliderListLiveData;
    private List<ZakatArticle> articleList;
    private List<Featured> featuredList;
    private MediatorLiveData<SuccessOrErrorResource<SuccessOrFailMsg>> successOrErrorResourceMediatorLiveData;

    private MediatorLiveData<Resource<Progress>> progressLiveData;
    private MediatorLiveData<Resource<ApplicationToSubmit>> applicationDetailsLiveData;
    private MediatorLiveData<Resource<UserProfile>> userProfileLiveData;

    @Inject
    public UserRepo(AuthRepo repo) {
        this.repo = repo;
    }

    public LiveData<Resource<UserProfile>> getUserProfileDetails(){
        DatabaseReference ref = database.getReference(USER_PATH.concat("/").concat(repo.getUserUid()));
        if(userProfileLiveData == null){
            userProfileLiveData = new MediatorLiveData<>();
            userProfileLiveData.setValue(Resource.loading(null));

            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                   if(snapshot.exists()){
                       UserProfile userProfile = snapshot.getValue(UserProfile.class);
                       userProfileLiveData.setValue(Resource.success(userProfile));
                   }
                   else
                       userProfileLiveData.setValue(Resource.error("Error on loading",null));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    userProfileLiveData.setValue(Resource.error("No internet Connection",null));
                }
            });
        }
        return userProfileLiveData;
    }

    public LiveData<Resource<ApplicationToSubmit>>  getApplicationStatusInfo(){
        if(applicationDetailsLiveData == null){
            applicationDetailsLiveData = new MediatorLiveData<>();
            applicationDetailsLiveData.setValue(Resource.loading(null));
        }
        return applicationDetailsLiveData;
    }

    public LiveData<Resource<List<ZakatArticle>>> getArticleList(){
        articleListLiveData = new MediatorLiveData<>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                articleList = new ArrayList<>();
               if(dataSnapshot.exists()) {
                   for (DataSnapshot ds : dataSnapshot.getChildren()) {
                       ZakatArticle article = ds.getValue(ZakatArticle.class);
                       articleList.add(article);
                   }

                   articleListLiveData.setValue(Resource.success(articleList));
                   Log.d(TAG, "onDataChange: " + articleList.size());
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                    articleListLiveData.setValue(Resource.error("Sorry",null));
            }
        });

        return articleListLiveData;
    }

    public LiveData<Resource<List<Featured>>> getSliderList(){
        sliderListLiveData = new MediatorLiveData<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                featuredList =new ArrayList<>();
                if(dataSnapshot.exists()){
                    for(DataSnapshot ds :dataSnapshot.getChildren()){
                        Featured featured = ds.getValue(Featured.class);
                        featuredList.add(featured);
                    }
                    sliderListLiveData.setValue(Resource.success(featuredList));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                sliderListLiveData.setValue(Resource.error("Sorry error",null));
            }
        });
        return sliderListLiveData;
    }

    public LiveData<Resource<Progress>> getApplicationStatus(){
        DatabaseReference ref = database.getReference(APPLICATION_PROGRESS_PATH);
        if(progressLiveData == null){
            progressLiveData = new MediatorLiveData<>();
            progressLiveData.setValue(Resource.loading(null));

            ref.child(repo.getUserUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        Progress progress = snapshot.getValue(Progress.class);
                        progressLiveData.setValue(Resource.success(progress));
                        loadDetailsApplication(progress.getId());
                    }
                    else{
                        progressLiveData.setValue(Resource.error("Sorry no data Found",null));
                        applicationDetailsLiveData.setValue(Resource.error("Sorry no data Found",null));
                    }



                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    progressLiveData.setValue(Resource.error("No internet connection",null));
                    applicationDetailsLiveData.setValue(Resource.error("No internet connection",null));
                }
            });
        }
        return progressLiveData;
    }


    public void applyForZakat(ApplicationToSubmit toDb){
        SuccessOrFailMsg mm = new SuccessOrFailMsg();
        mm.setCalled(true);
        mm.setSuccess(true);
        mm.setMsg("Success");
        successOrErrorResourceMediatorLiveData.setValue(SuccessOrErrorResource.success(mm));
        application.child(toDb.getApplicantInfo().getUid()).setValue(toDb);

    }

    public LiveData<SuccessOrErrorResource<SuccessOrFailMsg>> getSuccessOrFailMsg(){
        if (successOrErrorResourceMediatorLiveData == null) {
            successOrErrorResourceMediatorLiveData = new MediatorLiveData<>();
            SuccessOrFailMsg mm = new SuccessOrFailMsg();
            mm.setCalled(false);
            mm.setSuccess(true);
            mm.setMsg("Not called");
            successOrErrorResourceMediatorLiveData.setValue(SuccessOrErrorResource.success(mm));
        }
        return successOrErrorResourceMediatorLiveData;


    }

    private <T> void saveToDataBase(ApplicationModel model, T type){
        application.child(model.getUid()).setValue(model);
    }

    private void loadDetailsApplication(String id){
        DatabaseReference ref = database.getReference(APPLICATION_PATH_ROOT.concat("/").concat(APPLICATION_PATH_ALL));

        ref.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    ApplicationToSubmit applicationToSubmit = snapshot.getValue(ApplicationToSubmit.class);
                    applicationDetailsLiveData.setValue(Resource.success(applicationToSubmit));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
