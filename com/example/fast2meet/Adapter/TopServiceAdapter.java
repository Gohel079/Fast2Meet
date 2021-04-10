package com.example.fast2meet.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fast2meet.Model.TopServiceModel;
import com.example.fast2meet.R;
import com.example.fast2meet.databinding.ListTopServiceBinding;

import java.util.ArrayList;

public class TopServiceAdapter  extends RecyclerView.Adapter<TopServiceAdapter.ViewHolder> {

    ArrayList<TopServiceModel> topServiceModelArrayList;
    ListTopServiceBinding binding;

    public  TopServiceAdapter(ArrayList<TopServiceModel> topServiceModelArrayList)
    {
        this.topServiceModelArrayList=topServiceModelArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_top_service,parent,false);

        ViewHolder viewHolder = new ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        TopServiceModel topServiceModel = topServiceModelArrayList.get(position);

        holder.binding.imgService.setImageResource(topServiceModel.getImage());
    }

    @Override
    public int getItemCount() {
        return topServiceModelArrayList.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder
    {

        ListTopServiceBinding binding;
        public ViewHolder(@NonNull ListTopServiceBinding itemView) {
            super(itemView.getRoot());
            this.binding= itemView;
        }
    }
}
