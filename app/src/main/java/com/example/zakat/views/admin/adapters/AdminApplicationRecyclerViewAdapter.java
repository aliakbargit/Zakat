package com.example.zakat.views.admin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zakat.databinding.AdminSingleApplicationViewBinding;
import com.example.zakat.models.core.ApplicationToSubmit;
import com.example.zakat.views.core.OnClickListeners;

import java.util.List;


public class AdminApplicationRecyclerViewAdapter extends RecyclerView.Adapter<AdminApplicationRecyclerViewAdapter.ApplicationViewHolder> {
    private Context context;
    private List<ApplicationToSubmit> data;
    private OnClickListeners.OnClickListenersAdmin clickListeners;

    public AdminApplicationRecyclerViewAdapter(Context context, List<ApplicationToSubmit> data, OnClickListeners.OnClickListenersAdmin clickListeners) {
        this.context = context;
        this.data = data;
        this.clickListeners = clickListeners;
    }

    @NonNull
    @Override
    public ApplicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        AdminSingleApplicationViewBinding binding = AdminSingleApplicationViewBinding.inflate(layoutInflater,parent,false);
        return new ApplicationViewHolder(binding,clickListeners);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicationViewHolder holder, int position) {
        holder.binding.singleAdminUserName.setText(data.get(position).getApplicantInfo().getFullName());
        holder.binding.singleAdminUserZakatType.setText(data.get(position).getApplicantInfo().getApplicationType());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0:data.size();
    }

    public class ApplicationViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        private AdminSingleApplicationViewBinding binding;
        private OnClickListeners.OnClickListenersAdmin listeners;

        public ApplicationViewHolder(@NonNull AdminSingleApplicationViewBinding binding, OnClickListeners.OnClickListenersAdmin listeners) {
            super(binding.getRoot());
            this.binding = binding;
            this.listeners = listeners;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listeners.onItemClick(data.get(getAbsoluteAdapterPosition()),getAbsoluteAdapterPosition());
        }
    }
}
