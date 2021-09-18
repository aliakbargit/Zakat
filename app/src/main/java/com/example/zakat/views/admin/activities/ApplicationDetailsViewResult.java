package com.example.zakat.views.admin.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.zakat.databinding.ActivityApplicationDetailsViewResultBinding;
import com.example.zakat.util.Constrains.APPLICATION_STATUS;

import dagger.android.support.DaggerAppCompatActivity;

import static com.example.zakat.util.Constrains.ADMIN_SINGLE_APPLICATION_BUNDLE_KEY;

public class ApplicationDetailsViewResult extends DaggerAppCompatActivity {
    private ActivityApplicationDetailsViewResultBinding binding;
    private APPLICATION_STATUS status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityApplicationDetailsViewResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        status = APPLICATION_STATUS.READING;

        binding.buttonApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = APPLICATION_STATUS.APPROVE;
                Intent intent = getIntent();
                intent.putExtra(ADMIN_SINGLE_APPLICATION_BUNDLE_KEY,status);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

        binding.buttonReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status=APPLICATION_STATUS.REJECT;
                Intent intent = getIntent();
                intent.putExtra(ADMIN_SINGLE_APPLICATION_BUNDLE_KEY,status);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        status = APPLICATION_STATUS.READING;
        Intent intent = getIntent();
        intent.putExtra(ADMIN_SINGLE_APPLICATION_BUNDLE_KEY,status);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}