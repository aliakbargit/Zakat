package com.example.zakat.views.user.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.zakat.databinding.UserApplyPhaseTwoBinding;
import com.example.zakat.models.ApplicationModel;
import com.example.zakat.views.user.fragments.type.CommonTypeFragment;
import com.example.zakat.views.user.fragments.type.EducationTypeFragment;
import com.example.zakat.views.user.fragments.type.RentHouseTypeFragment;


import dagger.android.support.DaggerFragment;

import static com.example.zakat.util.Constrains.USER_APPLICATION_MODEL;
import static com.example.zakat.util.Constrains.USER_APPLY_PHASE_ONE;

public class UserApplyPhaseTwoFragment extends DaggerFragment {
    private static final String TAG = "UserApplyPhaseTwo";
    private UserApplyPhaseTwoBinding binding;
    private ApplicationModel model;
    private FrameLayout containerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = UserApplyPhaseTwoBinding.inflate(inflater,container,false);

        containerView = binding.fragmentContainerTypeUserForm;

        Bundle args = getArguments();
        if(args !=null){
            model = (ApplicationModel)args.getParcelable(USER_APPLY_PHASE_ONE);
            binding.applicationTypeUser.setText("Type "+model.getApplicationType());
            changeFragment(model,binding.getRoot());
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void changeFragment(ApplicationModel model, View view){
        Bundle bundle = new Bundle();
        bundle.putParcelable(USER_APPLICATION_MODEL,model);
        Log.d(TAG, "changeFragment: "+model.getApplicationType());

        switch (model.getApplicationType()){

            case "Zakat For Fee Sabilillah":
            case "Zakat For Zakat Collector":
            case "Zakat For Needy":
            case "Zakat For Converted Muslim":
            case "Zakat For Debtors":
                finalTypeFragmentTransaction(model,new CommonTypeFragment());
                break;
            case "Zakat For Rent House":
                finalTypeFragmentTransaction(model,new RentHouseTypeFragment());
                break;
            case "Zakat For Education":
                finalTypeFragmentTransaction(model,new EducationTypeFragment());
                break;
            default:
                Toast.makeText(getContext(),"Nothing Selected",Toast.LENGTH_LONG).show();
                break;
        }

    }

    public void finalTypeFragmentTransaction(ApplicationModel model, Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putParcelable(USER_APPLICATION_MODEL,model);
        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(containerView.getId(),fragment).commit();
    }
}
