package com.example.zakat.views.user.fragments.type;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.zakat.databinding.UserRentTypeFragmentBinding;
import com.example.zakat.models.ApplicationModel;
import com.example.zakat.models.core.SuccessOrFailMsg;
import com.example.zakat.models.types.RentHouse;
import com.example.zakat.models.core.ApplicationToSubmit;
import com.example.zakat.util.SuccessOrErrorResource;
import com.example.zakat.viewModels.ViewModelProviderFactory;
import com.example.zakat.viewModels.user.fragments.type.RentTypeViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

import static com.example.zakat.util.Constrains.USER_APPLICATION_MODEL;

public class RentHouseTypeFragment extends DaggerFragment {
    private static final String TAG = "RentHouseTypeFragment";
    private UserRentTypeFragmentBinding binding;
    private RentTypeViewModel viewModel;
    private ApplicationModel model;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = UserRentTypeFragmentBinding.inflate(inflater,container,false);


        Bundle args = getArguments();
        if(args !=null){
            model = (ApplicationModel)args.getParcelable(USER_APPLICATION_MODEL);
            binding.applicationTypeUser.setText(model.getApplicationType());
        }

        binding.userApplySubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RentHouse commonType = new RentHouse("78786756567bjs","","","","","2000","","","");
                ApplicationToSubmit toDb = new ApplicationToSubmit(model,commonType);
                viewModel.sendApplication(toDb);
            }
        });


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this,providerFactory).get(RentTypeViewModel.class);

        viewModel.getSuccessOrFailsResult().removeObservers(this);
        viewModel.getSuccessOrFailsResult().observe(getViewLifecycleOwner(), new Observer<SuccessOrErrorResource<SuccessOrFailMsg>>() {
            @Override
            public void onChanged(SuccessOrErrorResource<SuccessOrFailMsg> successOrFailMsgSuccessOrErrorResource) {

                switch (successOrFailMsgSuccessOrErrorResource.status){
                    case SUCCESS:
                        Log.d(TAG, "onChanged: "+ successOrFailMsgSuccessOrErrorResource.data.isSuccess());
                        Log.d(TAG, "onChanged: "+ successOrFailMsgSuccessOrErrorResource.data.isCalled());
                        break;
                    case FAILURE:
                        Log.d(TAG, "Fail ");
                        break;
                }
            }
        });
    }
}
