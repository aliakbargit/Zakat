package com.example.zakat.views.user.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zakat.databinding.UserSingleArticleViewBinding;
import com.example.zakat.models.core.ZakatArticle;
import com.example.zakat.views.core.OnClickListeners;

import java.util.ArrayList;



public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<ZakatArticle> data;
    private OnClickListeners clickListeners;


    public RecyclerViewAdapter(Context context, ArrayList<ZakatArticle> data,OnClickListeners listeners) {
        this.clickListeners = listeners;
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        UserSingleArticleViewBinding binding = UserSingleArticleViewBinding.inflate(layoutInflater,parent,false);
        return new MyViewHolder(binding,clickListeners);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.binding.setArticle(data.get(position));

        Glide.with(context).load(data.get(position).getImage())
                .centerCrop()
                .into(holder.binding.RelatedArticleImage);

       // holder.textView.setText(data.get(position).getName());
//
//        holder.layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return data==null? 0 : data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private UserSingleArticleViewBinding binding;

        OnClickListeners listeners;
        public MyViewHolder(@NonNull UserSingleArticleViewBinding binding,OnClickListeners listeners) {
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

