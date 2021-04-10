package com.example.fast2meet.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fast2meet.Model.ElectricSeriveModel;
import com.example.fast2meet.Model.PlumberServiceModel;
import com.example.fast2meet.OverView_Ele;
import com.example.fast2meet.R;
import com.example.fast2meet.databinding.ListElectricianWorkBinding;

import java.util.ArrayList;

public class PlumberServiceAdapter  extends RecyclerView.Adapter<PlumberServiceAdapter.ViewHolder> {

    ArrayList<PlumberServiceModel> PlumberServiceModel;
    ListElectricianWorkBinding binding;
    public  PlumberServiceAdapter(ArrayList<PlumberServiceModel> PlumberServiceModel)
    {
        this.PlumberServiceModel = PlumberServiceModel;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                , R.layout.list_electrician_work,parent,false);

        ViewHolder viewHolder = new ViewHolder(binding);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        PlumberServiceModel plumberServiceModel = PlumberServiceModel.get(position);

        holder.binding.imgEle1.setImageResource(plumberServiceModel.getImgId());
        holder.binding.tvElectricServiceName.setText(plumberServiceModel.getServiceName());

    }

    @Override
    public int getItemCount() {
        return PlumberServiceModel.size();
    }

    public  static  class  ViewHolder extends RecyclerView.ViewHolder
    {

        ListElectricianWorkBinding binding;
        public ViewHolder(@NonNull ListElectricianWorkBinding itemView) {
            super(itemView.getRoot());

            this.binding=itemView;

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
