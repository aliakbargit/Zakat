package com.example.zakat.repository.auth;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.zakat.models.RegisterModel;
import com.example.zakat.models.core.User;
import com.example.zakat.models.user.UserProfile;
import com.example.zakat.views.auth.AuthResource;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;

import static com.example.zakat.util.Constrains.ADMINS_PATH;
import static com.example.zakat.util.Constrains.USER_PATH;


public class AuthRepo {
    private static final String TAG = "AuthRepo";
    private AuthRepo instance;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference userRef = FirebaseDatabase.getInstance().getReference(ADMINS_PATH);
    private MediatorLiveData<AuthResource<User>> registrationLiveData;
    private User user;
    private RegisterModel registerModel;


    @Inject
    public AuthRepo() {
    }

    public LiveData<AuthResource<User>> loginWithEmailPassword(String email,String password){
        MediatorLiveData<AuthResource<User>> use = new MediatorLiveData<>();
        if(firebaseAuth.getCurrentUser() != null ){
            Log.d(TAG, "loginWithEmailPassword: "+firebaseAuth.getCurrentUser().getUid());
           return getLoggedInUserData(firebaseAuth.getCurrentUser().getUid());
        }
        else {
            Log.d(TAG, "loginWithEmailPassword: sorry auth is null");
            firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(authTask -> {
                if (authTask.isSuccessful()) {
                   // boolean isNewUser = authTask.getResult().getAdditionalUserInfo().isNewUser();
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    Log.d(TAG, "loginWithEmailPassword: auth is success "+firebaseUser.getUid());
                    if (firebaseUser != null) {
                        String uid=firebaseUser.getUid();
                        userRef.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    User userDB = snapshot.getValue(User.class);
                                    user = new User(userDB.getUid(),userDB.getEmail(),userDB.getType());
                                    use.setValue(AuthResource.authenticated(user));
                                }
                                else
                                    user = new User(uid,"example@gmail.com","user");
                                    use.setValue(AuthResource.authenticated(user));
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                } else {
                    Log.d(TAG, "Fail to login: "+authTask.getException().getMessage());
                    use.setValue(AuthResource.error("There is no user record corresponding to this identifier",null));
                }
            });
        }
        return use;
    }

    public boolean getLoggedInUser(){
        return firebaseAuth.getCurrentUser() != null;
    }

    public LiveData<AuthResource<User>> signOut(){
        Log.d(TAG, "signOut: Called");
        firebaseAuth.signOut();
        return null;
    }

    public LiveData<AuthResource<User>> getLiveDataUser(){
        String uid = firebaseAuth.getCurrentUser().getUid();
        return getLoggedInUserData(uid);
    }

    public void registerWithEmailPassword(RegisterModel registerModel){
        this.registerModel = registerModel;
        firebaseAuth.createUserWithEmailAndPassword(registerModel.getEmail(),registerModel.getPassword()).
                addOnCompleteListener(authResultTask->{
                    if(authResultTask.isSuccessful()){
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        if(firebaseUser != null){
                            User user = new User(firebaseUser.getUid(),registerModel.getEmail(),"user");
                            writeUserDetailsToDb(user);
                        }
                        else
                            registrationLiveData.setValue(AuthResource.error("Can't register",null));
                    }
                    else
                        registrationLiveData.setValue(AuthResource.error("Invalid user",null));
                });
    }

    public LiveData<AuthResource<User>> getSuccessFullRegistration(){
        if(registrationLiveData == null){
            registrationLiveData = new MediatorLiveData<>();
            registrationLiveData.setValue(AuthResource.error("Not yet completed",null));
        }
        return registrationLiveData;
    }


    private LiveData<AuthResource<User>> getLoggedInUserData(String uid){
        MediatorLiveData<AuthResource<User>> use = new MediatorLiveData<>();
        userRef.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    User userDB = snapshot.getValue(User.class);
                    user = new User(userDB.getUid(),userDB.getEmail(),userDB.getType());
                    use.setValue(AuthResource.authenticated(user));
                }
                else
                    user = new User(uid,"example@gmail.com","user");
                    use.setValue(AuthResource.authenticated(user));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return use;
    }

    public String getUserUid(){
        return firebaseAuth.getUid();
    }

    private void writeUserDetailsToDb(User user){
        DatabaseReference ref = database.getReference(USER_PATH.concat("/").concat(user.getUid()));
        UserProfile userProfile = new UserProfile(
                user.getUid(),
                registerModel.getName(),
                registerModel.getEmail(),
                "none",
                "user",
                "none",
                Timestamp.now().toString(),
                Timestamp.now().toString()
        );
             ref.setValue(userProfile).addOnCompleteListener(registration->{
                 if(registration.isSuccessful()){
                     registrationLiveData.setValue(AuthResource.authenticated(user));
                 }
             });




    }



}
