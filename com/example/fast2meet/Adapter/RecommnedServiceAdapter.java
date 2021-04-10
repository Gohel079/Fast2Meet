package com.example.fast2meet.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fast2meet.Model.RecommendServiceModel;
import com.example.fast2meet.R;
import com.example.fast2meet.databinding.ListRecommnedServiceBinding;

import java.util.ArrayList;

public class RecommnedServiceAdapter  extends RecyclerView.Adapter<RecommnedServiceAdapter.ViewHolder> {


    ArrayList<RecommendServiceModel> recommendServiceModelArrayList;
    ListRecommnedServiceBinding binding;

    public  RecommnedServiceAdapter(ArrayList<RecommendServiceModel> recommendServiceModelArrayList)
    {
        this.recommendServiceModelArrayList=recommendServiceModelArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_recommned_service,parent,false);

        ViewHolder viewHolder = new ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {

        RecommendServiceModel recommendServiceModel= recommendServiceModelArrayList.get(position);

        holder.binding.cardphoto.setImageResource(recommendServiceModel.getImg());
        holder.binding.imgname.setText(recommendServiceModel.getImgName());
    }

    @Override
    public int getItemCount() {
        return recommendServiceModelArrayList.size();
    }

    public  class  ViewHolder extends  RecyclerView.ViewHolder
    {

        ListRecommnedServiceBinding binding;
        public ViewHolder(@NonNull ListRecommnedServiceBinding itemView) {
            super(itemView.getRoot());
            this.binding=itemView;

        }
    }
}
