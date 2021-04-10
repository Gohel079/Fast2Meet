package com.example.fast2meet.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fast2meet.Model.ElectricSeriveModel;
import com.example.fast2meet.Model.RenovationServiceModel;
import com.example.fast2meet.OverView_Ele;
import com.example.fast2meet.R;
import com.example.fast2meet.databinding.ListElectricianWorkBinding;

import java.util.ArrayList;

public class RenovationAdapter  extends RecyclerView.Adapter<RenovationAdapter.ViewHolder>{


    ArrayList<RenovationServiceModel> renovationServiceModelArrayList;
    ListElectricianWorkBinding binding;
    public  RenovationAdapter(ArrayList<RenovationServiceModel> renovationServiceModelArrayList)
    {
        this.renovationServiceModelArrayList = renovationServiceModelArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.list_electrician_work,parent,false);

        ViewHolder viewHolder = new ViewHolder(binding);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {

        RenovationServiceModel renovationServiceModel = renovationServiceModelArrayList.get(position);

        holder.binding.imgEle1.setImageResource(renovationServiceModel.getImgId());
        holder.binding.tvElectricServiceName.setText(renovationServiceModel.getServiceName());

    }

    @Override
    public int getItemCount() {
        return renovationServiceModelArrayList.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        ListElectricianWorkBinding binding;
        public ViewHolder(@NonNull ListElectricianWorkBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;

            itemView.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), OverView_Ele.class);
                    v.getContext().startActivity(intent);
                }
            });

        }
    }
}
