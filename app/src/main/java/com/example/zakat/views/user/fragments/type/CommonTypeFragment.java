package com.example.zakat.views.user.fragments.type;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.zakat.databinding.UserCommonTypeFragmentBinding;
import com.example.zakat.models.ApplicationModel;
import com.example.zakat.models.core.SuccessOrFailMsg;
import com.example.zakat.models.types.CommonType;
import com.example.zakat.models.core.ApplicationToSubmit;
import com.example.zakat.util.SuccessOrErrorResource;
import com.example.zakat.viewModels.ViewModelProviderFactory;
import com.example.zakat.viewModels.user.fragments.type.CommonTypeViewModel;
import com.google.firebase.Timestamp;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

import static com.example.zakat.util.Constrains.USER_APPLICATION_MODEL;


public class CommonTypeFragment extends DaggerFragment {
    private UserCommonTypeFragmentBinding binding;
    private static final String TAG = "CommonTypeFragment";
    private ApplicationModel model;
    private CommonTypeViewModel viewModel;
    private boolean canInsert = false;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = UserCommonTypeFragmentBinding.inflate(inflater,container,false);

        Bundle args = getArguments();
        if(args !=null){
            model = (ApplicationModel)args.getParcelable(USER_APPLICATION_MODEL);
            binding.applicationTypeUser.setText(model.getApplicationType());
        }

        binding.userApplySubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canInsert){
                    processApplication();
                }
            }
        });

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this,providerFactory).get(CommonTypeViewModel.class);

        viewModel.getSuccessOrFailsResult().removeObservers(this);
        viewModel.getSuccessOrFailsResult().observe(getViewLifecycleOwner(), new Observer<SuccessOrErrorResource<SuccessOrFailMsg>>() {
            @Override
            public void onChanged(SuccessOrErrorResource<SuccessOrFailMsg> resource) {

                switch (resource.status){
                    case SUCCESS:
                        canInsert = resource.data.isCanInsert();
                        Log.d(TAG, "onChanged: can insert "+resource.data.isCanInsert());
                        Log.d(TAG, "onChanged: "+resource.data.getMsg());
                        break;
                    case FAILURE:
                        Log.d(TAG, "Fail ");
                        break;
                }
            }
        });

        viewModel.checkApplicationAlreadyExist();
    }

    private void processApplication(){
        CommonType commonType = new CommonType("78786756567bjs","","","","","1000","");
        ApplicationToSubmit toSubmit = new ApplicationToSubmit(
                Timestamp.now().toString(),Timestamp.now().toString(), viewModel.getUid(),"",model,commonType
        );
        viewModel.sendApplication(toSubmit);
    }
}
