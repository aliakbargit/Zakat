package com.example.zakat.views.user.fragments;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.zakat.R;
import com.example.zakat.databinding.UserCheckStatusBinding;
import com.example.zakat.models.Progress;
import com.example.zakat.models.core.ApplicationToSubmit;
import com.example.zakat.util.Resource;
import com.example.zakat.viewModels.ViewModelProviderFactory;
import com.example.zakat.viewModels.user.fragments.UserStatusViewModel;



import java.text.SimpleDateFormat;

import java.util.Locale;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class UserCheckStatusFragment extends DaggerFragment {
    private static final String TAG = "UserCheckStatusFragment";
    private UserCheckStatusBinding binding;
    private UserStatusViewModel statusViewModel;

    //progress
    private Handler handler = new Handler();
    private int percentLower = 0;
    private boolean stop = false;
    //end

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       binding=UserCheckStatusBinding.inflate(inflater,container,false);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        statusViewModel = new ViewModelProvider(this,providerFactory).get(UserStatusViewModel.class);

        statusViewModel.getApplicationStatus().removeObservers(this);
        statusViewModel.getApplicationStatus().observe(getViewLifecycleOwner(), new Observer<Resource<Progress>>() {
            @Override
            public void onChanged(Resource<Progress> progressResource) {
                if(progressResource !=null){
                    switch (progressResource.status){
                        case LOADING:
                            Log.d(TAG, "onChanged: Loading");
                            break;
                        case SUCCESS:
                            int per = Integer.parseInt(progressResource.data.getProgress());
                            progressShow(binding.zakatProgressStatus,binding.percentageInTheCenter,per);
                            setStatusDetails(progressResource.data);
                            Log.d(TAG, "onChanged: Success");
                            break;
                        case ERROR:
                            Log.d(TAG, "onChanged: Error"+progressResource.message);
                            break;
                    }
                }
            }
        });

        statusViewModel.getApplicationInfo().removeObservers(this);
        statusViewModel.getApplicationInfo().observe(getViewLifecycleOwner(), new Observer<Resource<ApplicationToSubmit>>() {
            @Override
            public void onChanged(Resource<ApplicationToSubmit> progressResource) {
                if(progressResource !=null){
                    switch (progressResource.status){
                        case LOADING:
                            Log.d(TAG, "onChanged: Loading");
                            break;
                        case SUCCESS:
                            setStatusUserInfo(progressResource.data);
                            Log.d(TAG, "onChanged: Success");
                            break;
                        case ERROR:
                            Log.d(TAG, "onChanged: Error"+progressResource.message);
                            break;
                    }
                }

            }
        });
    }

    private void setStatusDetails(Progress progress){


//        Date myDate = new Date(t*1000);
        SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
     //   String date = sfd.format(myDate);

        Log.d(TAG, "setStatusDetails: "+progress.getDate());

        binding.summaryDate.setText(progress.getDate());
        binding.summaryAppStatus.setText(progress.getStatus());

    }
    private void setStatusUserInfo(ApplicationToSubmit submit){
        binding.summaryName.setText(submit.getApplicantInfo().getFullName() == null?"No name":submit.getApplicantInfo().getFullName());
        binding.summaryType.setText(submit.getApplicantInfo().getApplicationType());
        binding.summaryIc.setText(submit.getApplicantInfo().getIc()==null?"No info":submit.getApplicantInfo().getIc());
    }



    private void progressShow(final ProgressBar progressBar, final TextView centerPercentage, int percentView){

        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.custom_progressbar_drawable);
        // final TextView centerPercentage = view.findViewById(R.id.percentage_inThe_center);

        final int percent = percentView;

        //centerPercentage.setText(String.valueOf(percent).concat("%"));
        // final ProgressBar mProgress = (ProgressBar) findViewById(R.id.circularProgressbar);
        // mProgress.setProgress(percent);   // Main Progress
        progressBar.setSecondaryProgress(100); // Secondary Progress
        progressBar.setMax(100); // Maximum Progress
        progressBar.setProgressDrawable(drawable);


        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (!stop) {
                    if(percentLower == percent){
                        percentLower -=1;
                        stop = true;
                    }
                    percentLower += 1;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            progressBar.setProgress(percentLower);
                            centerPercentage.setText(percentLower + "%");
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        // Just to display the progress slowly
                        Thread.sleep(30); //thread will take approx 1.5 seconds to finish
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
