package com.example.zakat.views.admin.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.zakat.databinding.AdminPendingListBinding;
import com.example.zakat.models.core.ApplicationToSubmit;
import com.example.zakat.util.Constrains.APPLICATION_STATUS;
import com.example.zakat.util.Resource;
import com.example.zakat.viewModels.ViewModelProviderFactory;
import com.example.zakat.viewModels.admin.fragments.PendingListViewModel;
import com.example.zakat.views.admin.activities.ApplicationDetailsViewResult;
import com.example.zakat.views.admin.adapters.AdminApplicationRecyclerViewAdapter;
import com.example.zakat.views.core.OnClickListeners;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

import static com.example.zakat.util.Constrains.ADMIN_SINGLE_APPLICATION_BUNDLE_KEY;
import static com.example.zakat.util.Constrains.APPLICATION_TO_SUBMIT_KEY;

public class AdminPendingListFragment extends DaggerFragment implements OnClickListeners.OnClickListenersAdmin {
    private static final int ADMIN_PENDING_REQUEST_CODE = 332;
    private static final String TAG = "AdminPendingList";
    private AdminPendingListBinding binding;

    private PendingListViewModel viewModel;
    private ApplicationToSubmit application;
    private int position;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      binding = AdminPendingListBinding.inflate(inflater,container,false);

        return binding.getRoot();

      //  return  inflater.inflate(R.layout.admin_pending_list,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel  = new ViewModelProvider(this,providerFactory).get(PendingListViewModel.class);

        viewModel.getPendingListLiveData().removeObservers(this);
        viewModel.getPendingListLiveData().observe(getViewLifecycleOwner(), new Observer<Resource<List<ApplicationToSubmit>>>() {
            @Override
            public void onChanged(Resource<List<ApplicationToSubmit>> listResource) {
                if(listResource !=null){
                    switch (listResource.status){
                        case LOADING:
                            Log.d(TAG, "onChanged: Loading");
                            break;
                        case SUCCESS:
                            setUpRecyclerView(listResource.data);
                            break;
                        case ERROR:
                            Log.d(TAG, "onChanged: error"+listResource.message);
                            break;
                    }
                }
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADMIN_PENDING_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            setApplicationStatus((APPLICATION_STATUS) data.getSerializableExtra(ADMIN_SINGLE_APPLICATION_BUNDLE_KEY));
        }
        else {
            Log.d(TAG, "onActivityResult: "+requestCode+" and result"+resultCode);
        }
    }

    private void setApplicationStatus(APPLICATION_STATUS status){
        switch (status){
            case PENDING:
                Log.d(TAG, "setApplicationStatus: pending");
                break;
            case READING:
                Log.d(TAG, "setApplicationStatus: reading");
                break;
            case APPROVE:
                Log.d(TAG, "setApplicationStatus: Approve");
                break;
            case REJECT:
                Log.d(TAG, "setApplicationStatus: Rejected");
                break;
        }
    }

    private void setUpRecyclerView(List<ApplicationToSubmit> list){
        AdminApplicationRecyclerViewAdapter adapter = new AdminApplicationRecyclerViewAdapter(getContext(),list,this::onItemClick);
        binding.adminPendingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.adminPendingRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(ApplicationToSubmit application, int position) {
        this.application=application;
        this.position=position;
        Intent intent = new Intent(getActivity(), ApplicationDetailsViewResult.class);
        intent.putExtra(APPLICATION_TO_SUBMIT_KEY,application);
        startActivityForResult(intent,ADMIN_PENDING_REQUEST_CODE);
    }
}
