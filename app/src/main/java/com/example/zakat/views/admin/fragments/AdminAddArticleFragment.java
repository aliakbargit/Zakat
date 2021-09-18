package com.example.zakat.views.admin.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.zakat.R;
import com.example.zakat.databinding.AdminAddArticaleBinding;
import com.example.zakat.models.core.SuccessOrFailureModel;
import com.example.zakat.models.core.User;
import com.example.zakat.util.Constrains;
import com.example.zakat.util.SuccessOrErrorResource;

import dagger.android.support.DaggerFragment;

public class AdminAddArticleFragment extends DaggerFragment {
    private static final String TAG = "AdminAddArticleFragment";
    private AdminAddArticaleBinding binding;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AdminAddArticaleBinding.inflate(inflater,container,false);

        User user = new User("user","email","user");


        SuccessOrErrorResource<User> mm = new SuccessOrErrorResource<User>(
          Constrains.SuccessOrFailure.SUCCESS,null,null
        );

        SuccessOrFailureModel data = new SuccessOrFailureModel("AdminAddArticleFragment", mm);

        binding.adminAddArticleSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("SuccessOrErrorData",data);
                navController.navigate(R.id.action_nav_add_article_to_admin_successOrFailure,bundle);
            }
        });
        return binding.getRoot();
       // return  inflater.inflate(R.layout.admin_add_articale,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);


    }
}

